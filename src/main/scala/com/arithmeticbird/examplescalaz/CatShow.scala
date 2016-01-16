package com.arithmeticbird.examplescalaz

import com.arithmeticbird.example.Cat
import scalaz.std.anyVal._
import scalaz.std.string._
import scalaz.syntax.show._

import scalaz.Show

object CatShow {
    implicit val catShow = Show.shows[Cat]{ cat =>
      val name = cat.name.show
      val age  = cat.age.show
      val color = cat.color.show
      s"$name is a $age year-old $color cat"
    }
}
