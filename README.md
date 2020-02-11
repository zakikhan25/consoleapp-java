# Under construction - not current for this example

TODO change instructions to mvn

# Learning Objectives

* Simple console app example
* Experience with Git source code management
* Building with SBT

# System requirements

* Java 8 SDK or later
* [SBT](https://www.scala-sbt.org/1.x/docs/Setup.html)

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
