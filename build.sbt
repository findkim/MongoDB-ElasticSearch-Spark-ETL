name := "mongodb_es_etl"

version := "1.0"

scalaVersion := "2.10.4"

libraryDependencies += "org.apache.spark" %% "spark-core" % "1.3.0"

libraryDependencies ++= Seq(
"org.elasticsearch" % "elasticsearch-spark_2.10" % "2.1.0",
"org.mongodb" % "mongo-java-driver" % "3.0.2",
"org.mongodb.mongo-hadoop" % "mongo-hadoop-core" % "1.4.0"
)