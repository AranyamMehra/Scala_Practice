package HarvestProject

object MainApp {
  def main(args: Array[String]): Unit = {
    val harvest = CsvReader.readFile("src/main/scala/Data/harvest.csv")
    val prices = CsvReader.readFile("src/main/scala/Data/prices.csv")

    val harvestRecords = CsvReader.parseHarvest(harvest)
    val priceRecords = CsvReader.parsePrices(prices)

    val joined = Analyzer.joinRecords(harvestRecords, priceRecords)

    Analyzer.bestGathererByMonth(joined)
    Analyzer.bestFruitByMonth(joined)
    Analyzer.worstFruitByMonth(joined)
    Analyzer.bestAndWorstFruitOverall(joined)
    Analyzer.bestGatherer(joined)
  }
}
