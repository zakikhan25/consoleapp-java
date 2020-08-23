package hw;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class TestMainTestableInteractive {

  @Test
  public void testInteractiveBehavior() {
    // the test input
    final Iterator<String> input = Arrays.asList("asdf", "qwer", "oiui", "zxcv").iterator();
    // the expected interaction trace
    final List<TraceEvent> expectedTrace = new LinkedList<>();
    expectedTrace.add(new InputEvent("asdf"));
    expectedTrace.add(new OutputEvent("asdf"));
    expectedTrace.add(new InputEvent("qwer"));
    expectedTrace.add(new OutputEvent("asdf", "qwer"));
    expectedTrace.add(new InputEvent("oiui"));
    expectedTrace.add(new OutputEvent("asdf", "qwer", "oiui"));
    expectedTrace.add(new InputEvent("zxcv"));
    expectedTrace.add(new OutputEvent("qwer", "oiui", "zxcv"));
    // create and exercise the SUT
    final WindowMaker sut = new WindowMaker(3);
    final List<TraceEvent> actualTrace = new Tracing(sut).run(input);
    // make sure the expected and actual traces match
    assertEquals(expectedTrace, actualTrace);
  }
}

// A mini-framework for trace-based testing of interactive behavior (WIP)

interface TraceEvent {}

class InputEvent implements TraceEvent {
  private final String value;

  public InputEvent(final String value) {
    this.value = value;
  }

  @Override
  public boolean equals(final Object that) {
    return that == this
        || that != null
            && (that instanceof InputEvent)
            && this.value.equals(((InputEvent) that).value);
  }

  @Override
  public int hashCode() {
    return value.hashCode();
  }

  @Override
  public String toString() {
    return "InputEvent(" + value + ")";
  }
}

class OutputEvent implements TraceEvent {
  private final List<String> value;

  public OutputEvent(final String... values) {
    this.value = Arrays.asList(values);
  }

  @Override
  public int hashCode() {
    return value.hashCode();
  }

  @Override
  public boolean equals(final Object that) {
    return that == this
        || that != null
            && (that instanceof OutputEvent)
            && this.value.equals(((OutputEvent) that).value);
  }

  @Override
  public String toString() {
    return "OutputEvent(" + value + ")";
  }
}

class Tracing {

  private final WindowMaker sut;

  /** The actual interaction event trace. */
  private final List<TraceEvent> trace = new LinkedList<>();

  public Tracing(final WindowMaker sut) {
    this.sut = sut;
  }

  public List<TraceEvent> run(final Iterator<String> input) {
    final Iterator<String> tracedInput = traced(input);
    // output sink that traces every invocation of update()
    final Output outputToTrace =
        (final Queue<String> value) -> {
          final List<String> copyOfValue = new LinkedList<>(value);
          trace.add(new OutputEvent(copyOfValue.toArray(new String[] {})));
        };
    sut.slidingWindow(tracedInput, outputToTrace);
    return Collections.unmodifiableList(trace);
  }

  /** Instruments (decorates) the iterator to trace every invocation of next(). */
  private Iterator<String> traced(final Iterator<String> input) {
    return new Iterator<String>() {
      @Override
      public boolean hasNext() {
        return input.hasNext();
      }

      @Override
      public String next() {
        final String value = input.next();
        trace.add(new InputEvent(value));
        return value;
      }
    };
  }
}
