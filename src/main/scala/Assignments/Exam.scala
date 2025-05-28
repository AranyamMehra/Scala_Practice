package Assignments

import java.io.PrintWriter
import scala.io.Source

object Exam extends App{
  val filePath = "src/main/scala/Data/exam_data.csv"
  val out = "src/main/scala/Data/exam_out.csv"

  val lines = Source.fromFile(filePath).getLines().toList
  val results = lines.map {line =>
    val col = line.split (",").map (_.trim.toInt)

    val result = if (col (2) >= col (0) * col (1)) "Yes" else "No"
    s"$line, $result"
  }

  val writer = new PrintWriter (out)
  results.foreach(writer.println)
  writer.close()

  results.foreach(println)
}


