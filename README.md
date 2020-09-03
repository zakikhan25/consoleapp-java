# Learning Objectives

* Simple console app example
* Experience with Git source code management
* Building with Gradle

# System requirements

* [Java 11 SDK or later](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
* [Gradle](https://gradle.org/)

You may also be able to install these requirements through your package manager or [SDKMAN!](https://sdkman.io/).


# Running the Application

On Linux or Mac OS X:

    $ sbt run

or

    $ sbt 'run arg1 arg2 arg3'

On Windows:

    > sbt run

or

    > sbt "run arg1 arg2 arg3"

# Running the Tests

On Linux or Mac OS X:

    $ sbt test

On Windows:

    > sbt test

# Running the Application Outside SBT

This allows passing command-line arguments directly:

On Linux or Mac OS X:

    $ sbt stage
    $ ./target/universal/stage/bin/consoleapp arg1 arg2 arg3

On Windows:

    > sbt stage
    > .\target\universal\stage\bin\consoleapp arg1 arg2 arg3
