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
    final Iterator<String> input =
        Arrays.asList(new String[] {"hello", "world", "what", "up"}).iterator();
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
    final Iterator<String> input = Arrays.asList(new String[] {}).iterator();
    assertFalse(input.hasNext());
  }

  @Test
  public void testSlidingWindowEmpty() {
    final WindowMaker sut = new WindowMaker(3);
    final Iterator<String> input = Arrays.asList(new String[] {}).iterator();
    final List<Queue<String>> result = new ArrayList<>();
    // an observer instance that sends updates to a buffer (list) for testing
    final Output outputToList =
        new Output() {
          public void update(final Queue<String> value) {
            final Queue<String> copyOfValue = new LinkedList<>(value);
            result.add(copyOfValue);
          }
        };
    sut.slidingWindow(input, outputToList);
    assertTrue(result.isEmpty());
  }

  @Test
  public void testSlidingWindowNonempty() {
    final WindowMaker sut = new WindowMaker(3);
    final Iterator<String> input =
        Arrays.asList(new String[] {"asdf", "qwer", "oiui", "zxcv"}).iterator();
    final List<Queue<String>> result = new ArrayList<>();
    final Output outputToList =
        new Output() {
          public void update(final Queue<String> value) {
            final Queue<String> copyOfValue = new LinkedList<>();
            copyOfValue.addAll(value);
            result.add(copyOfValue);
          }
        };
    sut.slidingWindow(input, outputToList);
    assertEquals(4, result.size());
    assertEquals("asdf", result.get(0).peek()); // [asdf]
    assertEquals("asdf", result.get(1).peek()); // [asdf,qwer]
    assertEquals("asdf", result.get(2).peek()); // [asdf,qwer,oiui]
    assertEquals("qwer", result.get(3).peek()); // [qwer,oiui,zxcv]
  }
}
