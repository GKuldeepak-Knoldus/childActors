package com.knoldus.example

import akka.actor.Actor

class Worker extends Actor{
  override def receive: Receive = {
    case message: String =>
      println(s"Process received at ${self.path} to process >>> ${message}")
  }

}
