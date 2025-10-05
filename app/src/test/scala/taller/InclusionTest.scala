package taller

import org.junit.runner.RunWith
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class InclusionTest extends AnyFunSuite {
  val objConj = new ConjuntosDifusos()

  test("Inclusion con cd1 totalmente incluido en cd2") {
    // cd1 siempre tiene valores <= cd2
    val cd1: Int => Double = n => if (n == 1) 0.3 else 0.4
    val cd2: Int => Double = n => if (n == 1) 0.7 else 0.9

    val inclusion = objConj.inclusion(cd1, cd2)
    assert(inclusion === true)
  }

  test("Inclusion con cd1 no incluido en cd2 en un punto") {
    // cd1 tiene un valor > cd2 en n = 1
    val cd1: Int => Double = n => if (n == 1) 0.8 else 0.2
    val cd2: Int => Double = n => if (n == 1) 0.5 else 0.9

    val inclusion = objConj.inclusion(cd1, cd2)
    assert(inclusion === false)
  }

  test("Inclusion con cd1 igual a cd2") {
    // cd1 y cd2 tienen los mismos valores
    val cd1: Int => Double = n => if (n == 1) 0.5 else 0.6
    val cd2: Int => Double = n => if (n == 1) 0.5 else 0.6

    val inclusion = objConj.inclusion(cd1, cd2)
    assert(inclusion === true)
  }

  test("Inclusion con cd1 parcialmente incluido en cd2") {
    // cd1 tiene valores <= cd2 excepto en algunos puntos
    val cd1: Int => Double = n => if (n == 1) 0.4 else if (n == 2) 0.7 else 0.3
    val cd2: Int => Double = n => if (n == 1) 0.6 else if (n == 2) 0.5 else 0.9

    val inclusion = objConj.inclusion(cd1, cd2)
    assert(inclusion === false)
  }

  test("Inclusion con cd1 y cd2 constantes") {
    // Ambos conjuntos tienen valores constantes, cd1 <= cd2
    val cd1: Int => Double = _ => 0.3
    val cd2: Int => Double = _ => 0.8

    val inclusion = objConj.inclusion(cd1, cd2)
    assert(inclusion === true)
  }
}