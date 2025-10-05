# Informe de corrección función complemento para conjuntos difusos

Sea: $$f: \mathcal{C} \to \mathcal{C}$$

una función que calcula el complemento de un conjunto difuso, donde $C$
representa el tipo de conjuntos difusos, definido como funciones

$$μ:Z→[0,1]$$

(es decir, ConjDifuso $\equiv$
Int $\to$ Double en Scala, asumiendo que los valores de membresía están en $[0,1]$).
El complemento $\bar{\mu}$de un conjunto difuso $\mu$
se define matemáticamente como:

$$\bar{\mu}(n) = 1 - \mu(n)\ \forall n \in \mathbb{Z}$.$$

Sea $P_f$
el siguiente programa en Scala, diseñado para calcular $f$:

```scala  
   def complemento(c: ConjDifuso): ConjDifuso = {
      def complemento1(n:Int): Double ={
        1.0 - c(n)
      }
      complemento1
  }
```
Este programa recibe un conjunto difuso $C$ de tipo ConjDifuso y devuelve 
una nueva función que representa su complemento.
Demostraremos el siguiente teorema:

$$\forall C \in \mathcal{C},\ \forall n \in \mathbb{Z}:\ P_f(C)(n) = f(C)(n) = 1 - C(n)$$

### Demostración
Aplicamos el modelo de sustitución al programa:<br>

$
P_f(C) = \text{complemento}(C) \to (\text{def complemento1}
(n: \text{Int}): \text{Double} = 1.0 - C(n)) \to \text{complemento1}
$
<br> Por lo tanto: <br>
$
P_f(C)(n) \to \text{complemento1}(n) \to 1.0 - C(n)
$

Esto coincide exactamente con la definición matemática de $f(C)(n)=1−C(n)$
Dado que la transformación es directa y no involucra condicionales, recursión 
o iteración, no hay casos base ni hipótesis de inducción que considerar. La 
igualdad se cumple por construcción para todo $C$ y todo $n$.Concluimos que $P_f$
es correcto con respecto a $f$.















