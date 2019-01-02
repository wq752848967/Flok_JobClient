package per.test

import akka.actor.Actor
import akka.util.Timeout
import com.typesafe.config.ConfigFactory
import scala.concurrent.duration._
case object Init
case object SendNoReturn

class LocalActor  extends Actor{
  val path = ConfigFactory.defaultApplication().getString("remote.remote-actor.path")
  println("path:"+path)
  implicit val timeout = Timeout(4.seconds)
  val remoteActor = context.actorSelection(path)
  remoteActor!"local send hello!"
  println("in local actor print remote actor:"+remoteActor)
  override def receive: Receive = {
    case Init => "init local actor"
    case SendNoReturn => remoteActor ! "hello remote actor"
  }
}
