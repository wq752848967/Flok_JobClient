package per.test

import akka.actor.{ActorSystem, Props}
import akka.util.Timeout
import scala.concurrent.duration._
import com.typesafe.config.ConfigFactory
import akka.pattern.ask
import scala.concurrent.ExecutionContext.Implicits.global


case class Start()
case class ReadFile(inputPath:String,outputPath:String)
case class End()
object LocalMain extends App {


  val system = ActorSystem("LocalDemoSystem")


  //val localActorUsingIdentity = system.actorOf(Props[LocalActorUsingIdentity], name = "LocalActorUsingIdentity")
//  val localActor = system.actorOf(Props[LocalActor], name = "LocalActor")
//  println("send init")
//  localActor ! Init
//  println("send SendNoReturn")
//  localActor ! SendNoReturn
  val path = ConfigFactory.defaultApplication().getString("remote.remote-actor.path")
  implicit val timeout = Timeout(4.seconds)
  val remoteActor = system.actorSelection(path)
  println("start send"+path)
  val future = (remoteActor ? ReadFile("hdfs://192.168.35.31:9000/sl_task9_0601.csv","hdfs://192.168.35.31:9000/akka_write.csv"))
  println("send------ wait future")
  future.collect{
    case msg:String=>{
      println("server read and write file: "+msg)
    }
  }


}
