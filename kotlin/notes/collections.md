# Collections

## 25/01/26

Todas collections do Kotlin são read-only por padrão.

Por exemplo, se você tem uma lista:

```kotlin
val list: List<Int> = listOf(1,2,3)
```

Ela é read-only, não expõe métodos add, remove, e tal.
Você não consegue modificar através de referência.

Mas, não garante imutabilidade.

[Exemplo ReadonlyExample](./proofs/ReadonlyExample.kts)

```bash
root@2bd5246b0ffa:/workspace/kotlin/proofs# kotlinc -script ReadonlyExample.kts
[1, 2, 3, 4]
```

Existem também as MutableList, MutableSet e MutableMap. Elas implementam os métodos para modificar as listas.

Toda Collection estende a implementação de iterators

![image](https://kotlinlang.org/docs/images/collections-diagram.png)

Ref: https://kotlinlang.org/docs/collections-overview.html#collection-types
