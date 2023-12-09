package org.hello3.jdbc

object VertxDb2 {

  @main
  def client(): Unit = {
    import io.vertx.db2client.DB2Builder
    import io.vertx.db2client.DB2ConnectOptions
    import io.vertx.sqlclient.PoolOptions
    val connectOptions = new DB2ConnectOptions()
      .setPort(50000)
      .setHost("localhost")
      .setDatabase("testdb")
      .setUser("DB2INST1")
      .setPassword("root")

    val poolOptions = new PoolOptions().setMaxSize(5)

    // Create the pooled client
    val client = DB2Builder.pool
      .`with`(poolOptions)
      .connectingTo(connectOptions)
      .build

    client
      .query("SELECT id,c_blob FROM DB2INST1.EMPLOYEES")
      .execute()
      .onComplete(ar => {
        if ar.succeeded() then
          val result = ar.result
          println(s"row.size() = ${result.size()}")
        else
          ar.cause().printStackTrace()
          println("Failure: " + ar.cause().getMessage)
        // Now close the pool
        client.close();
      })
  }
}



object jdbc{

  @main
  def conn()={
    import java.nio.charset.StandardCharsets
    import java.sql.Connection
    import java.sql.DriverManager
    val url = "jdbc:db2://localhost:50000/testdb"
    val username = "DB2INST1"
    val password = "root"

    // 注册DB2 JDBC驱动程序
    Class.forName("com.ibm.db2.jcc.DB2Driver")

    // 建立数据库连接
    val connection: Connection = DriverManager.getConnection(url, username, password)
    val bytes = "hello world".getBytes(StandardCharsets.UTF_8)
    try {
      import java.io.ByteArrayInputStream
      import java.io.InputStreamReader
      import java.sql.PreparedStatement
      // 执行SQL查询
      val sql = "INSERT INTO DB2INST1.EMPLOYEES (ID, NAME, AGE, SALARY, C_BLOB, C_CLOB) VALUES (?, ?, ?, ?, ?, ?)"
      val statement: PreparedStatement = connection.prepareStatement(sql)
      // 处理查询结果

      val id = 2
      val name = "John Doe"
      val age = 30
      val salary = 5000
      val textStream = getClass.getResourceAsStream("/big.txt")

      val blobData = new ByteArrayInputStream("hello".getBytes(StandardCharsets.UTF_8)) // Replace with your BLOB data
      val clobData = new InputStreamReader(new ByteArrayInputStream("Some text".getBytes())) // Replace with your CLOB data


      statement.setInt(1, id)
      statement.setString(2, name)
      statement.setInt(3, age)
      statement.setDouble(4, salary)
      statement.setBlob(5, textStream)
      statement.setClob(6, clobData)

      statement.executeUpdate()
    } catch {
      case e: Exception => e.printStackTrace()
    } finally {
      // 关闭连接
      connection.close()
    }
  }

  @main
  def write()={
    import java.io.BufferedWriter
    import java.io.FileWriter
    val filePath = "/home/laglang/IdeaProjects/HelloScala/scala3/src/main/resources/big.txt"
    val writer = new BufferedWriter(new FileWriter(filePath))
    val numLines = 10000
    try {
      for (i <- 1 to numLines) {
        writer.write(s"This is line $i\n")
      }
    } finally {
      writer.close()
    }

  }
}
