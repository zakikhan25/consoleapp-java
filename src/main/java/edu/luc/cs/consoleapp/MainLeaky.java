package edu.luc.cs.consoleapp;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.stream.Stream;

import org.apache.commons.collections4.queue.CircularFifoQueue;

// see https://stackoverflow.com/questions/1963806/#21699069
// why we're using this implementation instead of java.util.ArrayQueue!

public class MainLeaky {

  public static final int LAST_N_WORDS = 10;

  public static void main(final String[] args) {

    // TODO consider using a command-line option library

    // perform argument validity checking
    if (args.length > 1) {
      System.err.println("usage: ./target/universal/stage/bin/consoleapp [ last_n_words ]");
      System.exit(2);
    }

    var lastNWords = LAST_N_WORDS;
    try {
      if (args.length == 1) {
        lastNWords = Integer.parseInt(args[0]);
        if (lastNWords < 1) {
          throw new NumberFormatException();
        }
      }
    } catch (final NumberFormatException ex) {
      System.err.println("argument should be a natural number");
      System.exit(4);
    }

    final var input = new Scanner(System.in).useDelimiter("(?U)[^\\p{Alpha}0-9']+");
    final var result = new LeakyQueue(lastNWords).process(input.tokens());

    result.forEach(
        value -> {
          System.out.println(value);
          // terminate on I/O error such as SIGPIPE
          if (System.out.checkError()) {
            System.exit(1);
          }
        });
  }

  private static class LeakyQueue {

    private final Queue<String> queue;

    public LeakyQueue(final int capacity) {
      queue = new CircularFifoQueue<>(capacity);
    }

    private List<Queue<String>> process(final Stream<String> input) {
      final List<Queue<String>> result = new LinkedList<>();
      input.forEach(
          word -> {
            queue.add(word); // the oldest item automatically gets evicted
            final var snapshot = new LinkedList<>(queue);
            result.add(snapshot);
          });
      return result;
    }
  }
}
