name := """my-first-app2"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  javaJdbc,
  javaJpa,
  "commons-collections" % "commons-collections" % "3.2.1",
  "org.hibernate" % "hibernate-entitymanager" % "3.6.9.Final",
  cache,
  javaWs,
  "redis.clients" % "jedis" % "2.5.1"
)
