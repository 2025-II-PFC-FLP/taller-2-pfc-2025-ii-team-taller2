# Informe de corrección — Función `igualdad`

## Argumentación de corrección del programa

### Función `igualdad`

#### Especificación

Sean dos conjuntos difusos 
$$( S_1, S_2 \subseteq U ) $$
representados por sus funciones características
$$( f_{S_1}, f_{S_2} : \mathbb{Z} \rightarrow [0,1] ).$$

La igualdad entre dos conjuntos difusos se define como:

$$[
S_1 = S_2 \iff (S_1 \subseteq S_2) \land (S_2 \subseteq S_1)
]$$

donde la inclusión difusa se define por:

$$[
S_1 \subseteq S_2 \iff \forall x \in U : f_{S_1}(x) \le f_{S_2}(x)
]$$

La función implementada fue:

```scala
def igualdad(cd1: ConjDifuso, cd2: ConjDifuso): Boolean = {
  inclusion(cd1, cd2) && inclusion(cd2, cd1)
}
```

---

### Especificación formal del programa

Queremos demostrar que para todo par de conjuntos difusos ( cd_1, cd_2 ):

$$[
P_{igualdad}(cd_1, cd_2) = f_{igualdad}(cd_1, cd_2)
]$$

donde

$$
f_{igualdad}(cd_1, cd_2) =
\begin{cases}
\text{true} & \text{si } (\forall n \in U: cd_1(n) = cd_2(n)) \
\text{false} & \text{en otro caso}
\end{cases}
]$$

---

## Demostración de corrección

### Paso 1: Análisis de `inclusion`

La función `inclusion` (usada por `igualdad`) verifica:

```scala
val elements = -10 to 10
elements.forall(n => cd1(n) <= cd2(n))
```

Formalmente:

$$[
P_{inclusion}(cd_1, cd_2) =
\begin{cases}
\text{true} & \text{si } \forall n \in [-10,10],\ cd_1(n) \le cd_2(n) \
\text{false} & \text{en otro caso}
\end{cases}
]$$

Esto corresponde exactamente a la definición de inclusión difusa en un dominio discreto finito ( U = [-10,10] ).

---

### Paso 2: Composición en `igualdad`

La función `igualdad` aplica:

$$[
P_{igualdad}(cd_1, cd_2) = P_{inclusion}(cd_1, cd_2) \land P_{inclusion}(cd_2, cd_1)
]$$

Por sustitución, sabemos que:

$$[
P_{inclusion}(cd_1, cd_2) = \forall n \in U: cd_1(n) \le cd_2(n)
]$$
$$[
P_{inclusion}(cd_2, cd_1) = \forall n \in U: cd_2(n) \le cd_1(n)
]$$

Al conjuntar ambos predicados mediante la conjunción lógica `&&`, se obtiene:

$$[
\forall n \in U: cd_1(n) = cd_2(n)
]$$

que es exactamente la definición matemática de igualdad difusa.

---

### Paso 3: Demostración formal

**Teorema:**
$$[
\forall cd_1, cd_2 \in (\mathbb{Z} \rightarrow [0,1]),\ P_{igualdad}(cd_1, cd_2) = f_{igualdad}(cd_1, cd_2)
]$$

**Prueba:**

1. Por la definición de `igualdad`, se evalúan dos inclusiones.
2. Por la corrección de `inclusion`, cada llamada devuelve `true` si y solo si
   ( \forall n \in U, cd_i(n) \le cd_j(n) ).
3. La conjunción `&&` devuelve `true` si ambas inclusiones son verdaderas, es decir,
   si ( cd_1(n) = cd_2(n) ) para todo ( n ).
4. Por tanto, `igualdad` devuelve `true` si y solo si los conjuntos son iguales.

$$[
\therefore\ P_{igualdad}(cd_1, cd_2) \equiv f_{igualdad}(cd_1, cd_2)
]$$

---

## Representación del flujo de llamadas

```mermaid
  A[Inicio igualdad(cd1, cd2)] --> B[Llamar inclusion(cd1, cd2)]
  B --> C[Resultado1 = true/false]
  C --> D[Llamar inclusion(cd2, cd1)]
  D --> E[Resultado2 = true/false]
  E --> F[Aplicar && entre ambos resultados]
  F --> G[Retornar Resultado Final]
```

---

## Conclusión

La función `igualdad` es **correcta** con respecto a su especificación matemática porque:

1. Usa la definición clásica de igualdad difusa: 
$$( S_1 = S_2 \iff S_1 \subseteq S_2 \land S_2 \subseteq S_1 ).$$
2. Implementa las inclusiones mediante funciones de alto orden (`forall`) sin estructuras imperativas.
3. No usa mutabilidad ni ciclos, respetando el paradigma funcional.

Por tanto, se cumple:

$$[
\forall cd_1, cd_2 : P_{igualdad}(cd_1, cd_2) == f_{igualdad}(cd_1, cd_2)
]$$
