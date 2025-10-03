package taller

import org.junit.runner.RunWith
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.junit.JUnitRunner
import scala.math.pow
@RunWith(classOf[JUnitRunner])
class ConjuntosDIfusosGrandeTest extends AnyFunSuite {
  val objconjunto = new ConjuntosDifusos()
  val conjunto1 = objconjunto.grande(5, 5)
  val conjunto2 = objconjunto.grande(7, 1)
  def approxEqual(a: Double, b: Double, eps: Double = 1e-2): Boolean =
    (a - b).abs < eps
  test("10 no debe pertenecer al conjunto1 su porcentaje es muy chico") {
    //assert(objconjunto.pertenece(10, conjunto1) == 0.13)
    assert(approxEqual(objconjunto.pertenece(10, conjunto1), 0.13))
  }
  test("1000 debe pertenecer al conjunto1 su porcentaje es muy grande ") {
    //assert(objconjunto.pertenece(1000, conjunto1) == 0.97)
    assert(approxEqual(objconjunto.pertenece(1000, conjunto1), 0.97))
  }
  test("150 tiene muchas caracteriticas que lo clásifican en el conjunto1 ") {
     //assert(objconjunto.pertenece(150, conjunto1) == 0.84)
    assert(approxEqual(objconjunto.pertenece(150, conjunto1), 0.84))
  }
  test("funcion grande del conjunto2 evaluada en 1 debe ser igual a su funcion caracteristica a la 1") {
    assert(conjunto2(1) == (1.toDouble / (1 + 7)))
  }
  test("funcion grande del conjunto1 evaluada en 10 debe ser igual a su funcion caracteristica a la 5") {
    assert(conjunto1(10) == math.pow((10.toDouble / (10 + 7)), 5))
  }

}