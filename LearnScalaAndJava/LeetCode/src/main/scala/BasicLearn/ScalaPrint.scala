package com.scalalearn.scala.main

/**
  * Created by Administrator on 2016/5/14.
  */
object ScalaPrint {
  def print():String={
    println("I love scala");
    "I love scala"
  }

  def main(args: Array[String]) {
    if("dim12".matches("dim.*?")){
      println("yes")
    }else
    {
      println("no")
    }
}
}


