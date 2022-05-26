name := "Suprnation Excercise"

version := "0.1"

scalaVersion := "2.13.8"

run / mainClass := Some("org.exercise.Main")
assembly / mainClass := Some("org.exercise.Main")

assembly / assemblyJarName := "shortest_path.jar"

libraryDependencies ++= Seq(
  "org.scalactic" %% "scalactic" % "3.2.12",
  "org.scalatest" %% "scalatest" % "3.2.12" % "test"
)