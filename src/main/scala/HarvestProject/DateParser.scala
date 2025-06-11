package HarvestProject
import java.time.LocalDate
import java.time.format.DateTimeFormatter

object DateParser {
  private val harvestFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
  private val priceFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

  def parseHarvestDate(dateStr: String): LocalDate = LocalDate.parse(dateStr, harvestFormatter)
  def parsePriceDate(dateStr: String): LocalDate = LocalDate.parse(dateStr, priceFormatter)
  def formatToMonth(date: LocalDate): String = date.format(DateTimeFormatter.ofPattern("MM-yyyy"))
}