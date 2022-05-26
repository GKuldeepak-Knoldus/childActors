package com.knoldus.actors

import akka.actor.{Actor, Props}

class NodeDeployedMasterA extends Actor{

  override def receive: Receive = {
    case message: Any =>
      println(s"Request Received at ${self.path}")
      message match {
        case msg: String if (msg.contains("/")) =>
          val actorRef = context.actorSelection(msg)
          actorRef ! s"Ping from ${self.path}"
        case _ =>
          val myChild = context.actorOf(Props[NodeDeployedWorkerA], "workerA")
          myChild ! message
      }
  }

}
