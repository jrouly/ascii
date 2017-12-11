import Dependencies._

name := "ascii"

lazy val commonSettings = Seq(
  organization := "net.rouly",
  version := "0.0.1",
  scalaVersion := "2.12.2"
)

lazy val root = (project in file("."))
  .settings(commonSettings)
  .aggregate(
    `ascii`,
    `ascii-play`
  )
  .settings(
    publish in Docker := {},
    publishArtifact := false
  )

lazy val `ascii` = project
  .settings(commonSettings)
  .settings(libraryDependencies ++= Seq(
    Common.scalaTest
  ))

lazy val `ascii-play` = project
  .enablePlugins(PlayScala)
  .enablePlugins(DockerPlugin)
  .dependsOn(`ascii`)
  .settings(commonSettings)
  .settings(libraryDependencies ++= Seq(
    Rouly.libCommon,
    Play26.libCommonServer,
    Common.logback,
    Common.macwireMacros,
    Common.macwireUtil,
    Common.scalaLogging,
    Play26.playJson,
    Play26.playServer,
    Play26.playTest
  ))
  .settings(dockerBaseImage := "openjdk:8-jre")
  .settings(dockerRepository := Some("jrouly"))
  .settings(dockerUpdateLatest := true)
