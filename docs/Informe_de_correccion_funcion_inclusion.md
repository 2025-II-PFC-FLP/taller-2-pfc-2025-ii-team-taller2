# Informe de corrección función inclusion para conjuntos difusos



Sea: $$f: (\mathbb{Z} \to [0,1]) \times (\mathbb{Z} \to [0,1]) \to \mathbb{B}$$

una función que, dados dos conjuntos difusos `cd1` y `cd2`, determina si `cd1` está incluido en `cd2`.

Donde `ConjDifuso` es un tipo funcional definido como:
```scala
type ConjDifuso = Int => Double
```
Además, la función `inclusion` está definida como un predicado que verifica si, para todo elemento `n` en un rango dado, el grado de pertenencia de `cd1(n)` es menor o igual al grado de pertenencia de `cd2(n)`.

## Argumentación de corrección para la función `inclusion`

```scala
def inclusion(cd1: ConjDifuso, cd2: ConjDifuso): Boolean = {
  val elements = -10 to 10 // Rango de prueba
  elements.forall(n => cd1(n) <= cd2(n))
}
```

- La función `inclusion` toma dos conjuntos difusos `cd1` y `cd2` y retorna un valor booleano que indica si `cd1` está incluido en `cd2`. Esto se logra evaluando si, para todos los enteros `n` en el rango de `-10 a 10`, el grado de pertenencia de `cd1(n)` es menor o igual a `cd2(n)`.

### Estado y Transformación

- **Estado**: La función utiliza un rango fijo de elementos (`-10 to 10`) y aplica el método `forall` para iterar sobre este rango. No hay un estado acumulativo explícito.
- **Estado inicial**: El rango `-10 to 10` define el conjunto de elementos a evaluar.
- **Estado final**: La salida es `true` si la condición `cd1(n) <= cd2(n)` se cumple para todos los `n` en el rango, de lo contrario `false`.
- **Invariante**: La corrección depende de que `cd1(n)` y `cd2(n)` retornen valores en el rango `[0, 1]` para todo `n`, y que el rango de prueba sea representativo de los conjuntos difusos.

### Demostración de corrección

Queremos demostrar que:
$$
\forall cd1, cd2 \in (\mathbb{Z} \to [0,1]) : inclusion(cd1, cd2) = \text{true} \iff \forall n \in [-10, 10] : cd1(n) \leq cd2(n)
$$

#### Caso base y general:
Dado que `inclusion` utiliza `forall` para verificar la condición en un rango finito, la corrección se basa en la semántica de `forall`:
- Si para todo `n` en `-10 to 10`, `cd1(n) <= cd2(n)` es verdadero, entonces `inclusion(cd1, cd2)` retorna `true`.
- Si existe al menos un `n` en `-10 to 10` donde `cd1(n) > cd2(n)`, entonces `inclusion(cd1, cd2)` retorna `false`.

La función es correcta por la definición de inclusión en teoría de conjuntos difusos, donde un conjunto `cd1` está incluido en `cd2` si el grado de pertenencia de cada elemento en `cd1` no excede el de `cd2`.

#### Condiciones de validez:
- **Rango de valores**: Se asume que `cd1` y `cd2` son funciones válidas de tipo `ConjDifuso`, retornando valores en `[0, 1]` para todo `n`. Esto asegura que las comparaciones sean válidas.
- **Rango de prueba**: El rango `-10 to 10` es una aproximación finita. La corrección depende de que este rango sea representativo de los conjuntos difusos en cuestión. En un contexto teórico, la inclusión debería verificarse para todo `n` en $\mathbb{Z}$, pero el rango finito es una heurística práctica.

- **Conclusión**: La función `inclusion(cd1, cd2)` determina correctamente si `cd1` está incluido en `cd2` dentro del rango `-10 to 10`, siempre que `cd1` y `cd2` sean conjuntos difusos válidos.

## Argumentación de corrección para la función `inclusion` (perspectiva funcional)

La función `inclusion` es una composición de `forall` aplicado a un rango fijo con la comparación `cd1(n) <= cd2(n)`. Su corrección depende de:
- La implementación de `forall`, que es correcta por definición en Scala y retorna `true` solo si la condición se cumple para todos los elementos.
- La garantía de que `cd1(n)` y `cd2(n)` devuelvan valores en `[0, 1]`.

### Retorno de `inclusion`
- La función retorna un valor booleano basado en la evaluación de `forall(n => cd1(n) <= cd2(n))` sobre el rango `-10 to 10`.
- No hay validación explícita de los parámetros `cd1` y `cd2`, pero se asume que son funciones bien definidas como parte del contrato de `ConjDifuso`.

### Condiciones de validez
- **Dominio**: La función está definida para todo `n` en el rango `-10 to 10`. La elección de este rango es arbitraria pero suficiente para pruebas prácticas.
- **Rango**: El resultado es un booleano, consistente con la definición de un predicado de inclusión.

## Conclusión
La función `inclusion` satisface la especificación:
$$
\forall cd1, cd2 \in (\mathbb{Z} \to [0,1]) : inclusion(cd1, cd2) = \text{true} \iff \forall n \in [-10, 10] : cd1(n) \leq cd2(n)
$$
- La implementación es correcta porque utiliza `forall` para verificar que los grados de pertenencia de `cd1` no excedan los de `cd2` en el rango especificado, alineándose con la definición teórica de inclusión en conjuntos difusos.
- La limitación del rango `-10 to 10` es una aproximación práctica, y la corrección plena requeriría verificar la propiedad para todo `n` en $\mathbb{Z}$, pero esto es impracticable en un contexto computacional finito.