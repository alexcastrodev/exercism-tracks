import org.junit.Test
import org.junit.Assert.*

class OrderAnalyzerTest {

    private val phone = Product(1, "Phone", "Electronics", 1000.0)
    private val laptop = Product(2, "Laptop", "Electronics", 2000.0)
    private val book = Product(3, "Book", "Books", 50.0)

    private val orders = listOf(
        Order(
            id = 1,
            client = "Alice",
            products = listOf(phone, book)
        ),
        Order(
            id = 2,
            client = "Bob",
            products = listOf(laptop)
        ),
        Order(
            id = 3,
            client = "Alice",
            products = listOf(phone, laptop)
        )
    )

    private val analyzer = OrderAnalyzer()

    @Test
    fun `should return distinct categories`() {
        val categories = analyzer.distinctCategories(orders)

        assertEquals(2, categories.size)
        assertTrue(categories.contains("Electronics"))
        assertTrue(categories.contains("Books"))
    }

    @Test
    fun `should return distinct clients`() {
        val clients = analyzer.distinctClients(orders)

        assertEquals(2, clients.size)
        assertTrue(clients.contains("Alice"))
        assertTrue(clients.contains("Bob"))
    }

    @Test
    fun `should count products sold by category`() {
        val result = analyzer.productsSoldByCategory(orders)

        assertEquals(3, result["Electronics"])
        assertEquals(1, result["Books"])
    }

    @Test
    fun `should calculate revenue by client`() {
        val revenue = analyzer.revenueByClient(orders)

        assertEquals(4050.0, revenue["Alice"]!!, 0.001)
        assertEquals(2000.0, revenue["Bob"]!!, 0.001)
    }

    @Test
    fun `should return products by client without duplicates`() {
        val productsByClient = analyzer.productsByClient(orders)

        val aliceProducts = productsByClient["Alice"]!!
        val bobProducts = productsByClient["Bob"]!!

        assertEquals(3, aliceProducts.size)
        assertTrue(aliceProducts.contains("Phone"))
        assertTrue(aliceProducts.contains("Laptop"))
        assertTrue(aliceProducts.contains("Book"))

        assertEquals(1, bobProducts.size)
        assertTrue(bobProducts.contains("Laptop"))
    }
}
