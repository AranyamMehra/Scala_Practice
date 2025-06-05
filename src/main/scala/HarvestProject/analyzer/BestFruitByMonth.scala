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

      val updatedIncome = fruitMonthIncome.getOrElse(key, 0.0) + income
      fruitMonthIncome.update(key, updatedIncome)

      bestByMonth.updateWith(month) {
        case Some((_, bestIncome)) if updatedIncome > bestIncome => Some((record.fruit, updatedIncome))
        case Some(existing) => Some(existing)
        case None => Some((record.fruit, updatedIncome))
      }
    }
  }
  override def report(): Unit = {
    println()
    println("Best Fruit by month (based on Income):")
    println(bestByMonth.mkString("\n"))
  }
}
