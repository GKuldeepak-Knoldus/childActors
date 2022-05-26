package com.knoldus.classicActor

import akka.actor.{ActorSystem, Props}
import com.knoldus.models.Models.StartChild

object Application extends App {
  val system = ActorSystem("ActorSystem")
  val parentActor = system.actorOf(Props[ParentActor], "Parent")
  parentActor ! StartChild("child", "hiii there")
  parentActor ! "What is this"
  parentActor ! "Start New Actor"
  parentActor ! StartChild("child1", "hiii there")
  parentActor ! "Hi"
  parentActor ! Seq.empty
  parentActor ! 0
  Thread.sleep(1000)
  parentActor ! "ASDFG"

}
