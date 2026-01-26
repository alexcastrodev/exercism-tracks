Quando eu coloco

```
class LicensesController(
    private val licenseService: LicenseService
)
```

ele infere `open`. 

1 - O que é open?

Toda classe é final, e não pode ser herdada. 

["By default, Kotlin classes are final – they can't be inherited. To make a class inheritable, mark it with the open keyword"](https://kotlinlang.org/docs/inheritance.html)


Ref: https://stackoverflow.com/questions/49024200/what-is-the-difference-between-open-and-public-in-kotlin

2 - Por que na controller do spring já deixou `open` explícito ? (Modality is changed from final to 'open' by a Kotlin compiler plugin)

Pelo código, é considerado final, e mantém a regra do Kotlin. Mas o @RestController modifica isso com o plugin all-open.

["For instance, when you use Spring, you don't need all the classes to be open, but only classes annotated with specific annotations like @Configuration or @Service. The all-open plugin allows you to specify such annotations."](https://kotlinlang.org/docs/all-open-plugin.html)

Também diz:

"Thanks to meta-annotations support, classes annotated with @Configuration, @Controller, @RestController, @Service or @Repository are automatically opened since these annotations are meta-annotated with @Component."
