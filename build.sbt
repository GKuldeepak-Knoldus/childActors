ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.8"

lazy val root = (project in file("."))
  .settings(
    name := "childActors"
  )

libraryDependencies ++= Seq("com.typesafe.akka" %% "akka-actor" % "2.6.19",
   "com.typesafe.akka" %% "akka-actor-typed" % "2.6.19"
)

