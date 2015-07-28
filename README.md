# MongoDB-ElasticSearch-ETL
Generic template to read MongoDB and migrate to ElasticSearch

Requirements:
	mongodb-driver-3.0.2.jar
	bson-3.0.2.jar
	mongodb-driver-core-3.0.2.jar
	mongo-hadoop-core-1.4.0.jar
	elasticsearch-spark_2.10-2.1.0.jar

spark-submit --class "mongodb_es_etl" --master yarn-cluster --driver-library-path /usr/lib/hadoop/lib/native --jars mongodb-driver-3.0.2.jar,bson-3.0.2.jar,mongodb-driver-core-3.0.2.jar,mongo-hadoop-core-1.4.0.jar,elasticsearch-spark_2.10-2.1.0.jar target/scala-2.10/mongodb_es_etl_2.10-1.0.jar