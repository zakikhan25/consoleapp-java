name := "consoleapp"

version := "0.1"

javacOptions in compile += "-Xlint:all"

javaOptions += "-enableassertions"

libraryDependencies ++= Seq(
 "org.apache.commons" %  "commons-collections4" % "4.4",
 "com.novocode"       %  "junit-interface"      % "0.11" % Test
)

enablePlugins(JavaAppPackaging)
