package HarvestProject.analyzer

import scala.collection.mutable
import HarvestProject.{Analyzer, DateParser, HarvestRecord}

class BestFruitByMonth extends Analyzer {
  private val fruitMonthIncome = mutable.Map[(String, String), Double]()
  private val bestByMonth = mutable.Map[String, (String, Double)]()

  override def compute(record: HarvestRecord, priceOpt: Option[Double]): Unit = {
    priceOpt.foreach { price =>
      val month = DateParser.formatToMonth(record.date)
      val key = (month, record.fruit)
      val income = record.amount * price

      val totalIncome = fruitMonthIncome.getOrElse(key, 0.0) + income
      fruitMonthIncome.update(key, totalIncome)

      val currentBest = bestByMonth.get(month)
      bestByMonth.update(
        month,
        currentBest match {
          case Some((_, bestIncome)) if totalIncome > bestIncome => (record.fruit, totalIncome)
          case Some(_) => currentBest.get
          case None => (record.fruit, totalIncome)
        }
      )
    }
  }

  override def report(): Unit = {
    println()
    println("Best fruit by month (based on total income):")
    println(bestByMonth.mkString("\n"))
  }
}
