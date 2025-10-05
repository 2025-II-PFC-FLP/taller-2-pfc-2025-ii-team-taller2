package taller
import org.junit.runner.RunWith
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class UnionTest extends AnyFunSuite{
  val objInter = new ConjuntosDifusos()
  //assert(SplitAlgorithm.multiply(listA(i), listB(i)) == listA(i) * listB(i))


  test("union con argumento de 1 devuelve el maximo") {

    //Como la funcion espera 1 solo argumento
    //cd1 y cd2 (que son funciones anonimas) se les asigna el mismo argumento en este caso 1
    //Donde pasa por un condicional en cada funcion anonima y dandoles un valor a
    //cd1 y cd2 de la funcion union
    val cd1: Int => Double = n => if (n == 1) 0.92 else 0.8
    val cd2: Int => Double = n => if (n == 1) 1.0 else 0.1

    val union = objInter.Union(cd1, cd2)
    //Se asigna un argumento a la funcion, en este caso 1
    assert(union(1) === 1.0)

  }

  test("Union con argumento de 2 devuelve el Maximo") {

    //Como la funcion espera 2 solo argumento
    //cd1 y cd2 (que son funciones anonimas) se les asigna el mismo argumento en este caso 2
    //Donde pasa por un condicional en cada funcion anonima y dandoles un valor a
    //cd1 y cd2 de la funcion union
    val cd1: Int => Double = n => if (n == 1) 0.5 else 0.27
    val cd2: Int => Double = n => if (n == 1) 0.45 else 0.3

    val union = objInter.Union(cd1, cd2)
    //Se asigna un argumento a la funcion, en este caso 1
    assert(union(2) === 0.3)

  }

  test("Comparacion de ambos conjuntos") {
    val cd1: Int => Double = n => if (n == 1) 0.35 else 0.13
    val cd2: Int => Double = n => if (n == 1) 0.35 else 0.28

    val v1 = cd1(1)
    val v2 = cd2(1)

    if (v1 < v2)
      println(s"cd1($v1) es menor que cd2($v2)")
    else if (v2 < v1)
      println(s"cd2($v2) es menor que cd1($v1)")
    else
      println(s"cd1($v1) y cd2($v2) son iguales")
  }

  test("Union que tiene al menos un valor igual a 0.5") {
    val cd1: Int => Double = n => if (n == 2) 0.2 else 0.8
    val cd2: Int => Double = n => if (n == 2) 0.1 else 1.0

    val union = objInter.Union(cd1, cd2)
    val argumentos = List(1, 2, 3)//Nuestros valores n para evaluar
    //Cada elemento de la lista se almacena en n y despues se evalua en union
    //Si alguno de los resultados es igual a 0.8
    //Entonces el test pasa
    assert(argumentos.exists(n => union(n)===1.0))

  }

  test("Union con argumentos específicos") {
    val cd1: Int => Double = _ => 0.25
    val cd2: Int => Double = _ => 0.37
    //Los argumentos en este caso no importan
    //Porque las funciones anonimas retornan un valor pre establecido

    val union = objInter.Union(cd1, cd2)
    assert(union(5)=== 0.37)
  }
}
