package hw;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestSlidingQueue {

  @Test
  public void testTrivialExample() {
    // general test structure
    //        input = ...
    //        result = sut.f(input);
    //        assertSomething(result);
    final ArrayList<String> input = new ArrayList<>();
    Collections.sort(input);
    assertTrue(input.isEmpty());
  }

  @Test
  public void testIteratorNonempty() {
    final Iterator<String> input = Arrays.asList("hello", "world", "what", "up").iterator();
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
    final Iterator<String> input = Collections.emptyIterator();
    assertFalse(input.hasNext());
  }

  @Test
  public void testSlidingWindowEmpty() {
    final SlidingQueue sut = new SlidingQueue(3);
    final Iterator<String> input = Collections.emptyIterator();
    final List<Queue<String>> result = new ArrayList<>();
    // an observer instance that sends updates to a buffer (list) for testing
    final var outputToList = new OutputToList();
    sut.process(input, outputToList);
    assertTrue(result.isEmpty());
  }

  @Test
  public void testSlidingWindowNonempty() {
    final SlidingQueue sut = new SlidingQueue(3);
    final Iterator<String> input = Arrays.asList("asdf", "qwer", "oiui", "zxcv").iterator();
    final var outputToList = new OutputToList();
    sut.process(input, outputToList);
    final var result = outputToList.result;
    assertEquals(4, result.size());
    assertArrayEquals(new String[] {"asdf"}, result.get(0).toArray());
    assertArrayEquals(new String[] {"asdf", "qwer"}, result.get(1).toArray());
    assertArrayEquals(new String[] {"asdf", "qwer", "oiui"}, result.get(2).toArray());
    assertArrayEquals(new String[] {"qwer", "oiui", "zxcv"}, result.get(3).toArray());
  }

  private static class OutputToList implements Output {

    final List<Queue<String>> result = new ArrayList<>();

    @Override
    public void update(final Queue<String> value) {
      final Queue<String> snapshot = new LinkedList<>(value);
      result.add(snapshot);
    };
  }
}
