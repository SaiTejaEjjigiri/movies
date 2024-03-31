package com.movieapi.movies;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import scala.Tuple2;

import java.util.Arrays;

public class SparkBatchProcess {

    public static void main(String[] args){
        SparkConf conf = new SparkConf().setAppName("SparkBatchProcess").setMaster("local");
        JavaSparkContext sc = new JavaSparkContext(conf);

        // Read input file
        JavaRDD<String> inputRDD = sc.textFile("input.txt");

        // Perform transformations
        JavaRDD<String> wordsRDD = inputRDD.flatMap(
                (FlatMapFunction<String, String>) line -> Arrays.asList(line.split(" ")).iterator());

        JavaRDD<String> wordPairsRDD = wordsRDD.mapToPair(word -> new Tuple2<>(word, 1))
                .reduceByKey((x, y) -> x + y)
                .map(pair -> pair._1 + ": " + pair._2);

        // Write results to output file
        wordPairsRDD.saveAsTextFile("output");

        // Stop Spark context
        sc.stop();

    }

}
