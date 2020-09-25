name := "consoleapp"

version := "0.1"

javacOptions in compile += "-Xlint:all"

javaOptions += "-enableassertions"

libraryDependencies ++= Seq(
 "org.apache.commons" %  "commons-collections4" % "4.4",
 "com.novocode"       %  "junit-interface"      % "0.11" % Test
)


jacocoReportSettings := JacocoReportSettings()
  .withThresholds(
    JacocoThresholds(
      instruction = 80,
      method = 100,
      branch = 100,
      complexity = 100,
      line = 90,
      clazz = 100)
  )

enablePlugins(JavaAppPackaging)
