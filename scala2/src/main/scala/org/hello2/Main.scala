package org.hello2

import java.io.File
import scala.io.{Codec, Source}
import scala.util.control.Exception.ultimately


/**
 */
object Main {


  /**
   * @param args
   */
  def main(args: Array[String]): Unit = {
    implicit val enc: Codec = Codec.UTF8
    scala.util.Either
    ultimately(Source.fromFile(new File("sss.text"))) {

    }
    val source = Source.fromFile(new File("sss.text"))
    val reader = source.bufferedReader()
    reader.lines().takeWhile(_ eq null).toArray.mkString("")
    reader.close()
  }
}

