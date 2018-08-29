import sbt.Keys.libraryDependencies

name := "quandl_finance_data_processing"

version := "0.1"

scalaVersion := "2.12.6"



libraryDependencies ++= Seq(
  "javax.activation" % "activation" % "1.1.1",
  "com.jimmoores" % "quandl-core" % "2.0.0",
  "com.jimmoores" % "quandl-tablesaw" % "2.0.0",
  "org.slf4j" % "slf4j-simple" % "1.8.0-beta2",
  "org.scalanlp" %% "breeze" % "1.0-RC2",
  "org.scalanlp" %% "breeze-natives" % "1.0-RC2"
)

