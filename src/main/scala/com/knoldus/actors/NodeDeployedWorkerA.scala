package com.knoldus.actors

import akka.actor.Actor

class NodeDeployedWorkerA extends Actor{
  override def receive: Receive = {
    case message: Any =>
      println(s"Message received at ${self.path} message is -> ${message}")
  }
}
