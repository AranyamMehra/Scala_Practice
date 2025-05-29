package HarvestProject.analyzer

import HarvestProject.{Analyzer, DateParser, HarvestRecord}
import scala.collection.mutable
import java.time.LocalDate

class bestAndWorstFruitOverall extends Analyzer {
  def compute (harvestRecords: Iterator[HarvestRecord], priceMap: Map[(LocalDate, String), Double]): Unit = {
    val fruitIncomeOverall = mutable.Map[String, Double]()

    for (h <- harvestRecords) {
      priceMap.get((h.date, h.fruit)).foreach { price =>
        val income = h.amount * price
        fruitIncomeOverall.update(h.fruit, fruitIncomeOverall.getOrElse(h.fruit, 0.0) + income)
      }
    }

    if (fruitIncomeOverall.nonEmpty) {
      val best = fruitIncomeOverall.maxBy(_._2)
      val worst = fruitIncomeOverall.minBy(_._2)
      println()
      println(s"Best fruit overall: ${best._1} with total income: ${best._2}")
      println(s"Worst fruit overall: ${worst._1} with total income: ${worst._2}")
    } else {
      println("No data to calculate best and worst fruit overall.")
    }
  }
}