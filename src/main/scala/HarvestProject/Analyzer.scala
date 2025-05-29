package HarvestProject
import java.time.LocalDate

trait Analyzer {
  def compute(harvestRecords: Iterator[HarvestRecord], priceMap: Map[(LocalDate, String), Double]): Unit
}
