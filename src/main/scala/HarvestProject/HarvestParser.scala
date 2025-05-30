package HarvestProject

import scala.io.Source
import DateParser._

object HarvestParser {
  def parse(path: String): Iterator[HarvestRecord] = {
    lazy val source = Source.fromFile(path)
    lazy val lines = source.getLines().drop(1)
    lines.map { line =>
      lazy val cols = line.split(",").map(_.trim)
      lazy val date = parseHarvestDate(cols(1))
      HarvestRecord(date, cols(0), cols(2), cols(3).toDouble)
    }
  }
}