package taller
import scala.annotation.tailrec
import scala.math._
class ConjuntosDifusos {
  type ConjDifuso = Int => Double


  def pertenece(elem: Int, s: ConjDifuso): Double = {
    s(elem)
  }


  def grande(d: Int, e: Int): ConjDifuso = {
    // d representa qué tan rápido crece la curva
    // e representa qué tan pronunciada es la subida de los porcentajes de pertenencia
    // Se asume e >= 0 para que la potencia esté bien definida
    if (e < 0) {
      throw new IllegalArgumentException("El exponente e debe ser no negativo (e >= 0)")
    }

    def funcion(n: Int): Double = {
      if (n + d == 0) {
        // setear a 0.0 ,los casos donde la funcion no esta definida
        0.0
      } else {
        val expresion = n.toDouble / (n + d)
        @tailrec
        def potencia(i: Int, acc: Double): Double = {
          // Calcula (n / (n + d))^e usando recursión de cola
          // n es el valor que se desea evaluar
          i match {
            case 0 => acc
            case _ => potencia(i - 1, acc * expresion)
          }
        }
        potencia(e, 1.0)
      }
    }
    funcion  // Se retorna la función como un conjunto difuso (Int => Double)
  }


  def complemento(c: ConjDifuso): ConjDifuso = {

      def complemento1(n:Int): Double ={
        1.0 - c(n)
      }
      complemento1
    //retorna un conjunto difuso complemento del ingresado
  }

  def Union(cd1: ConjDifuso, cd2: ConjDifuso): ConjDifuso = {

    n => math.max(cd1(n), cd2(n))

  }

  def Interseccion(cd1: ConjDifuso, cd2: ConjDifuso): ConjDifuso = {

    n => math.min(cd1(n), cd2(n))

  }


  /*def inclusion(cd1: ConjDifuso, cd2: ConjDifuso): Boolean = {

    // Implementaci´on de la funci´on inclusion

  }
  def igualdad(cd1: ConjDifuso, cd2: ConjDifuso): Boolean = {

    // Implementaci´on de la funci´on igualdad
  }*/

}

