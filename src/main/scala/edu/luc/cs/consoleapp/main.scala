package edu.luc.cs.consoleapp

import org.apache.commons.collections4.queue.CircularFifoQueue
import scala.io.StdIn

object MainScala {

  val LAST_N_WORDS = 10

  def main(args: Array[String]): Unit = {
    if (args.length > 1) {
      System.err.println("usage: ./target/universal/stage/bin/consoleapp [ last_n_words ]")
      sys.exit(2)
    }

    var lastNWords = LAST_N_WORDS

    if (args.length == 1) {
      try {
        lastNWords = args(0).toInt
        if (lastNWords < 1) throw new NumberFormatException()
      } catch {
        case _: NumberFormatException =>
          System.err.println("argument should be a natural number")
          sys.exit(4)
      }
    }

    val queue = new CircularFifoQueue[String](lastNWords)

    Iterator
      .continually(StdIn.readLine())
      .takeWhile(_ != null)
      .flatMap(_.split("(?U)[^\\p{Alpha}0-9']+"))
      .filter(_.nonEmpty)
      .foreach { word =>
        queue.add(word)
        println(queue)
        if (Console.err.checkError()) sys.exit(1)
      }
  }
}
