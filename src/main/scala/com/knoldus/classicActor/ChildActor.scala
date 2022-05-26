package com.knoldus.classicActor

import akka.actor.{Actor, OneForOneStrategy, SupervisorStrategy}
import akka.actor.SupervisorStrategy.{Escalate, Restart, Resume, Stop}
import com.knoldus.models.Models.OrderChild

class ChildActor extends Actor{
  override def preStart(): Unit = {
    println(Console.GREEN + s"Starting Child Actor ${self.path}" + Console.RESET)
  }

  override def postStop(): Unit = {
    println(Console.GREEN + s"Stopping Child Actor ${self.path}" + Console.RESET)
  }

  override def preRestart(reason: Throwable, message: Option[Any]): Unit = {
    println(Console.GREEN + s"Restarting Child Actor ${self.path}" + Console.RESET)
  }

  override def postRestart(reason: Throwable): Unit = {
    println(Console.GREEN + s"Restarted Child Actor ${self.path}" + Console.RESET)
  }


  override def receive: Receive = {
    case OrderChild(message) =>
      println(s"[INFO] Received an message ${message} on address ${self.path}")
    case messages: Any =>
      messages match {
        case message: String if (message.length < 10) =>
          println("Send an exception RTE")
          throw new RuntimeException
        case message: Int if (message == 0) =>
          println("Send an exception IAE")
          throw new IllegalArgumentException
        case message: Seq[Any] if (message.isEmpty) =>
          println("Send an exception NPE")
          throw new NullPointerException
        case message: String if (message.length == 5) =>
          println("Send an exception")
          throw new Exception
        case message: Any =>
          println(s"Proceeding your process ! Kindly wait. Your process is ${message}")
      }
  }

}
