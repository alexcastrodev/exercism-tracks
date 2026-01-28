interface DataProvider {
    fun provideData(): String
}

object DataProviderManager {
    private val providers = mutableListOf<DataProvider>()

    fun register(provider: DataProvider) {
        providers.add(provider)
    }

    val all: Collection<DataProvider> get() = providers
}

class ExampleDataProvider : DataProvider {
    override fun provideData(): String {
        return "Alex"
    }
}

val ref1 = DataProviderManager
val ref2 = DataProviderManager
println("referencia: ${ref1 == ref2}")

val provider = ExampleDataProvider()
DataProviderManager.register(provider)

println(DataProviderManager.all.map { it.provideData() })

