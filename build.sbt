
scalaVersion := "2.12.15"

fork := true

Compile / run / javaOptions += "-Dlog4j.configurationFile=log4j2.properties"

name := "arson-jobs"
organization := "ch.epfl.scala"
version := "1.0"

lazy val sparkVersion = "3.5.0"

libraryDependencies ++= Seq(
    "org.apache.spark"          %% "spark-core"                               % sparkVersion,
    "org.apache.spark"          %% "spark-sql"                                % sparkVersion,
    // "org.apache.spark"          %% "spark-mllib"                              % sparkVersion,
    // "org.apache.spark"          %% "spark-graphx"                             % sparkVersion,
    // "org.apache.spark"          %% "spark-streaming"                          % sparkVersion,
    // "com.github.potix2"         %% "spark-google-spreadsheets"                % "0.6.3",
    "com.lihaoyi"               %% "requests"                                 % "0.8.0"
)

// Here, `libraryDependencies` is a set of dependencies, and by using `+=`,
// we're adding the scala-parser-combinators dependency to the set of dependencies
// that sbt will go and fetch when it starts up.
// Now, in any Scala file, you can import classes, objects, etc., from
// scala-parser-combinators with a regular import.

// TIP: To find the "dependency" that you need to add to the
// `libraryDependencies` set, which in the above example looks like this:

// "org.scala-lang.modules" %% "scala-parser-combinators" % "2.3.0"

// You can use Scaladex, an index of all known published Scala libraries. There,
// after you find the library you want, you can just copy/paste the dependency
// information that you need into your build file. For example, on the
// scala/scala-parser-combinators Scaladex page,
// https://index.scala-lang.org/scala/scala-parser-combinators, you can copy/paste
// the sbt dependency from the sbt box on the right-hand side of the screen.

// IMPORTANT NOTE: while build files look _kind of_ like regular Scala, it's
// important to note that syntax in *.sbt files doesn't always behave like
// regular Scala. For example, notice in this build file that it's not required
// to put our settings into an enclosing object or class. Always remember that
// sbt is a bit different, semantically, than vanilla Scala.

// ============================================================================

// Most moderately interesting Scala projects don't make use of the very simple
// build file style (called "bare style") used in this build.sbt file. Most
// intermediate Scala projects make use of so-called "multi-project" builds. A
// multi-project build makes it possible to have different folders which sbt can
// be configured differently for. That is, you may wish to have different
// dependencies or different testing frameworks defined for different parts of
// your codebase. Multi-project builds make this possible.

// Here's a quick glimpse of what a multi-project build looks like for this
// build, with only one "subproject" defined, called `root`:

// lazy val root = (project in file(".")).
//   settings(
//     inThisBuild(List(
//       organization := "ch.epfl.scala",
//       scalaVersion := "2.13.12"
//     )),
//     name := "hello-world"
//   )

// To learn more about multi-project builds, head over to the official sbt
// documentation at http://www.scala-sbt.org/documentation.html
