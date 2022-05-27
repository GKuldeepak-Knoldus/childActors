package com.knoldus.example

import akka.actor.{Actor, ActorLogging, ActorRef, Props}
import com.knoldus.models.Models.Instantiate

class Master extends Actor {
  override def receive: Receive = {
    case Instantiate(number) =>
      println(s"Received request at ${self.path} to instantiate ${number} workers !")
      val workerActorRefs = for (index <- 1 to number) yield context.actorOf(Props[Worker], s"worker$index")
      context.become(StartSendingWorkerProcess(workerActorRefs, 0))
  }

  def StartSendingWorkerProcess(actor: IndexedSeq[ActorRef], currentWorker: Int): Receive = {
    case task: String =>
      val workerSelected = actor(currentWorker) ! task
      val nextWorker = (currentWorker + 1) % actor.length
      context.become(StartSendingWorkerProcess(actor, nextWorker))
  }
}


