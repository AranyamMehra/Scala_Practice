package HarvestProject.analyzer

import HarvestProject.{Analyzer, DateParser, HarvestRecord}
import java.time.LocalDate
import scala.collection.mutable

class bestGatherer extends Analyzer{
  def compute(harvestRecords: Iterator[HarvestRecord], priceMap: Map[(LocalDate, String), Double]): Unit = {

    val gathererIncome = mutable.Map[String, Double]()

    for (h <- harvestRecords) {
      priceMap.get((h.date, h.fruit)).foreach { price =>
        val income = h.amount * price
        gathererIncome.update(h.gatherer, gathererIncome.getOrElse(h.gatherer, 0.0) + income)
      }
    }

    println()
    if (gathererIncome.nonEmpty) {
      val (bestGatherer, income) = gathererIncome.maxBy(_._2)
      println(s"Best gatherer overall: $bestGatherer with total income: $income")
    } else {
      println("No data to calculate best gatherer overall.")
    }
  }
}