ThisBuild / scalaVersion := "3.1.1"
ThisBuild / organization := "com.alwayswantedtoplay"

lazy val scramscala = (project in file("."))
  .settings(
    name := "scramscala",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.11" % "test",
  )

