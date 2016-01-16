package com.arithmeticbird.monoid

import scalaz.Monoid

case class Order(totalCost: Double, quantity: Double)

object Order {
  implicit val monoid = new Monoid[Order] {
    override def zero: Order = Order(0,0)

    override def append(f1: Order, f2: => Order): Order = {
      Order(f1.totalCost + f2.totalCost, f1.quantity+f2.quantity)
    }
  }
}
