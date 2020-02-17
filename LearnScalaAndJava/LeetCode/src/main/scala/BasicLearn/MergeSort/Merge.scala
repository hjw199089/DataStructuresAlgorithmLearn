package com.scalalearn.java.main.MergeSort

/**
  * Created by hjw on 17/6/1.
  */
object Merge {
  def msort[T](less: (T, T) => Boolean)(xs: List[T]): List[T] = {
    //柯里化
    //内部函数
    def merge(xs: List[T], ys: List[T]): List[T] = {
      (xs, ys) match {
        case (Nil, _) => ys
        case (_, Nil) => xs
        case (x :: xs1, y :: ys1) =>
          if (less(x, y)) x :: merge(xs1, ys)
          else y :: merge(xs, ys1)
      }
    }

    val n = xs.length / 2
    if (n == 0) xs
    else {
      val (ys, zs) = xs splitAt n //二分
      merge(msort(less)(ys), msort(less)(zs)) //归并
    }

  }

  def main(args: Array[String]) {
    val ls = List(5, 6, 3, 7, 1)
    println(msort((x: Int, y: Int) => x < y)(ls))
  }
}
