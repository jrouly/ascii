import sbt._

object Dependencies {

  val enumeratumVersion = "1.5.12"
  val macwireVersion = "2.3.0"

  object Common {

    lazy val enumeratum = "com.beachape" %% "enumeratum" % enumeratumVersion
    lazy val logback = "ch.qos.logback" % "logback-classic" % "1.2.3"
    lazy val macwireMacros = "com.softwaremill.macwire" %% "macros" % macwireVersion
    lazy val macwireUtil = "com.softwaremill.macwire" %% "util" % macwireVersion
    lazy val scalaLogging = "com.typesafe.scala-logging" %% "scala-logging" % "3.7.2"
    lazy val scalaTest = "org.scalatest" %% "scalatest" % "3.0.1" % "test"

  }

}
