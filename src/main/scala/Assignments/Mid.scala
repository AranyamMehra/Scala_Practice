package Assignments

object Mid extends App {
  def size[T](list: List[T]): Int = list match {
    case Nil => 0
    case _ :: tail => 1 + size(tail)
  }

  def get[T](list: List[T], i: Int): Option[T] = list match
  {
    case Nil => None
    case head :: tail =>
      if (i == 0) Some(head)
      else get(tail, i - 1)
  }

  def middle[T](list: List[T]): Option[T] = {
    val len = size(list)
    get(list, len / 2)
  }
  println(middle(List(1, 4, 3, 2, 5)))
  println(middle(List(1, 2, 3, 4, 5, 6)))
  println(middle(List(1)))
  println(middle(Nil))
}
