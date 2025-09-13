version := "0.2"

ThisBuild / scalaVersion := "2.13.12"  // specify Scala version

ThisBuild / libraryDependencies ++= Seq(
  "org.apache.commons" %  "commons-collections4" % "4.5.0",
  "org.apache.commons" % "commons-collections4" % "4.5.0",
"com.github.sbt.junit" % "jupiter-interface" % "0.15.1" % Test,
  "net.jqwik"   % "jqwik"             % "1.9.3"  % Test
  "net.jqwik" % "jqwik" % "1.9.3" % Test
)

enablePlugins(JavaAppPackaging)

// Specify the Scala main class to avoid multiple main class conflicts
Compile / mainClass := Some("edu.luc.cs.consoleapp.MainScala")
