package org.hello2.demo

class ImplicitDemo(implicit val world: String) {
  def hello() = {
    println(world)
  }
}


class Parent {
  private implicit val world: String = "hello Parent"

  def print() = {
    val demo = new ImplicitDemo()
    demo.hello()
  }

}

class Sun extends Parent {
  implicit val world: String = "hello Sun"
}


object Main {
  def main(args: Array[String]): Unit = {
    val parent = new Parent()
    val sun = new Sun()
    parent.print()
    sun.print()
  }
}




