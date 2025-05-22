package Assignments

object Length extends App{
  def length[A] (list : List[A]): Int = {
    def rec (l : List [A], acc : Int) : Int = {
      if (l  == Nil) acc
      else rec (l.tail, acc = acc + 1)
    }
    rec (list, 0)
  }

  val list = List (1,2,3,4,5,6,7,8,9,6,7,8,3,5,7,4)
  println (length(list))
}
