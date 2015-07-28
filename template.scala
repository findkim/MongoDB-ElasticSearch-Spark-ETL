import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf

import org.apache.hadoop.conf.Configuration
import org.bson.BSONObject
import org.bson.BasicBSONObject
import org.elasticsearch.spark.sql._

object mongodb_es_etl {
	def main(args: Array[String]) {

		val conf = new org.apache.spark.SparkConf().setAppName("mongodb_es_etl")
		val sc = new SparkContext(conf)
		val sqlContext = new org.apache.spark.sql.SQLContext(sc)

		// Set Mongo configuration
		val config = new Configuration()
		config.set("mongo.input.uri", "mongodb://host:port/db.collection")
		config.set("mongo.job.input.format", "com.mongodb.hadoop.MongoInputFormat")

		// Read Mongo database
		val mongoRDD = sc.newAPIHadoopRDD(config, classOf[com.mongodb.hadoop.MongoInputFormat], classOf[Object], classOf[BSONObject])

		// Convert BSON to JSON
		val bsonRDD = mongoRDD.map(x => x._2)	// Array[(Object, org.bson.BSONObject)] --> Array[org.bson.BSONObject]
		val jsonStringRDD = bsonRDD.map(x => x.toString())	// org.apache.spark.rdd.RDD[org.bson.BSONObject] --> org.apache.spark.rdd.RDD[String]
		val jsonRDD = sqlContext.jsonRDD(jsonStringRDD)	// org.apache.spark.rdd.RDD[String] --> org.apache.spark.sql.DataFrame
		val jsonRDD_mod = jsonRDD.withColumnRenamed("_id", "mongoDB_id")	// modify Mongo column name to avoid conflict with ES

		// Write to ES with auto indexing
		jsonRDD_mod.saveToEs("index/type", Map("es.nodes" -> "xx.x.xx.xx", "es.index.auto.create" -> "true", "es.nodes.client.only" -> "true", "es.mapping.date.rich" -> "false"))
	}
}