package hw;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestSlidingQueue {

  @Test
  public void testTrivialExample() {
    // general test structure
    //        input = ...
    //        result = sut.f(input);
    //        assertSomething(result);
    final var input = Collections.<String>emptyList();
    Collections.sort(input);
    assertTrue(input.isEmpty());
  }

  @Test
  public void testIteratorNonempty() {
    final var input = List.of("hello", "world", "what", "up").iterator();
    assertTrue(input.hasNext());
    assertEquals("hello", input.next());
    assertTrue(input.hasNext());
    assertEquals("world", input.next());
    assertTrue(input.hasNext());
    assertEquals("what", input.next());
    assertTrue(input.hasNext());
    assertEquals("up", input.next());
    assertFalse(input.hasNext());
  }

  @Test
  public void testIteratorEmpty() {
    final var input = Collections.<String>emptyIterator();
    assertFalse(input.hasNext());
  }

  @Test
  public void testSlidingWindowEmpty() {
    final var sut = new SlidingQueue(3);
    final var input = Collections.<String>emptyIterator();
    // an observer instance that sends updates to a buffer (list) for testing
    final var outputToList = new OutputToList();
    sut.process(input, outputToList);
    final var result = outputToList.result;
    assertTrue(result.isEmpty());
  }

  @Test
  public void testSlidingWindowNonempty() {
    final var sut = new SlidingQueue(3);
    final var input = List.of("asdf", "qwer", "oiui", "zxcv").iterator();
    final var outputToList = new OutputToList();
    sut.process(input, outputToList);
    final var result = outputToList.result;
    assertEquals(4, result.size());
    assertEquals(List.of("asdf"), result.get(0));
    assertEquals(List.of("asdf", "qwer"), result.get(1));
    assertEquals(List.of("asdf", "qwer", "oiui"), result.get(2));
    assertEquals(List.of("qwer", "oiui", "zxcv"), result.get(3));
  }

  private static class OutputToList implements OutputObserver {

    final List<Queue<String>> result = new ArrayList<>();

    @Override
    public void accept(final Queue<String> value) {
      final var snapshot = new LinkedList<>(value);
      result.add(snapshot);
    }
  }
}
