name := "hello-java"

version := "0.1"

javacOptions += "-Xlint:all"

javaOptions += "-enableassertions"

libraryDependencies ++= Seq(
 "com.novocode"   %  "junit-interface" % "0.11"   % Test
)

enablePlugins(JavaAppPackaging)
