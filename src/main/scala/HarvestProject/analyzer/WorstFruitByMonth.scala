package HarvestProject.analyzer

import scala.collection.mutable
import HarvestProject.{Analyzer, DateParser, HarvestRecord}

class WorstFruitByMonth extends Analyzer {
  private val fruitMonthIncome = mutable.Map[(String, String), Double]()

  override def compute(record: HarvestRecord, priceOpt: Option[Double]): Unit = {
    priceOpt.foreach { p =>
      val income = record.amount * p
      val month = DateParser.formatToMonth(record.date)
      val key = (month, record.fruit)
      fruitMonthIncome.update(key, fruitMonthIncome.getOrElse(key, 0.0) + income)
    }
  }

  def report(): Unit = {
    val worstByMonth = fruitMonthIncome.groupBy(_._1._1).view.mapValues(_.minBy(_._2))
      .map {
        case (month, ((_, fruit), income)) => s"$month: $fruit ($income)"
      }
    println()
    println("Worst Fruit by month (based on Income):")
    println(worstByMonth.mkString("\n"))
  }
}