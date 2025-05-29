package HarvestProject

import scala.io.Source
import java.time.LocalDate

object PriceParser {
  def parse(path: String): Iterator[PriceRecord] = {
    val source = Source.fromFile(path)
    val lines = source.getLines().drop(1)
    lines.map { line =>
      val cols = line.split(",").map(_.trim)
      val date = DateParser.parsePriceDate(cols(1))
      PriceRecord(date, cols(0), cols(2).toDouble)
    }
  }

  val priceMap: Map[(LocalDate, String), Double] = parse("src/main/scala/Data/prices.csv").map(r => ((r.date, r.fruit), r.price)).toMap
}

