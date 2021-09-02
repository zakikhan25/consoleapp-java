[![Gradle Build](https://github.com/lucoodevcourse/consoleapp-java/actions/workflows/gradle-build.yml/badge.svg)](https://github.com/lucoodevcourse/consoleapp-java/actions/workflows/gradle-build.yml)

# Learning objectives

* simple console app example
* experience with Git source code management
* building with Gradle
* stream processing (finite vs. infinite/unbounded)
* pipes and filters architecture
* representing streams using the Iterator design pattern
* separation of processing and I/O concerns
* testability using the Observer design pattern
* time/space complexity and scalability


# System requirements

* [Java 11 SDK or later](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
* [Gradle](https://gradle.org/)

You may also be able to install these requirements through your package manager or [SDKMAN!](https://sdkman.io/).

Alternatively, you can use an IDE such as IntelliJ IDEA or Android Studio.

# Description

This application maintains a running FIFO queue of the last n words read from the standard input (stdin).
For each word read, the application prints the updated queue to the standard output (stdout) in the format

    [w1, w2, ..., wn]

where wn is the most recent word read, and w1 is the oldest word still in the queue.

The default queue capacity is 10, and a different capacity can be passed as a command-line argument.

If multiple words are entered on the same line, the application processes them separately, using non-word characters, such as whitespace, as separators.

# Examples

```
$ java -cp build/lib/consoleapp-java-0.1-SNAPSHOT.jar hw.Main 3
w1 w2
[w1]
[w1, w2]
w3
[w1, w2, w3]
w4 w5
[w2, w3, w4]
[w3, w4, w5]
w6
[w4, w5, w6]
EOF
```

# Running the application

For the remaining commands to work, you should be in the project root directory, e.g., `/home/yourid/StudioProjects/cs313413f20p1a`.

On Linux or Mac OS X:

    $ ./gradlew run

or with an explicit argument and redirecting stdin from a sample file:

    $ ./gradlew run --args="3" < build.gradle

On Windows:

    > gradlew run

or with an explicit argument and redirecting stdin from a sample file:

    > gradlew run --args="3" < build.gradle

# Running the tests

On Linux or Mac OS X:

    $ ./gradlew test

On Windows:

    > gradlew test

# Running the application outside Gradle

On Linux or Mac OS X:

    $ ./gradlew jar startScript
    $ ./build/scripts/consoleapp-java

On Windows:

    > gradlew jar startScript
    > build\scripts\consoleapp-java

This allows using the application as part of a [pipeline](https://ss64.com/nt/syntax-redirection.html):

    $ yes hello | ./build/scripts/consoleapp-java | head -n 10

Furthermore, using the "fat" jar, which contains the application code plus its dependencies, one can choose among the various versions of the main application.
This requires having the `java` executable in the execution path.
Add command-line arguments and input/output redirect as needed.

On Linux or Mac OS X:

    $ ./gradlew jar
    $ java -cp build/lib/consoleapp-java-0.1-SNAPSHOT.jar hw.Main
    $ java -cp build/lib/consoleapp-java-0.1-SNAPSHOT.jar hw.MainLeaky
    $ java -cp build/lib/consoleapp-java-0.1-SNAPSHOT.jar hw.MainTestable

On Windows:

    > gradlew jar
    > java -cp build/lib/consoleapp-java-0.1-SNAPSHOT.jar hw.Main
    > java -cp build/lib/consoleapp-java-0.1-SNAPSHOT.jar hw.MainLeaky
    > java -cp build/lib/consoleapp-java-0.1-SNAPSHOT.jar hw.MainTestable
