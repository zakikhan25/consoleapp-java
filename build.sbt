name := "consoleapp"

version := "0.2"

compile / javacOptions += "-Xlint:all"

javaOptions += "-enableassertions"

ThisBuild / libraryDependencies ++= Seq(
  "org.apache.commons" %  "commons-collections4" % "4.4",
  "net.aichler" % "jupiter-interface" % "0.11.0" % Test,
  "net.jqwik"   % "jqwik"             % "1.6.5"  % Test
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
  .withFormats(JacocoReportFormats.HTML)

enablePlugins(JavaAppPackaging)
