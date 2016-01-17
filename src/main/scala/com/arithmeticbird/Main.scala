package com.arithmeticbird

import com.arithmeticbird.example.{PrintSyntax, Cat}
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

  def functorExample() = {
    import scalaz.Functor
    import scalaz.std.list._
    val n = Functor[List].map(List(4,5,8,11))(x=>x*2)
    println(n)

    //lifting a function into Functor
    import scalaz.std.option._
    val lifted: (Option[Int]) => Option[Int] = Functor[Option].lift((x:Int) => x + 2)

    // Creating a new functor and then using it ...
    import com.arithmeticbird.functor.Result._

    import scalaz.syntax.functor._

    println(success(11) map (_ * 2))  //
  }

  def monadExample() = {
    import scalaz.Monad
    import scalaz.std.option._

    //convert to option of ...
    val maybeX = Monad[Option].tuple3(some(1), some("hi"), some(4.5))
    println(maybeX)

    //
    import com.arithmeticbird.monad.Result._
    import scalaz.syntax.monad._

    val r = for {
      a <- success(6)
      b <- warning(-1, "-ve number")
    } yield a+b

    println(r)


  }

  simplePrint(cat)

  scalazPrint(cat)

  monoidExample()

  functorExample()

  monadExample()

}
