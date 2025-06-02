package HarvestProject.analyzer

import scala.collection.mutable
import HarvestProject.{Analyzer, DateParser, HarvestRecord}

class BestFruitByMonth extends Analyzer {
  private val fruitIncomeByMonth = mutable.Map[(String, String), Double]()

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
      .groupBy(_._1._1)
      .view.mapValues(_.maxBy(_._2))
      .map { case (month, ((_, fruit), income)) => s"$month: $fruit ($income)" }

    val worstByMonth = fruitIncomeByMonth
      .groupBy(_._1._1)
      .view.mapValues(_.minBy(_._2))
      .map { case (month, ((_, fruit), income)) => s"$month: $fruit ($income)" }

    println("\nBest fruit by month:")
    println(bestByMonth.mkString("\n"))
    println ()
    println("\nWorst fruit by month:")
    println(worstByMonth.mkString("\n"))
  }
}