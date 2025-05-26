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
    if (len % 2 != 0)
      get(list, len / 2)
    else get (list, len/2 - 1)
  }

  def FindMiddle [T] (list: List [T]): Option [T] = {
    def loop (slow: List [T], fast: List[T]): Option [T] = fast match {
      case _ :: _ :: Nil => slow.headOption
      case _ :: _ :: tail => loop (slow.tail, tail)
      case _ => slow.headOption
    }
    loop (list, list)
  }

  def safePrintMiddle[T](list: List[T]): Unit = {
    try {
      println(FindMiddle(list))
    } catch {
      case e: Exception => println(s"Exception caught: ${e.getMessage}")
    }
  }


  safePrintMiddle(List(1,2,3,4,5,6,7))
  safePrintMiddle (List(1, 2, 3, 4, 5, 6))
  safePrintMiddle(List(1))
  safePrintMiddle(Nil)
}
