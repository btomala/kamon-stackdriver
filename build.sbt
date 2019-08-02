val kamon            = "io.kamon"         %% "kamon-core"             % "2.0.0"
val kamonTestKit     = "io.kamon"         %% "kamon-testkit"          % "2.0.0"
val kamonLogback     = "io.kamon"         %% "kamon-logback"          % "2.0.0"
val googleCloudCore  = "com.google.cloud" % "google-cloud-core"       % "1.84.0"
val googleMonitoring = "com.google.cloud" % "google-cloud-monitoring" % "1.84.0"
val googleTracing    = "com.google.cloud" % "google-cloud-trace"      % "0.102.0-beta"
val sprayJson        = "io.spray"         %% "spray-json"             % "1.3.5"

lazy val `kamon-stackdriver-root` = (project in file("."))
  .settings(noPublishing)
  .settings(
    skip in publish := true,
    bintrayEnsureBintrayPackageExists := {},
    crossScalaVersions := Seq.empty
  )
  .aggregate(`kamon-stackdriver`, `kamon-logback-stackdriver`)

val `kamon-stackdriver` = project
  .settings(
    libraryDependencies ++=
      compileScope(kamon, googleMonitoring, googleTracing) ++ testScope(logbackClassic, kamonTestKit, scalatest),
    bintrayOrganization := Some("gwidx"),
    bintrayRepository := "maven",
    bintrayVcsUrl := Some("https://github.com/GlobalWebIndex/kamon-stackdriver.git"),
    crossScalaVersions := Seq("2.11.12", "2.12.8")
  )

val `kamon-logback-stackdriver` = project
  .settings(
    libraryDependencies ++= compileScope(kamon, kamonLogback, logbackClassic, googleCloudCore) ++ testScope(sprayJson, kamonTestKit, scalatest),
    bintrayOrganization := Some("gwidx"),
    bintrayRepository := "maven",
    bintrayVcsUrl := Some("https://github.com/GlobalWebIndex/kamon-stackdriver.git"),
    crossScalaVersions := Seq("2.11.12", "2.12.8")
  )
