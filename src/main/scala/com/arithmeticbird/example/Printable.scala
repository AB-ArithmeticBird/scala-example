package com.arithmeticbird.example

trait Printable[A] {
  def format(a: A): String
}

object PrintDefaults {

  implicit val stringPrintable = new Printable[String] {
    override def format(a: String): String = a
  }

  implicit val intPrintable = new Printable[Int] {
    override def format(a: Int): String = a.toString
  }

}