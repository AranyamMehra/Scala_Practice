//package HarvestProject.analyzer
//import HarvestProject.{Analyzer, DateParser, HarvestRecord}
//import scala.collection.mutable
//
//class WorstFruitByMonth extends Analyzer{
//  private val fruitIncomeByMonth = mutable.Map[(String, String), Double]()
//
//  override def compute(record: HarvestRecord, price: Option[Double]): Unit = {
//    price.foreach { p =>
//      val income = record.amount * p
//      val month = DateParser.formatToMonth(record.date)
//      val key = (month, record.fruit)
//      fruitIncomeByMonth.update(key, fruitIncomeByMonth.getOrElse(key, 0.0) + income)
//    }
//  }
//
//  override def report(): Unit = {
//    val worstByMonth = fruitIncomeByMonth
//      .groupBy(_._1._1)
//      .view.mapValues(_.minBy(_._2))
//      .map { case (month, ((_, fruit), income)) => s"$month: $fruit ($income)" }
//
//    println("\nWorst fruit by month:")
//    println(worstByMonth.mkString("\n"))
//  }
//}