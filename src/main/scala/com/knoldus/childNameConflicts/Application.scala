package com.knoldus.childNameConflicts

import akka.actor.{ActorSystem, Props}
import com.knoldus.models.Models.Instantiate

object Application extends App {
  val system = ActorSystem("AS")
  val actor = system.actorOf(Props[ParentActor], "parent")
  actor ! Instantiate(5)
}
