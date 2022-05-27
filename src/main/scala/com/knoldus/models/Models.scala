package com.knoldus.models

object Models {

  final case class StartChild(name: String, message: String)

  final case class OrderChild(message: String)

  final case class Instantiate(number: Int)

}
