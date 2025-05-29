package HarvestProject.analyzer

import java.time.LocalDate
import scala.collection.mutable
import HarvestProject.{Analyzer, DateParser, HarvestRecord}  // <-- Import the trait and other required classes

class BestFruitByMonth extends Analyzer {
  override def compute(harvestRecords: Iterator[HarvestRecord], priceMap: Map[(LocalDate, String), Double]): Unit = {
    val fruitIncomeByMonth = mutable.Map[(String, String), Double]()

    for (h <- harvestRecords) {
      priceMap.get((h.date, h.fruit)).foreach { price =>
        val income = h.amount * price
        val month = DateParser.formatToMonth(h.date)
        val key = (month, h.fruit)
        fruitIncomeByMonth.update(key, fruitIncomeByMonth.getOrElse(key, 0.0) + income)
      }
    }

    val grouped = fruitIncomeByMonth.toList.groupBy(_._1._1)
      .view.mapValues(_.maxBy(_._2)).toMap

    val best = grouped.map { case (month, ((_, fruit), income)) => s"$month: $fruit ($income)" }
    println("\nBest fruit by month:")
    println(best.mkString("\n"))
  }
}
