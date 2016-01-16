package com.arithmeticbird.monoid

import scalaz.Monoid

object Add {

  import scalaz.syntax.monoid._

  def add[A:Monoid](items: List[A]): A =
    items.foldLeft(mzero[A]){ _ |+| _ }
}

