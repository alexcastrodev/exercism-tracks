
Kotlin tem duas formas de reusar objetos criados uma só vez, ou objetos singleton.

- Object declaration (singletons)

- Object Expression ( one time object - Data Objects )

# Singleton

"A singleton ensures that a class has only one instance and provides a global point of access to it."

Você quer instanciar um client, somente uma vez, para que ele não crie sempre um novo objeto.

[Exemplo](./proofs/Singleton.kts)
```
// root@3035fa7902f9:/workspace/kotlin/notes/proofs# kotlinc -script Singleton.kts 
// [Alex]
```

Assim como declaração de variáveis, o Object declaration não são expressões, então não pode ser associado a uma variável

```
val teste = object MySingleton() {
    val name = "singleton"
}
```

Ref: https://kotlinlang.org/docs/object-declarations.html#data-objects


# Data Objects



Ref: https://kotlinlang.org/docs/object-declarations.html#data-objects