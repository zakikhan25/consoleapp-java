package hw;

import org.apache.commons.collections4.queue.CircularFifoQueue;

import java.util.Iterator;
import java.util.Queue;

class SlidingQueue {

  protected final Queue<String> queue;

  public SlidingQueue(final int queueSize) {
    this.queue = new CircularFifoQueue<>(queueSize);
  }

  public void process(final Iterator<String> input, final Output output) {
    while (input.hasNext()) {
      final String word = input.next();
      queue.add(word); // the oldest item automatically gets evicted
      output.update(queue);
    }
  }
}
