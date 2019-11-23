package hw;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

// A mini-framework for trace-based testing of interactive behavior (WIP)

interface TraceEvent<Input, Result> {}

class InputEvent<Input, Result> implements TraceEvent<Input, Result> {
  private Input value;

  public InputEvent(final Input value) {
    this.value = value;
  }

  @Override
  public boolean equals(final Object that) {
    return that != null
        && (that instanceof InputEvent)
        && this.value.equals(((InputEvent) that).value);
  }

  @Override
  public String toString() {
    return "InputEvent(" + value + ")";
  }
}

class OutputEvent<Input, Result> implements TraceEvent<Input, Result> {
  private Result value;

  public OutputEvent(final Result value) {
    this.value = value;
  }

  @Override
  public boolean equals(final Object that) {
    return that != null
        && (that instanceof OutputEvent)
        && this.value.equals(((OutputEvent) that).value);
  }

  @Override
  public String toString() {
    return "OutputEvent(" + value + ")";
  }
}

public class TestMainTestableInteractive {

  @Test
  public void testInteractiveBehavior() {
    final WindowMaker sut = new WindowMaker(3);
    final Iterator<String> input = Arrays.asList("asdf", "qwer", "oiui", "zxcv").iterator();
    // the actual event trace instance
    final List<TraceEvent<String, List<String>>> trace = new LinkedList<>();
    // instrument the iterator so that every invocation of next() gets traced
    final Iterator<String> tracedInput =
        new Iterator<String>() {
          @Override
          public boolean hasNext() {
            return input.hasNext();
          }

          @Override
          public String next() {
            final String value = input.next();
            trace.add(new InputEvent<>(value));
            return value;
          }
        };
    // create an output sink so that every invocation of update() gets traced
    final Output outputToTrace =
        (final Queue<String> value) -> {
          final List<String> copyOfValue = new LinkedList<>(value);
          trace.add(new OutputEvent<>(copyOfValue));
        };
    // the expected trace
    final List<TraceEvent<String, List<String>>> expectedTrace = new LinkedList<>();
    expectedTrace.add(new InputEvent<>("asdf"));
    expectedTrace.add(new OutputEvent<>(Arrays.asList("asdf")));
    expectedTrace.add(new InputEvent<>("qwer"));
    expectedTrace.add(new OutputEvent<>(Arrays.asList("asdf", "qwer")));
    expectedTrace.add(new InputEvent<>("oiui"));
    expectedTrace.add(new OutputEvent<>(Arrays.asList("asdf", "qwer", "oiui")));
    expectedTrace.add(new InputEvent<>("zxcv"));
    expectedTrace.add(new OutputEvent<>(Arrays.asList("qwer", "oiui", "zxcv")));
    // exercise the SUT
    sut.slidingWindow(tracedInput, outputToTrace);
    // make sure the expected and actual traces match
    assertEquals(expectedTrace, trace);
  }
}
