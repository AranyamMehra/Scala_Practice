package HarvestProject.analyzer

import HarvestProject.{Analyzer, DateParser, HarvestRecord}
import scala.collection.mutable

class bestGatherer extends Analyzer {
  private val gathererIncome = mutable.Map[String, Double]()

  override def compute(record: HarvestRecord, price: Option[Double]): Unit = {
    price.foreach { p =>
      val income = record.amount * p
      gathererIncome.update(
        record.gatherer,
        gathererIncome.getOrElse(record.gatherer, 0.0) + income
      )
    }
  }

  override def report(): Unit = {
    println()
    if (gathererIncome.nonEmpty) {
      val (bestGatherer, income) = gathererIncome.maxBy(_._2)
      println(s"Best gatherer overall: $bestGatherer with total income: $income")
    } else {
      println("No data to calculate best gatherer overall.")
    }
  }
}