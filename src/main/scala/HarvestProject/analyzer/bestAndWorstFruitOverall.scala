package HarvestProject.analyzer

import HarvestProject.{Analyzer, HarvestRecord}
import scala.collection._
class bestAndWorstFruitOverall extends Analyzer {
  private val fruitIncomeOverall = mutable.Map[String, Double]()

  override def compute(record: HarvestRecord, price: Option[Double]): Unit = {
    price.foreach { p =>
      val income = record.amount * p
      fruitIncomeOverall.update(
        record.fruit,
        fruitIncomeOverall.getOrElse(record.fruit, 0.0) + income
      )
    }
  }

  override def report(): Unit = {
    println()
    if (fruitIncomeOverall.nonEmpty) {
      val best = fruitIncomeOverall.maxBy(_._2)
      val worst = fruitIncomeOverall.minBy(_._2)
      println(s"Best fruit overall: ${best._1} with total income: ${best._2}")
      println(s"Worst fruit overall: ${worst._1} with total income: ${worst._2}")
    } else {
      println("No data to calculate best and worst fruit overall.")
    }
  }
}