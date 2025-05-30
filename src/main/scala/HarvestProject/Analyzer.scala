package HarvestProject
import java.time.LocalDate

trait Analyzer {
  def compute(record: HarvestRecord, price: Option[Double]): Unit
  def report(): Unit
}