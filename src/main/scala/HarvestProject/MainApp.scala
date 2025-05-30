package HarvestProject
import HarvestProject.analyzer._

object MainApp
{
  def main(args: Array[String]): Unit =
  {
    val priceMap = PriceParser.priceMap

    val analyzers: List[Analyzer] = List(
      new BestFruitByMonth(),
      new BestGathererByMonth(),
      new WorstFruitByMonth(),
      new bestAndWorstFruitOverall(),
      new bestGatherer()
    )

    for (record <- HarvestParser.parse("src/main/scala/Data/harvest.csv")) {
      val price = priceMap.get((record.date, record.fruit))
      analyzers.foreach(_.compute(record, price))
    }
    analyzers.foreach(_.report())
  }
}