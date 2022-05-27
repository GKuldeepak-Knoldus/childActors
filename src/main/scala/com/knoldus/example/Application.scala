package com.knoldus.example

import akka.actor.{ActorSystem, Props}
import com.knoldus.models.Models.Instantiate

object Application extends App {

  val system = ActorSystem("Example")
  val master = system.actorOf(Props[Master], "Master")
  master ! Instantiate(3)
  val task = Seq("Hello", "Im in Knolx", "Session", "Scala Rocks", "Knoldus")
  task.foreach(text => master ! text)

}
