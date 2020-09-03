# Learning objectives

* Simple console app example
* Experience with Git source code management
* Building with Gradle

# System requirements

* [Java 11 SDK or later](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
* [Gradle](https://gradle.org/)

You may also be able to install these requirements through your package manager or [SDKMAN!](https://sdkman.io/).

Alternatively, you can use an IDE such as IntelliJ IDEA or Android Studio.

# Running the application

On Linux or Mac OS X:

    $ ./gradlew run

or

    $ ./gradlew run --args="3"

On Windows:

    > gradlew run

or

    > gradlew run --args="3"

# Running the tests

On Linux or Mac OS X:

    $ ./gradlew test

On Windows:

    > gradlew test

# Running the application outside Gradle

This allows passing command-line arguments directly:

On Linux or Mac OS X:

    $ ./gradlew jar startScript
    $ ./build/scripts/consoleapp-java

On Windows:

    > gradlew jar startSCript
    > build\scripts\consoleapp-java

Using the "fat" jar, which contains the application code plus its dependencies, one can choose among the various versions of the main application.
This requires having the `java` executable in the execution path.

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
