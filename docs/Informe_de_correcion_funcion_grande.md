# informe de corrección funcion Grande para conjuntos difusos con potencia por Recursión de Cola

**Fundamentos de Programación Funcional y Concurrente**

Sea: $$f: \mathbb{Z} \times \mathbb{Z} \to (\mathbb{Z} \to [0,1])
$$  

una función que, dados dos parámetros enteros `d` y `e`, construye un **conjunto difuso**.

Donde ```ConjDifuso``` es un tipo funcional definido como:
```scala
type ConjDifuso = Int => Double
```
Ademas La función grande esta definida de la forma:
$
grande(d,e)(n) = \left(\frac{n}{n+d}\right)^e
$ y la potencia se calcula por medio de recursion de cola.


## Argumentación de corrección para potencia

```scala
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

```


* Estado: $s = (i,acc)$
* Estado inicial: $s_0 = (i ,1.0)$
* Estado final: $s_f = \left(0, \left(\frac{n}{n + d}\right)^e\right)$
* Invariante:$
  \text{Inv}(i, acc) \equiv acc = \left( \frac{n}{n + d} \right)^{e - i}
  $

  Este invariante indica que, en cada iteración, el acumulador contiene la potencia de $\frac{n}{n + d}$ correspondiente al número de iteraciones completadas ($e - i$).


* Transformación:
  $$
  (i, acc) \to \left( i - 1, acc \cdot \frac{n}{n + d} \right)
  $$
  Donde $\frac{n}{n + d}$ es el valor de la variable `expresion` en el código.

### Demostración por inducción para `potencia`

Queremos demostrar que:
$$
\forall e \geq 0 : \text{potencia}(e, 1.0) = \left( \frac{n}{n + d} \right)^e
$$
asumiendo $ n + d \neq 0 $.

### **Caso base**: $ e = 0 $.
  $$
  \text{potencia}(0, 1.0) \to 1.0 \quad \land \quad \left( \frac{n}{n + d} \right)^0 = 1
  $$
  Entonces $ \text{potencia}(0, 1.0) = \left( \frac{n}{n + d} \right)^0 $.

### **Caso inductivo**: supondremos que $ \text{potencia}(k, 1.0) = \left( \frac{n}{n + d} \right)^k $ para $ k \geq 0 $. Para $ e = k + 1 $:
  

$$
  \text{potencia}(k + 1, 1.0) \to \text{potencia}(k, 1.0) \cdot \frac{n}{n + d}
  $$
  Por la hipótesis de inducción:
  $$
  \to \left( \frac{n}{n + d} \right)^k \cdot \frac{n}{n + d} = \left( \frac{n}{n + d} \right)^{k + 1}
  $$
  Por lo tanto, $ \text{potencia}(k + 1, 1.0) = \left( \frac{n}{n + d} \right)^{k + 1} $.


- **Conclusión**:la función `potencia(i: Int, acc: Double): Double` calcula correctamente $ \left( \frac{n}{n + d} \right)^e $ para $ e \geq 0 $, por hipotesis de inducción.



---


## Argumentación de corrección para la función `grande`

```scala

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

```

La función:
```grande(d: Int, e: Int): ConjDifuso ```
devuelve una función  ```funcion: Int => Double``` que para cualquier $n$, calcula $\left( \frac{n}{n + d} \right)^e$. 

Queremos demostrar que:
$$\forall n \in \mathbb{Z}, \forall d\in \mathbb{Z},\forall  e \in \mathbb{Z}, e \geq 0, n + d \neq 0 : grande(d, e)(n) = \left( \frac{n}{n + d} \right)^e$$

Definición de ``funcion``:
La función interna ``funcion(n: Int): Double calcula``:

``expresion = n.toDouble / (n + d)``, es decir $$\frac{n}{n + d}$$
Llama a potencia(e, 1.0). pasando expresion como el factor de multiplicación en cada iteración.


### Retorno de grande

* La función  verifica primero si $( e \geq 0 )$.

* Si $( e < 0 )$, lanza una excepción (IllegalArgumentException).

* Si $( e \geq 0 )$, retorna ```funcion```, que recibe un dato de tipo Int y retorna un Double. 

### Condiciones de validez

* Para que la expresión $( \frac{n}{n + d} )$ esté definida, se requiere $( n + d \neq 0 )$.
 para esto se retorna ( 0.0 ). 

* La restricción $( e \geq 0 )$ asegura que la recursión en potencia termine. 

* Para $( e < 0 )$, la excepción garantiza que el programa no ejecute un comportamiento indefinido.

Por lo tanto, para todos los casos donde $( e \geq 0 )$ y $( n + d \neq 0 )$, la función ```grande(d, e)(n)``` produce $( \left( \frac{n}{n + d} \right)^e )$, y para ```( n + d = 0 )```, retorna ```( 0.0 )```.


## Conclusión
La función grande satisface la especificación:
$$\forall n \in \mathbb{Z}, \forall d\in \mathbb{Z},\forall  e \in \mathbb{Z}, e \geq 0, n + d \neq 0 : grande(d, e)(n) = \left( \frac{n}{n + d} \right)^e$$
* Para $( n + d = 0 )$, la función retorna ( 0.0 ). ***esto quire decir que el elemento no pertenece al conjunto***.
* Para $( e < 0 )$, la función lanza una excepción, respetando la restricción de la especificación.
    