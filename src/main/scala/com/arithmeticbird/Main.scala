package com.arithmeticbird

import com.arithmeticbird.example.Cat
import com.arithmeticbird.monoid.Add

//Type class example
object Main extends App {
  val cat = com.arithmeticbird.example.Cat("Tracy", 4, "black")

  //our own type classes
  def simplePrint(cat:Cat) = {
    import PrintSyntax._
    import com.arithmeticbird.example.CatPrint._
    cat.print
  }

  //type classes using scalaz
  def scalazPrint(cat: Cat) = {
    import com.arithmeticbird.examplescalaz.CatShow._

    import scalaz.syntax.show._
    println(cat.show)
  }

  //Monoid using scalaz
  def monoidExample() = {
    import scalaz.std.anyVal._
    val l = List(4,5,6,7,8)
    println(Add.add(l))

    import scalaz.std.option._

    val maybeInt1 = Option(1)
    val maybeInt2 = Option(6)
    val maybeIntList = List(maybeInt1,maybeInt2)
    println(Add.add(maybeIntList))

    import com.arithmeticbird.monoid.Order
    val lOrder = List(Order(1,2),Order(1,8),Order(6,2))
    println(Add.add(lOrder))
  }

  simplePrint(cat)
  scalazPrint(cat)
  monoidExample()

}
