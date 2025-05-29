package HarvestProject
import java.time.LocalDate

trait Analyzer {
  def compute(harvestRecords: Iterator[HarvestRecord], priceMap: Map[(LocalDate, String), Double]): Unit
}

//object Analyzer {
//
//  def bestGathererByMonth(harvestRecords: Iterator[HarvestRecord], priceMap: Map[(LocalDate, String), Double]): Unit = {
//    val incomeMap = scala.collection.mutable.Map[(String, String), Double]()
//
//    for (h <- harvestRecords) {
//      priceMap.get((h.date, h.fruit)).foreach { price =>
//        val month = DateParser.formatToMonth(h.date)
//        val key = (month, h.gatherer)
//        val income = h.amount * price
//        incomeMap.update(key, incomeMap.getOrElse(key, 0.0) + income)
//      }
//    }
//
//    val bestByMonth = incomeMap
//      .groupBy(_._1._1).view
//      .mapValues(_.maxBy(_._2)).toMap
//      .map { case (month, ((_, gatherer), income)) => (month, (gatherer, income)) }
//
//    println("Best gatherer by month:")
//    println(bestByMonth)
//  }
//
//
//
//  def bestFruitByMonth(harvestRecords: Iterator[HarvestRecord], priceMap: Map[(LocalDate, String), Double]): Unit = {
//    val fruitIncomeByMonth = scala.collection.mutable.Map[(String, String), Double]()
//
//    for (h <- harvestRecords) {
//      priceMap.get((h.date, h.fruit)).foreach { price =>
//        val income = h.amount * price
//        val month = DateParser.formatToMonth(h.date)
//        val key = (month, h.fruit)
//        fruitIncomeByMonth.update(key, fruitIncomeByMonth.getOrElse(key, 0.0) + income)
//      }
//    }
//
//    val grouped = fruitIncomeByMonth.toList.groupBy(_._1._1)
//      .view.mapValues(_.maxBy(_._2)).toMap
//
//    val best = grouped.map { case (month, ((_, fruit), income)) => s"$month: $fruit ($income)" }
//    println()
//    println("Best fruit by month:")
//    println(best)
//  }
//
//
//  def worstFruitByMonth(harvestRecords: Iterator[HarvestRecord], priceMap: Map[(LocalDate, String), Double]): Unit = {
//    val fruitIncomeByMonth = scala.collection.mutable.Map[(String, String), Double]()
//
//    for (h <- harvestRecords) {
//      priceMap.get((h.date, h.fruit)).foreach { price =>
//        val income = h.amount * price
//        val month = DateParser.formatToMonth(h.date)
//        val key = (month, h.fruit)
//        fruitIncomeByMonth.update(key, fruitIncomeByMonth.getOrElse(key, 0.0) + income)
//      }
//    }
//
//    val grouped = fruitIncomeByMonth.toList
//      .groupBy(_._1._1)
//      .view
//      .mapValues(_.minBy(_._2))
//      .toMap
//
//    val worst = grouped.map { case (month, ((_, fruit), income)) => s"$month: $fruit ($income)" }
//
//    println()
//    println("Worst fruit by month:")
//    println(worst)
//  }
//
//  def bestAndWorstFruitOverall(harvestRecords: Iterator[HarvestRecord], priceMap: Map[(LocalDate, String), Double]): Unit = {
//    val fruitIncomeOverall = scala.collection.mutable.Map[String, Double]()
//
//    for (h <- harvestRecords) {
//      priceMap.get((h.date, h.fruit)).foreach { price =>
//        val income = h.amount * price
//        fruitIncomeOverall.update(h.fruit, fruitIncomeOverall.getOrElse(h.fruit, 0.0) + income)
//      }
//    }
//
//    if (fruitIncomeOverall.nonEmpty) {
//      val best = fruitIncomeOverall.maxBy(_._2)
//      val worst = fruitIncomeOverall.minBy(_._2)
//
//      println(s"Best fruit overall: ${best._1} with total income: ${best._2}")
//      println()
//      println(s"Worst fruit overall: ${worst._1} with total income: ${worst._2}")
//    } else {
//      println("No data to calculate best and worst fruit overall.")
//    }
//  }
//
//  def bestGatherer(harvestRecords: Iterator[HarvestRecord], priceMap: Map[(LocalDate, String), Double]): Unit = {
//
//    val gathererIncomeByMonth = scala.collection.mutable.Map[(String, String), Double]()
//    val gathererIncomeOverall = scala.collection.mutable.Map[String, Double]()
//
//    for (h <- harvestRecords) {
//      priceMap.get((h.date, h.fruit)).foreach { price =>
//        val income = h.amount * price
//        val month = DateParser.formatToMonth(h.date)
//        val monthKey = (month, h.gatherer)
//        gathererIncomeByMonth.update(monthKey, gathererIncomeByMonth.getOrElse(monthKey, 0.0) + income)
//        gathererIncomeOverall.update(h.gatherer, gathererIncomeOverall.getOrElse(h.gatherer, 0.0) + income)
//      }
//    }
//
//    val bestByMonth = gathererIncomeByMonth.toList
//      .groupBy(_._1._1)                // group by month
//      .view
//      .mapValues(_.maxBy(_._2))        // find max income gatherer per month
//      .toMap
//      .map { case (month, ((_, gatherer), income)) => (month, (gatherer, income)) }
//
//    println()
//    println(s"Best gatherer by month are $bestByMonth")
//
//    if (gathererIncomeOverall.nonEmpty) {
//      val overallBest = gathererIncomeOverall.maxBy(_._2)
//      println(s"Best gatherer overall: ${overallBest._1} with income: ${overallBest._2}")
//    } else {
//      println("No data to calculate best gatherer overall.")
//    }
//  }
//}
