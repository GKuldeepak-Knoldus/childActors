package com.knoldus.classicActor

import akka.actor
import akka.actor.SupervisorStrategy.{Escalate, Restart, Resume, Stop}
import akka.actor.ActorRef
import akka.actor.{Actor, OneForOneStrategy, Props, SupervisorStrategy}
import com.knoldus.models.Models.{OrderChild, StartChild}

class ParentActor extends Actor{
  override def receive: Receive = {
    case StartChild(name: String, message: String) =>
      println(s"[INFO] Instantiating Child with name ${name}")
      val childActor: actor.ActorRef = context.actorOf(Props[ChildActor], name)
      childActor forward message
      context.become(SentActorMessage(childActor))
  }

  def SentActorMessage(actorRef: ActorRef): Receive = {
    case message: Any => if(message.toString == "Start New Actor"){
      context.unbecome()
    } else  actorRef ! message

  }


  override val supervisorStrategy: SupervisorStrategy = OneForOneStrategy(){
    case _: NullPointerException => Restart
    case _: IllegalArgumentException => Stop
    case _: RuntimeException => Resume
    case _: Exception => Escalate
  }
}
