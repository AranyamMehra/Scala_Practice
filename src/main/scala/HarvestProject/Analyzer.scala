package HarvestProject

object Analyzer {
  def joinRecords(harvestRecords: List[HarvestRecord], priceRecords: List[PriceRecord]): List[Join] = {
    for {
      h <- harvestRecords
      p <- priceRecords
      if h.date == p.date && h.fruit == p.fruit
    } yield {
      val month = DateParser.formatToMonth(h.date)
      val income = h.amount * p.price
      Join(h.date, month, h.gatherer, h.fruit, h.amount, p.price, income)
    }
  }

  def bestGathererByMonth(j: List[Join]): Unit = {
    val gbym = j.groupBy(r => (r.month, r.gatherer))
      .mapValues(_.map(_.amount).sum).toList.groupBy(_._1._1)
      .mapValues(_.maxBy(_._2)).toMap
    val best = gbym.map { case (month, ((_, gatherer), amount)) => (month, (gatherer, amount)) }
    println(best)
  }

  def bestFruitByMonth(j: List[Join]): Unit = {
    val fbym = j.groupBy(r => (r.month, r.fruit))
      .mapValues(_.map(_.income).sum).toList.groupBy(_._1._1)
      .mapValues(_.maxBy(_._2)).toMap
    val best = fbym.map { case (month, ((_, fruit), amount)) => (month, (fruit, amount)) }
    println()
    println(best)
  }

  def worstFruitByMonth(j: List[Join]): Unit = {
    val wfbym = j.groupBy(r => (r.month, r.fruit))
      .mapValues(_.map(_.income).sum).toList.groupBy(_._1._1)
      .mapValues(_.minBy(_._2)).toMap
    val worst = wfbym.map { case (month, ((_, fruit), amount)) => (month, (fruit, amount)) }
    println(worst)
  }

  def bestAndWorstFruitOverall(j: List[Join]): Unit = {
    val grouped = j.groupBy(_.fruit).mapValues(_.map(_.income).sum)
    val best = grouped.maxBy(_._2)
    val worst = grouped.minBy(_._2)
    println(s"Best fruit overall: ${best._1} and total income of it is: ${best._2}")
    println()
    println(s"Worst fruit overall: ${worst._1} and total income of it is: ${worst._2}")
  }

  def bestGatherer(j: List[Join]): Unit = {
    val bestGatherer = j.groupBy(r => (r.month, r.gatherer))
      .mapValues(_.map(_.income).sum).toList.groupBy(_._1._1)
      .mapValues(_.maxBy(_._2)).toMap
      .map { case (month, ((_, gatherer), income)) => (month, (gatherer, income)) }
    println()
    println(s"Best gatherer by month are $bestGatherer")

    val overall = j.groupBy(_.gatherer).mapValues(_.map(_.income).sum).maxBy(_._2)
    println(s"Best gatherer overall: ${overall._1} and their income: ${overall._2}")
  }

}
