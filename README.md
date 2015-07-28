# MongoDB-ElasticSearch-ETL
## Synopsis
Generic template to read MongoDB and migrate to ElasticSearch

## Requirements
* mongodb-driver-3.0.2.jar
* bson-3.0.2.jar
* mongodb-driver-core-3.0.2.jar
* mongo-hadoop-core-1.4.0.jar
* elasticsearch-spark_2.10-2.1.0.jar
Jar files can be downloaded here:
	<https://www.elastic.co/guide/en/elasticsearch/hadoop/current/spark.html>
	<http://mongodb.github.io/mongo-java-driver/3.0/driver/getting-started/installation-guide/>
	<https://github.com/mongodb/mongo-hadoop/releases>

## Run
	spark-submit \
	--class "mongodb_es_etl" \
	--master yarn-cluster \
	--driver-library-path /usr/lib/hadoop/lib/native \
	--jars mongodb-driver-3.0.2.jar,bson-3.0.2.jar,mongodb-driver-core-3.0.2.jar,mongo-hadoop-core-1.4.0.jar,elasticsearch-spark_2.10-2.1.0.jar \
	target/scala-2.10/mongodb_es_etl_2.10-1.0.jar