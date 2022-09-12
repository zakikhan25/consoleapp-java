package edu.luc.cs.consoleapp;

import java.util.Queue;
import java.util.stream.Stream;

import org.apache.commons.collections4.queue.CircularFifoQueue;

class SlidingQueue {

  protected final Queue<String> queue;

  public SlidingQueue(final int queueSize) {
    this.queue = new CircularFifoQueue<>(queueSize);
  }

  public void process(final Stream<String> input, final OutputObserver output) {
    input.takeWhile(word -> {
          queue.add(word); // the oldest item automatically gets evicted
          return output.test(queue);
        })
        .count(); // forces evaluation of the entire stream
  }
}
