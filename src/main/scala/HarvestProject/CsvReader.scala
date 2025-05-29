//package HarvestProject
//import scala.io.Source
//
//object CsvReader {
//  def readFile(path: String): List[String] = Source.fromFile(path).getLines().drop (1).toList
//
//  def parseHarvest(lines: List[String]): List[HarvestRecord] = {
//    val data = lines
//    data.map { line =>
//      val cols = line.split(",").map(_.trim)
//      val date = DateParser.parseHarvestDate(cols(1))
//      HarvestRecord(date, cols(0), cols(2), cols(3).toDouble)
//    }
//  }
//
//  def parsePrices(lines: List[String]): List[PriceRecord] = {
//    val data = lines
//    data.map { line =>
//      val cols = line.split(",").map(_.trim)
//      val date = DateParser.parsePriceDate(cols(1))
//      PriceRecord(date, cols(0), cols(2).toDouble)
//    }
//  }
//}
