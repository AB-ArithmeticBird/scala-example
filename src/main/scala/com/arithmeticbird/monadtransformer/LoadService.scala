package com.arithmeticbird.monadtransformer

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Await, Future}
import scalaz.EitherT
import scalaz.std.scalaFuture._
import scalaz.syntax.monadError._

trait LoadService {
  type FutureEither[A] = EitherT[Future, String, A]

  def getLoad(hostName:String):FutureEither[Double]
  def getMeanLoad(hostNames:List[String]) :FutureEither[Double]
  def report [A](input:FutureEither[Double]):Unit

}

object LoadServiceInterpreter extends LoadService{
  override def getLoad(hostName: String): FutureEither[Double] = {
    val k: Option[Double] = LoadDB.loadAverage
      .get(hostName)

    val m: Option[LoadServiceInterpreter.FutureEither[Double]] = k.map(_.point[FutureEither])

    m.getOrElse("Host unreachable".raiseError[FutureEither, Double])

  }

  override def getMeanLoad(hostNames: List[String]): FutureEither[Double] = {
    hostNames.length match {
      case 0 =>
        val k: LoadServiceInterpreter.FutureEither[Double] =
          s"No hosts to contact".raiseError[FutureEither, Double]
           k

      case n =>
        import scalaz.std.list._
        import scalaz.syntax.traverse._
        val l: List[FutureEither[Double]] = hostNames.map(h => getLoad(h))
        val m: FutureEither[List[Double]] = l.sequence   //Idea is failing huh!
        val r: FutureEither[Double] = m.map(xs => xs.foldLeft(0.0)(_ + _) / n )
        r
    }
  }

  override  def report [A](input:FutureEither[Double]):Unit = {
    import scala.concurrent.duration._
     Await.result(input.run, 2.seconds).fold(
      l = msg => println("[FAIL] " + msg),
      r = ans => println("[DONE] " + ans)
    )

  }
}