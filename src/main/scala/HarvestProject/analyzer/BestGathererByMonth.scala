package HarvestProject.analyzer

import HarvestProject.{Analyzer, DateParser, HarvestRecord}
import scala.collection.mutable

class BestGathererByMonth extends Analyzer {
  private val amountMap = mutable.Map[(String, String), Double]()

  override def compute(record: HarvestRecord, priceOpt: Option[Double]): Unit = {
    val month = DateParser.formatToMonth(record.date)
    val key = (month, record.gatherer)
    val amount = record.amount
    amountMap.update(key, amountMap.getOrElse(key, 0.0) + amount)
  }
  override def report(): Unit = {
    val bestByMonth = amountMap.groupBy(_._1._1).view
      .mapValues(_.maxBy(_._2)).toMap
      .map { case (month, ((_, gatherer), income)) => (month, (gatherer, income)) }
    println ()
    println("Best gatherer by month:")
    println(bestByMonth.mkString("\n"))
  }
}