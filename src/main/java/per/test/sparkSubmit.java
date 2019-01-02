package per.test;

import org.apache.spark.launcher.SparkAppHandle;
import org.apache.spark.launcher.SparkLauncher;

import java.util.concurrent.CountDownLatch;

/**
 * Created by wangqi on 2018/9/13.
 */
public class sparkSubmit {

    public static void main(String[] args) throws Exception{
        final CountDownLatch  countDownLatch = new CountDownLatch(1);
        SparkAppHandle handler =  new SparkLauncher()
                .setSparkHome("/home/fit/spark-2.1.1-bin-hadoop2.7")
                .setAppResource("/home/fit/data/spark-art-1.0-SNAPSHOT-jar-with-dependencies.jar")
                .setMainClass("per.test.TestMain")
                .setMaster("spark://192.168.15.234:7077")
                .setDeployMode("client")
                .setConf("spark.app.id", "launcher_1314")
                .setConf("spark.driver.memory", "2g")
                .setConf("spark.executor.memory", "1g")
                .setConf("spark.executor.instances", "4")
                .setConf("spark.executor.cores", "2")
                .setVerbose(true).startApplication(new SparkAppHandle.Listener() {

                    public void stateChanged(SparkAppHandle sparkAppHandle) {
                        if (sparkAppHandle.getState().toString()=="RUNNING"){
                            countDownLatch.countDown();
                        }
                        System.out.println(sparkAppHandle.getAppId()+" :state:" + sparkAppHandle.getState().toString());
                    }

                    public void infoChanged(SparkAppHandle sparkAppHandle) {
                        System.out.println("Info:" + sparkAppHandle.getState().toString());
                    }
                });

        countDownLatch.await();
        System.out.println("The task is executing, please wait ....");




    }
}
