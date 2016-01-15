package com.arithmeticbird.example

import com.arithmeticbird.Printable

object CatPrint {
  implicit val catPrintable = new Printable[Cat] {

    override def format(a: Cat): String = {
      s"${a.name} is a ${a.age} year-old cat"
    }
  }
}