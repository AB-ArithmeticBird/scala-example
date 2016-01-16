package com.arithmeticbird.monad

import scalaz.Monad

//Result again ...
sealed trait Result[+A]
final case class Success[A](value: A) extends Result[A]
final case class Warning[A](value: A, message: String) extends Result[A]
final case class Failure(message: String) extends Result[Nothing]

object Result {
  //smart constructor
  def success[A](value: A): Result[A] = Success(value)
  def warning[A](value: A, message: String): Result[A] = Warning(value, message)
  def failure[A](message: String): Result[A] = Failure(message)

  implicit val resultMonad = new Monad[Result]   {
    override def bind[A, B](fa: Result[A])(f: (A) => Result[B]): Result[B] = fa match{
      case Success(value) => f(value)
      case Warning(value, msg) => f(value) match {
        case Success(value2) => Warning(value2,msg)
        case Warning(value2, msg2) => Warning(value2, "msg" +"msg2")
        case Failure(msg2)=> Failure(msg2)
      }
      case Failure(value) => Failure(value)

    }

    override def point[A](a: => A): Result[A] = Success(a)
  }
}