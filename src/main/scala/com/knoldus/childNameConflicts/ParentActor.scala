package com.knoldus.childNameConflicts

import akka.actor.{Actor, ActorLogging, ActorRef, Props}
import com.knoldus.models.Models.Instantiate

import scala.util.Random

class ParentActor extends Actor with ActorLogging{
  override def receive: Receive = {
    case Instantiate(number) =>
      println(s"[INFO] Instantiating Actor ${number}")
      val random = Random
      val childActors: IndexedSeq[ActorRef] = for(index <- 1 to  number) yield {
        val num = random.nextInt(number)
        val childName = s"child$num"
        context.actorOf(Props[ChildActor], childName)
      }
      childActors.foreach(name => println(name.path))
      Range(1, number).map {
        _ =>
          val actor = childActors(random.nextInt(number))
          actor ! s"Hi From ${self.path}"
      }

  }
}

class ChildActor extends Actor with ActorLogging {
  override def receive: Receive = {
    case message: String =>
      println(s"Message ${message} received at ${self.path}")
    case _ =>
      println("Unknown Entity !!!")
  }
}
