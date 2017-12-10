import Dependencies._

import com.typesafe.sbt.SbtScalariform.ScalariformKeys
import scalariform.formatter.preferences._

name := "ascii"

lazy val scalariformPreferences = FormattingPreferences()

lazy val commonSettings = Seq(
  organization := "net.rouly",
  version := "0.0.1",
  scalaVersion := "2.12.2",
  ScalariformKeys.preferences := scalariformPreferences
)

lazy val root = (project in file("."))
  .settings(commonSettings)
  .aggregate(
    `ascii`
  )

lazy val `ascii` = project
  .settings(commonSettings)
  .settings(libraryDependencies ++= Seq(
    Common.logback,
    Common.macwireMacros,
    Common.macwireUtil,
    Common.scalaLogging,
    Common.scalaTest
  ))
