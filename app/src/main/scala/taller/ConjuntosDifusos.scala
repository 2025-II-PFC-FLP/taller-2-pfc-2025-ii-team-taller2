package taller
import scala.annotation.tailrec
import scala.math._
class ConjuntosDifusos {
  type ConjDifuso = Int => Double


  def pertenece(elem: Int, s: ConjDifuso): Double = {
    s(elem)
  }


  def grande(d: Int, e: Int): ConjDifuso = {
    //d representa que tan rapido crece la curva
    //e representa que tan curva es la subida de los porcentajes de pertenecia

    def funcion (n:Int): Double = {
      val exprecion = n.toDouble / (n + d)
      @tailrec
      def potencia(e: Int)(acc: Double): Double = {
        //n es el valor que se desea evaluar
        e match {
          case 0 => acc
          case e => potencia (e - 1)(acc * exprecion )
        }
      }
      potencia(e)(1.0)
    }
    funcion
    //se retorna la funcion como un conjunto debido a que por definicion es una funcion
    //que recibe un entero y retorna un Double
  }


  def complemento(c: ConjDifuso): ConjDifuso = {

      def complemento1(n:Int): Double ={
        1.0 - c(n)
      }
      complemento1
    //retorna un conjunto difuso complemento del ingresado
  }

  def union(cd1: ConjDifuso, cd2: ConjDifuso): ConjDifuso = {

    // Implementaci´on de la funci´on union

  }
  def interseccion(cd1: ConjDifuso, cd2: ConjDifuso): ConjDifuso = {

    // Implementaci´on de la funci´on interseccion

  }
  def inclusion(cd1: ConjDifuso, cd2: ConjDifuso): Boolean = {

    // Implementaci´on de la funci´on inclusion

  }
  def igualdad(cd1: ConjDifuso, cd2: ConjDifuso): Boolean = {

    // Implementaci´on de la funci´on igualdad


  }

}

