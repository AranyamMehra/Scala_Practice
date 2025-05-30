package HarvestProject.analyzer

import java.time.LocalDate
import scala.collection.mutable
import HarvestProject.{Analyzer, DateParser, HarvestRecord}

class BestFruitByMonth extends Analyzer {
  private val fruitIncomeByMonth = scala.collection.mutable.Map[(String, String), Double]() // (month, fruit) -> income

  override def compute(record: HarvestRecord, price: Option[Double]): Unit = {
    price.foreach { p =>
      val income = record.amount * p
      val month = DateParser.formatToMonth(record.date)
      val key = (month, record.fruit)
      fruitIncomeByMonth.update(key, fruitIncomeByMonth.getOrElse(key, 0.0) + income)
    }
  }

  override def report(): Unit = {
    val bestByMonth = fruitIncomeByMonth
      .groupBy(_._1._1) // group by month
      .view.mapValues(_.maxBy(_._2)) // best fruit by income
      .map { case (month, ((_, fruit), income)) => s"$month: $fruit ($income)" }

    println("\nBest fruit by month:")
    println(bestByMonth.mkString("\n"))

  }
}
