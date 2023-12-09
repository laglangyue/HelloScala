package org.hello2.feature

import org.json4s.native.JsonMethods._
import org.json4s._

object ForYield2 {

  def main(args: Array[String]): Unit = {

    val json = parse(
      """
         { "name": "joe",
           "children": [
             {
               "name": "Mary",
               "age": 5
             },
             {
               "name": "Mazy",
               "age": 3
             }
           ]
         }
       """)

    val age = for {
      JObject(child) <- json
      JField("age", JInt(age)) <- child
    } yield age
    println(age)
  }
}
