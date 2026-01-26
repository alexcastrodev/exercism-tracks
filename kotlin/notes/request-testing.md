# Setup inicial

A principio, parece ser bem simples, utilizando no spring.


Por exemplo, eu posso criando meu teste com a classe:

```
class LicenseControllerTests {}
```

Adiciona o metódo, por exemplo eu quero testar o get paginado de uma api


```
class LicenseControllerTests {
    fun givenLicenses_whenGetRequest_ReturnsLicensesPaginated() {}
}
```

bem estilo javinha. Agora eu preciso indicar que vai rodar o [SpringBootTest](https://spring.io/guides/gs/testing-web)

```
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureRestTestClient
class LicenseControllerTests {
    fun givenLicenses_whenGetRequest_ReturnsLicensesPaginated() {}
}
```

O meu primeiro teste é buscar a lista paginada, e baseando na doc oficial, ele deveria ficar assim

```
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureRestTestClient
class LicensesControllerTest(@Autowired private val restTestClient: RestTestClient) {

    @LocalServerPort
    private var port: Int = 0

    @Test
    fun givenLicenses_whenGetRequest_ReturnsLicensesPaginated() {
        restTestClient.get()
            .uri("http://localhost:$port/licenses")
            .exchange()
            .expectStatus().isOk()
            .expectBody<LicenseListResponse>()
    }
}
```

Como eu já tenho a conexão com banco no meu controller, eu preciso subir os container com [TestContainer](https://docs.spring.io/spring-boot/reference/testing/testcontainers.html)


```
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureRestTestClient
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Import(TestcontainersConfiguration::class)
class LicensesControllerTest(@Autowired private val restTestClient: RestTestClient) {
```

Vamos criar um arquivo de configuração:

```
package com.example.app

import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.springframework.context.annotation.Bean
import org.testcontainers.containers.GenericContainer
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.utility.DockerImageName

@TestConfiguration(proxyBeanMethods = false)
class TestcontainersConfiguration {

    @Bean
    @ServiceConnection
    fun postgresContainer(): PostgreSQLContainer<*> {
        return PostgreSQLContainer(DockerImageName.parse("postgres:18"))
    }

    @Bean
    @ServiceConnection(name = "redis")
    fun redisContainer(): GenericContainer<*> {
        return GenericContainer(DockerImageName.parse("valkey/valkey:8.1.4"))
            .withExposedPorts(6379)
    }
}
```

Meu test completo agora ficou

```
package com.impic.app.controller

import com.impic.app.TestcontainersConfiguration
import com.impic.app.dto.LicenseListResponse
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.context.annotation.Import
import org.springframework.test.web.servlet.client.RestTestClient
import org.springframework.test.web.servlet.client.expectBody

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureRestTestClient
@Import(TestcontainersConfiguration::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class LicensesControllerTest(@Autowired private val restTestClient: RestTestClient) {

    @LocalServerPort
    private var port: Int = 0

    @Value("\${app.api-key}")
    private val apiKey: String = ""

    @Test
    fun givenLicenses_whenGetRequest_ReturnsLicensesPaginated() {
        restTestClient.get()
            .uri("http://localhost:$port/licenses")
            .header("X-Api-Key", apiKey)
            .exchange()
            .expectStatus().isOk()
            .expectBody<LicenseListResponse>()
            .consumeWith { response ->
                var body = response.responseBody

                assertThat(body?.meta?.total).isEqualTo(0)
                assertThat(body?.meta?.limit).isEqualTo(20) // Default
                assertThat(body?.meta?.offset).isEqualTo(0) // Default
            }
    }
}
```