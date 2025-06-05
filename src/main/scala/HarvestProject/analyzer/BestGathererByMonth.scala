package HarvestProject.analyzer

import HarvestProject.{Analyzer, DateParser, HarvestRecord}
import scala.collection.mutable

class BestGathererByMonth extends Analyzer {
  private val gathererMonthAmount = mutable.Map[(String, String), Double]()
  private val bestByMonth = mutable.Map[String, (String, Double)]()

  override def compute(record: HarvestRecord, priceOpt: Option[Double]): Unit = {
    val month = DateParser.formatToMonth(record.date)
    val key = (month, record.gatherer)
    val amount = gathererMonthAmount.getOrElse(key, 0.0) + record.amount
    gathererMonthAmount.update(key, amount)

    val currentBest = bestByMonth.get(month)
    bestByMonth.update(
      month, currentBest match {
        case Some((_, bestAmount)) if amount > bestAmount => (record.gatherer, amount)
        case Some(_) => currentBest.get
        case None => (record.gatherer, amount)
      }
    )
  }

  override def report(): Unit = {
    println()
    println("Best gatherer by month (based on total amount):")
    println(bestByMonth.mkString("\n"))
  }
}