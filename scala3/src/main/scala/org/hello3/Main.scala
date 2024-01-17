package org.hello3

import java.io.FileReader
import scala.util.Using

object Main {

  @main
  def main(): Unit = {
    Using(new FileReader("input.txt")) { reader =>
      // 使用reader读取文件
    }
  }
}
