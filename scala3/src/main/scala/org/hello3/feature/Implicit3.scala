package org.hello3.feature

class Implicit3(using private val world: String):
  def hello() = {
    println(world)
  }

class Parent():

  private given world: String = "world"

  def hello() = {
    val implicit3 = new Implicit3()
    implicit3.hello()
  }


class Sun extends Parent:
  private given world: String = "sun"


class Sun2 extends Parent:
  private given world: String = "sun2"

  // would find world
  override def hello(): Unit = {
    super.hello()
    new Implicit3().hello()
  }

object Main {
  @main
  def main() = {
    Parent().hello()
    Sun().hello()
    Sun2().hello()
  }
}






