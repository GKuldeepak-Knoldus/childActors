package com.knoldus.actors

import akka.actor.{Actor, Props}

class ParentActors extends Actor {
  override def receive: Receive = {
    case message: Any =>
      val childActor = context.actorOf(Props[ChildActors], "child")
      childActor ! message
  }
}

class ChildActors extends Actor {
  override def receive: Receive = {
    case message: Any =>
      println(s"Yupppppy !!! received a message ${message}")
  }

}
