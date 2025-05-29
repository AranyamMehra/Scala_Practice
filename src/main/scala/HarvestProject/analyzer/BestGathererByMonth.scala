package HarvestProject.analyzer
import HarvestProject.{Analyzer, DateParser, HarvestRecord}
import java.time.LocalDate
import scala.collection.mutable

class BestGathererByMonth extends Analyzer {
  override def compute(harvestRecords: Iterator[HarvestRecord], priceMap: Map[(LocalDate, String), Double]): Unit = {
    val incomeMap = mutable.Map[(String, String), Double]()

    for (h <- harvestRecords) {
      priceMap.get((h.date, h.fruit)).foreach { price =>
        val month = DateParser.formatToMonth(h.date)
        val key = (month, h.gatherer)
        val income = h.amount * price
        incomeMap.update(key, incomeMap.getOrElse(key, 0.0) + income)
      }
    }

    val bestByMonth = incomeMap.groupBy(_._1._1).view.mapValues(_.maxBy(_._2)).toMap
      .map { case (month, ((_, gatherer), income)) => (month, (gatherer, income)) }
    println()
    println("Best gatherer by month:")
    println(bestByMonth.mkString("\n"))
  }
}
