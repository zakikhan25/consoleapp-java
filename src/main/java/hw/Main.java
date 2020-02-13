package hw;

import java.util.Queue;
// see https://stackoverflow.com/questions/1963806/#21699069
// why we're using this implementation instead of java.util.ArrayQueue!
import org.apache.commons.collections4.queue.CircularFifoQueue;
import org.apache.commons.cli.*;
import java.util.Scanner;
import sun.misc.Signal;

public class Main {

  public static int LAST_N_WORDS = 10;

  public static void main(final String[] args) {

    // TODO consider using a command-line option library
    // TODO this shows how to do it; still need to add help
    // TODO consider wrapping this in a static method similar to scopt approach: MyConfig getCommandLine(MyConfig defaults)
    Options options = new Options();
    Option lastnOpt = Option.builder("lastn").hasArg().build();
    lastnOpt.setType(Number.class);
    options.addOption(lastnOpt);
    CommandLineParser parser = new DefaultParser();
    int lastNWords = LAST_N_WORDS;
    try {
      CommandLine cmd = parser.parse( options, args);
      Number lastn = (Number)cmd.getParsedOptionValue("lastn"); 
      if (lastn != null) {
        lastNWords = lastn.intValue();
      }
    } catch (final ParseException pe) {
      System.err.println("Could not parse command line.");
      System.exit(1);
    }

    // properly terminate on SIGPIPE received from downstream
    // see also http://lucproglangcourse.github.io/imperative.html#the-role-of-console-applications
    if (!"Windows".equals(System.getProperty("os.name"))) {
      Signal.handle(new Signal("PIPE"), (final Signal sig) -> System.exit(1));
    }

    final Scanner input = new Scanner(System.in).useDelimiter("(?U)[^\\p{Alpha}0-9']+");
    final Queue<String> queue = new CircularFifoQueue<>(lastNWords);

    while (input.hasNext()) {
      final String word = input.next();
      queue.add(word); // the oldest item automatically gets evicted
      System.out.println(queue);
    }
  }
}
