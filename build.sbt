ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.3.1"

lazy val root = (project in file("."))
  .settings(
    name := "hello-scala",
    scalaVersion := "3.3.1")

lazy val subProject1 = (project in file("scala2"))
  .settings(
    name := "scala2",
    scalaVersion := "2.13.12")

lazy val subProject2 = (project in file("scala3"))
  .settings(
    name := "scala3",
    scalaVersion := "3.3.1")
