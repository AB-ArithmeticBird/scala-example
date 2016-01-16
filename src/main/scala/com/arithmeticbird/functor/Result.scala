package com.arithmeticbird.functor

import scalaz.Functor

sealed trait Result[+A]
final case class Success[A](value: A) extends Result[A]
final case class Warning[A](value: A, message: String) extends Result[A]
final case class Failure(message: String) extends Result[Nothing]


object Result {
  //smart constructor
  def success[A](value: A): Result[A] = Success(value)
  def warning[A](value: A, message: String): Result[A] = Warning(value, message)
  def failure[A](message: String): Result[A] = Failure(message)


  implicit val resultFunctor = new Functor[Result] {
    override def map[A, B](fa: Result[A])(f: (A) => B): Result[B] = {
      fa match {
        case Success(value) => Success(f(value))
        case Warning(value, msg) => Warning(f(value), msg)
        case Failure(msg) => Failure(msg)
      }
    }
  }
}