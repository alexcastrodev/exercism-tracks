A diferença do [HashMap](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin.collections/hash-map-of.html) para o [Map](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin.collections/-map/) é que hashMap não garante a ordem das chaves dentro da collection.

Exemplo de uso:

```
val peoples = HashMap<Int, String> = HashMap()

peoples.put(1, "person 1")
peoples.put(2, "person 2")
peoples.put(3, "person 3")
peoples.put(4, "person 4")

println(peoples) 
# {1=person 1, 2=person 2, 3=person 3, 4=person 4}
```

Também posso inicializar com N Elementos:

```
val peoples: HashMap<Int, String> = hashMapOf(3 to "person 3", 4 to "person 4")

peoples.put(1, "person 1")
peoples.put(2, "person 2")
peoples.put(3, "person 3")

println(peoples) 
# {1=person 1, 2=person 2, 3=person 3, 4=person 4}
```


