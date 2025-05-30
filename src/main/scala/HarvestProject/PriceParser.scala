package HarvestProject

import scala.io.Source
import java.time.LocalDate

object PriceParser {
  def parse(path: String): Iterator[PriceRecord] = {
    lazy val source = Source.fromFile(path)
    lazy val lines = source.getLines().drop(1)
    lines.map { line =>
      lazy val cols = line.split(",").map(_.trim)
      lazy val date = DateParser.parsePriceDate(cols(1))
      PriceRecord(date, cols(0), cols(2).toDouble)
    }
  }

  lazy val priceMap: Map[(LocalDate, String), Double] = parse("src/main/scala/Data/prices.csv").map(r => ((r.date, r.fruit), r.price)).toMap
}