package hw;

import java.util.Queue;

/** Observer for decoupling sliding window logic from routing updates. */
interface Output {
  void update(Queue<String> value);
}
