package com.knoldus.actors

import akka.actor.{ActorSystem, Props}
object Application extends App {

  val system =  ActorSystem("ActorSystem")

/*  val parent = system.actorOf(Props[ParentActors], "parent")
  parent ! "I am Parent"
  val child = system.actorSelection("/user/parent/child")
  child ! "But I am child"*/

  val masterA = system.actorOf(Props[NodeDeployedMasterA], "masterA")
  val masterB = system.actorOf(Props[NodeDeployedMasterB], "masterB")
  masterA ! "Hello !!! Master A"
  Thread.sleep(1000)
  masterB ! "Hello !!! Master B"
  Thread.sleep(1000)
  masterA ! "/user/masterB/workerB"
  Thread.sleep(1000)
  masterB ! "/user/masterA/workerA"
  Thread.sleep(1000)
  masterA ! "/user/masterB/workerA"
  Thread.sleep(1000)
  masterA ! "/parent/A"





}
