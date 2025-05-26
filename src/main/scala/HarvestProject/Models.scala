package HarvestProject

import java.time.LocalDate

case class HarvestRecord(date: LocalDate, gatherer: String, fruit: String, amount: Double)
case class PriceRecord(date: LocalDate, fruit: String, price: Double)
case class Join(date: LocalDate, month: String, gatherer: String, fruit: String, amount: Double, price: Double, income: Double)
