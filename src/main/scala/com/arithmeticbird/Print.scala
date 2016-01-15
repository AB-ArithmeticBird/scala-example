package com.arithmeticbird


object Print {
  def format[A](a: A)(implicit p: Printable[A]): String = {
     p.format(a)
  }

  def print[A](a: A)(implicit p: Printable[A]):Unit = {
    println(format(a)(p))
  }
}
