import sbt._
ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.3.1"

lazy val root = (project in file("."))
  .settings(
    name := "hello-scala",
    scalaVersion := "3.3.1")

lazy val subProject1 = (project in file("scala2"))
  .settings(
    name := "scala2",
    scalaVersion := "2.13.12",
    libraryDependencies += Dependencies.vertxDB2,
    // https://mvnrepository.com/artifact/org.json4s/json4s-core
    libraryDependencies += "org.json4s" %% "json4s-core" % "4.0.7",
    // https://mvnrepository.com/artifact/org.json4s/json4s-native
    libraryDependencies += "org.json4s" %% "json4s-native" % "4.0.7")

lazy val subProject2 = (project in file("scala3"))
  .settings(
    name := "scala3",
    scalaVersion := "3.3.1",
    libraryDependencies += Dependencies.vertxDB2,
    libraryDependencies += Dependencies.db2Driver,
    javacOptions ++= Seq("-encoding", "UTF-8"))
