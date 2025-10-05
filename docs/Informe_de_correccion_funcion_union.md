# Informe de corrección función union para conjuntos difusos



Sea: $$f: (\mathbb{Z} \to [0,1]) \times (\mathbb{Z} \to [0,1]) \to (\mathbb{Z} \to [0,1])$$

una función que, dados dos conjuntos difusos `cd1` y `cd2`, construye un nuevo **conjunto difuso** que representa su unión.

Donde `ConjDifuso` es un tipo funcional definido como:
```scala
type ConjDifuso = Int => Double
```
Además, la función `union` está definida de la forma:
$$
union(cd1, cd2)(n) = \max(cd1(n), cd2(n))
$$

## Argumentación de corrección para la función `union`

```scala
def union(cd1: ConjDifuso, cd2: ConjDifuso): ConjDifuso = {
  n => math.max(cd1(n), cd2(n))
}
```

- La función `union` toma dos funciones `cd1` y `cd2` (conjuntos difusos) y retorna una nueva función que, para cada entero `n`, calcula el máximo entre los grados de pertenencia de `cd1(n)` y `cd2(n)`.

### Estado y Transformación

- **Estado**: No se utiliza recursión ni un acumulador, por lo que no hay un estado explícito que evolucione iterativamente. La función es una transformación directa basada en el valor de entrada `n`.
- **Estado inicial**: No aplica, ya que la función no mantiene un estado inicial.
- **Estado final**: La salida es el valor `math.max(cd1(n), cd2(n))` para cada `n`.
- **Invariante**: La corrección depende de que `cd1(n)` y `cd2(n)` retornen valores en el rango `[0, 1]`, lo cual se asume como parte de la definición de `ConjDifuso`.

### Demostración de corrección

Queremos demostrar que:
$$
\forall n \in \mathbb{Z}, \forall cd1, cd2 \in (\mathbb{Z} \to [0,1]) : \text{union}(cd1, cd2)(n) = \max(cd1(n), cd2(n))
$$

#### Caso base y general:
Dado que `union` es una función pura que aplica `math.max` directamente, no requiere un caso base ni inducción. La corrección se verifica por la definición misma de la función:
- Para cualquier `n`, `union(cd1, cd2)(n)` evalúa `math.max(cd1(n), cd2(n))`.
- Como `math.max` es una función estándar que retorna el mayor de dos números, y asumiendo que `cd1(n)` y `cd2(n)` están en `[0, 1]`, el resultado siempre estará en `[0, 1]`, cumpliendo con las propiedades de un conjunto difuso.

#### Condiciones de validez:
- **Rango de valores**: Se asume que `cd1` y `cd2` son funciones válidas de tipo `ConjDifuso`, es decir, retornan valores en `[0, 1]` para todo `n`. Esto garantiza que el resultado de `union` también esté en `[0, 1]`.
- **Comportamiento**: La unión de dos conjuntos difusos, definida como el máximo, es una operación válida en teoría de conjuntos difusos, y la implementación refleja esta propiedad.

- **Conclusión**: La función `union(cd1, cd2)(n)` calcula correctamente $\max(cd1(n), cd2(n))$ para todo `n`, siempre que `cd1` y `cd2` sean conjuntos difusos válidos.

## Argumentación de corrección para la función `union` (perspectiva funcional)

La función `union` es una composición directa de las funciones `cd1` y `cd2` con `math.max`. No hay bucles ni recursión, por lo que su corrección depende únicamente de:
- La implementación de `math.max`, que es correcta por definición.
- La garantía de que `cd1(n)` y `cd2(n)` devuelvan valores en `[0, 1]`.

### Retorno de `union`
- La función retorna una nueva función `n => math.max(cd1(n), cd2(n))`, que es de tipo `ConjDifuso`, cumpliendo con la especificación.
- No hay validación explícita de los parámetros `cd1` y `cd2`, pero se asume que son funciones bien definidas como parte del contrato de `ConjDifuso`.

### Condiciones de validez
- **Dominio**: La función está definida para todo `n` en $\mathbb{Z}$, ya que `cd1` y `cd2` son funciones totales de `Int => Double`.
- **Rango**: El resultado siempre estará en `[0, 1]` si `cd1(n)` y `cd2(n)` lo están, lo cual es consistente con la definición de conjuntos difusos.

## Conclusión
La función `union` satisface la especificación:
$$
\forall n \in \mathbb{Z}, \forall cd1, cd2 \in (\mathbb{Z} \to [0,1]) : union(cd1, cd2)(n) = \max(cd1(n), cd2(n))
$$
- La implementación es correcta porque utiliza `math.max` para calcular la unión de los grados de pertenencia, alineándose con la definición teórica de unión en conjuntos difusos.
- No se introducen errores adicionales, y la función respeta las propiedades de los conjuntos difusos siempre que los parámetros de entrada sean válidos.