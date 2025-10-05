package taller

import org.junit.runner.RunWith
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class IgualdadTest extends AnyFunSuite {
  val objConj = new ConjuntosDifusos()

  test("Igualdad entre dos conjuntos idénticos") {
    val cd1: Int => Double = _ => 0.5
    val cd2: Int => Double = _ => 0.5
    assert(objConj.igualdad(cd1, cd2) === true)
  }

  test("Igualdad entre conjunto vacío y lleno") {
    val cd1: Int => Double = _ => 0.0
    val cd2: Int => Double = _ => 1.0
    assert(objConj.igualdad(cd1, cd2) === false)
  }

  test("Igualdad entre dos conjuntos escalonados iguales") {
    val cd1: Int => Double = n => if (n < 5) 0.0 else 1.0
    val cd2: Int => Double = n => if (n < 5) 0.0 else 1.0
    assert(objConj.igualdad(cd1, cd2) === true)
  }

  test("Igualdad entre escalonado y constante") {
    val cd1: Int => Double = n => if (n < 5) 0.0 else 1.0
    val cd2: Int => Double = _ => 0.5
    assert(objConj.igualdad(cd1, cd2) === false)
  }

  test("Igualdad entre dos conjuntos con mismos valores en todo el rango") {
    val cd1: Int => Double = n => (n % 3) * 0.3
    val cd2: Int => Double = n => (n % 3) * 0.3
    assert(objConj.igualdad(cd1, cd2) === true)
  }
}