package taller

import org.junit.runner.RunWith
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class ConjuntosDIfusosComplementoTest extends AnyFunSuite {
  val objconjunto = new ConjuntosDifusos()
  val conjunto = objconjunto.grande(2, 1)
  val conjunto2 = objconjunto.grande(0, 0)
  val complemento = objconjunto.complemento(conjunto)

  def approxEqual(a: Double, b: Double, eps: Double = 1e-2): Boolean =
    (a - b).abs < eps

  test("El complemento en 10 debe ser 1 - conjunto(10)") {
    val esperado = 1.0 - conjunto(10)
    assert(approxEqual(complemento(10), esperado))
  }

  test("El complemento en 1000 debe ser 1 - conjunto(100)") {
    val esperado = 1.0 - conjunto(1000)
    assert(approxEqual(complemento(1000), esperado))
  }

  test("El complemento en 1 debe ser 1 - conjunto(1)") {
    val esperado = 1.0 - conjunto(1)
    assert(approxEqual(complemento(1), esperado))
  }

  test("El complemento en 0 debe ser 1 - conjunto(0)") {
    val esperado = 1.0 - conjunto(0)
    assert(approxEqual(complemento(0), esperado))
  }

  test("El complemento en 47 debe ser 1 - conjunto2(0)") {
    val esperado = 1.0 - conjunto2(47)
    assert(approxEqual(complemento(47), esperado))

  }

}