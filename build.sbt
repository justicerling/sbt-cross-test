name := "sbt-cross-compiling-test"

version := "0.1"

//scalaVersion := "2.12.8"

val scala_2_11 = "2.11.8"
val scala_2_12 = "2.12.8"
/* module common*/
lazy val common = project.in(file("common")).cross
lazy val common_2_11 = common(scala_2_11).withId("common_2_11")
lazy val common_2_12 = common(scala_2_12).withId("common_2_12")
/* module Boo*/
lazy val boo = project.in(file("boo"))
        .dependsOn(common_2_11)
        .settings(
            scalaVersion := scala_2_11
        )

/* module Foo*/
lazy val foo = project.in(file("foo"))
        .dependsOn(common_2_11)
        .settings(
            scalaVersion := scala_2_12
        )
lazy val root = Project("root", file("."))
        .aggregate(boo, foo)
