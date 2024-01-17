package org.hello2.feature

class ImplicitDemo(implicit val world: String) {
  def hello() = {
    println(world)
  }
}


class Parent {

  private implicit val world = "hello Parent"

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


case class Ints(ints: Array[Int])

object Method {
  def sums(ints1:Int*)(implicit ints: Ints)={
    ints.ints.sum+ints1.sum
  }
}

object Main1 {
  def main(args: Array[String]): Unit = {
    implicit val ints=Ints(Array(1,2,3))
    println(Method.sums(1))
  }
}



