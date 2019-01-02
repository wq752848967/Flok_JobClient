package per.test;

import org.apache.spark.HashPartitioner;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.codehaus.janino.Java;
import scala.Tuple2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by wangqi on 2018/9/19.
 */
public class GroupByTest {
    public static void main(String[] args) {
        final SparkSession session = SparkSession.builder().appName("123")
                .master("local").getOrCreate();
        JavaPairRDD<String,Row> keyIdRdd= session.read().csv("/Users/wangqi/Desktop/FloK/data/data.csv").toJavaRDD().mapToPair(new PairFunction<Row, String, Row>() {
            public Tuple2<String, Row> call(Row row) throws Exception {

                //System.out.println(row);
                return new Tuple2<String, Row>(row.getString(0).split(";")[0],row);
            }
        });
       JavaRDD<List<Row>> resultRdd = keyIdRdd.groupByKey().map(new Function<Tuple2<String,Iterable<Row>>, List<Row>>() {
           public List<Row> call(Tuple2<String, Iterable<Row>> stringIterableTuple2) throws Exception {
               String key = stringIterableTuple2._1();
               List<Row> result = new ArrayList<Row>();
               for (Row item:stringIterableTuple2._2()){
                   System.out.println(key+"  "+item);
                   result.add(item);
               }
               return result;
           }
       });
       JavaRDD<Row> flatMap  = resultRdd.flatMap(new FlatMapFunction<List<Row>, Row>() {
           public Iterator<Row> call(List<Row> rows) throws Exception {

               return rows.iterator();
           }
       });
        System.out.println("hh"+flatMap.count());
    }

}
