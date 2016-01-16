package com.arithmeticbird.monad

object Fuctor {
  //A monad is a functor. write map in terms of flatmap

  //Say we have two functions flatMap and point
  def flatMap[F[_], A, B](value: F[A])(func: A => F[B]): F[B] = ???

  def point[F[_], A](value: A): F[A] = ???

  //Let's write map in terms of last two!
  def map[F[_], A, B](value: F[A])(func: A => B): F[B] =  {
      flatMap(value)(a => point(func(a)))
  }

}
