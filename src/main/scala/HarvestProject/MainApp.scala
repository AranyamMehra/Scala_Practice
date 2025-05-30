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

    analyzers.foreach { analyzer =>
      lazy val harvestRecords = HarvestParser.parse("src/main/scala/Data/harvest.csv")
      analyzer.compute(harvestRecords, priceMap)
    }
  }
}
