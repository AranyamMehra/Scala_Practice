package HarvestProject
import java.time.LocalDate
import java.time.format.DateTimeFormatter

object DateParser {
  val harvestFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
  val priceFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

  def parseHarvestDate(dateStr: String): LocalDate = LocalDate.parse(dateStr, harvestFormatter)
  def parsePriceDate(dateStr: String): LocalDate = LocalDate.parse(dateStr, priceFormatter)
  def formatToMonth(date: LocalDate): String = date.format(DateTimeFormatter.ofPattern("yyyy-MM"))
}
