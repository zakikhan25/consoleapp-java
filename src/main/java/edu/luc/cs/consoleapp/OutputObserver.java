package edu.luc.cs.consoleapp;

import java.util.Queue;
import java.util.function.Predicate;

/**
 * Observer for decoupling sliding window logic from routing updates. The predicate returns false if
 * the output was unable to process the item.
 */
interface OutputObserver extends Predicate<Queue<String>> {}
