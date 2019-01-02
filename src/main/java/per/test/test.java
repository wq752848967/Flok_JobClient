package per.test;

import org.apache.spark.HashPartitioner;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import scala.Array;
import scala.Tuple2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by wangqi on 2018/9/14.
 */
public class test {
    public static void main(String[] args) {


//        SparkSession session = SparkSession.builder().appName("123")
//                .master("local").getOrCreate();
//        JavaPairRDD<String,Row> keyIdRdd= session.read().csv("/Users/wangqi/Desktop/FloK/data/data.csv").toJavaRDD().mapToPair(new PairFunction<Row, String, Row>() {
//            public Tuple2<String, Row> call(Row row) throws Exception {
//
//                return new Tuple2<String, Row>(row.getString(0).split(";")[0],row);
//            }
//        });
//        System.out.println(keyIdRdd.count());
//        JavaPairRDD<String,Row> partitionRdd = keyIdRdd.partitionBy(new HashPartitioner(4));
//
//        JavaRDD<Integer> result = partitionRdd.mapPartitions(new FlatMapFunction<Iterator<Tuple2<String,Row>>, Integer>() {
//            public Iterator<Integer> call(Iterator<Tuple2<String, Row>> tuple2Iterator) throws Exception {
//                while(tuple2Iterator.hasNext()){
//                    Row row = tuple2Iterator.next()._2();
//                    System.out.println(row.getString(0));
//                }
//                System.out.println("this is a partition@@@@@@@@@");
//                List<Integer> re = new ArrayList<Integer>();
//                re.add(1);
//                return re.iterator();
//            }
//        });
//        System.out.println(result.count());
        System.out.println(8&1);
    }
}
