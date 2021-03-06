val circeVersion = "0.14.1"

name := "Circe"

version := "0.1"

scalaVersion := "2.13.3"

libraryDependencies ++= Seq(
	"io.circe" %% "circe-core",
	"io.circe" %% "circe-generic",
	"io.circe" %% "circe-parser"
).map(_ % circeVersion)

