package hw;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class TestMainTestable {

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
    final WindowMaker sut = new WindowMaker(3);
    final Iterator<String> input = Collections.emptyIterator();
    final List<Queue<String>> result = new ArrayList<>();
    // an observer instance that sends updates to a buffer (list) for testing
    final Output outputToList =
        (final Queue<String> value) -> {
          final Queue<String> copyOfValue = new LinkedList<>(value);
          result.add(copyOfValue);
        };
    sut.slidingWindow(input, outputToList);
    assertTrue(result.isEmpty());
  }

  @Test
  public void testSlidingWindowNonempty() {
    final WindowMaker sut = new WindowMaker(3);
    final Iterator<String> input = Arrays.asList("asdf", "qwer", "oiui", "zxcv").iterator();
    final List<Queue<String>> result = new ArrayList<>();
    final Output outputToList =
        (final Queue<String> value) -> {
          final Queue<String> copyOfValue = new LinkedList<>(value);
          result.add(copyOfValue);
        };
    sut.slidingWindow(input, outputToList);
    assertEquals(4, result.size());
    assertArrayEquals(new String[] {"asdf"}, result.get(0).toArray());
    assertArrayEquals(new String[] {"asdf", "qwer"}, result.get(1).toArray());
    assertArrayEquals(new String[] {"asdf", "qwer", "oiui"}, result.get(2).toArray());
    assertArrayEquals(new String[] {"qwer", "oiui", "zxcv"}, result.get(3).toArray());
  }
}
