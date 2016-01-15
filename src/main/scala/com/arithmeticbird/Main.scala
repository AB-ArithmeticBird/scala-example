package com.arithmeticbird

//Type class example
object Main extends App {

  import com.arithmeticbird.example.CatPrint._
  import PrintSyntax._

  val cat = com.arithmeticbird.example.Cat("Tracy", 4, "black")
  cat.print
}
