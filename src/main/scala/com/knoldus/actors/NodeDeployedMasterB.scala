package com.knoldus.actors

import akka.actor.{Actor, Props}

class NodeDeployedMasterB extends Actor{
  override def receive: Receive = {
    case message: Any =>
      message match {
        case msg: String if (msg.contains("/")) =>
          val actorRef = context.actorSelection(msg)
          actorRef ! s"Ping from ${self.path}"
        case _ =>
          val myChild = context.actorOf(Props[NodeDeployedWorkerB], "workerB")
          myChild ! message
      }
  }

}
