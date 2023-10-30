package com.freshtuna.sharp.adapter.external.sku

import com.freshtuna.sharp.StockApiApplication
import com.freshtuna.sharp.api.response.DataResponse
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.*
import org.springframework.test.context.ActiveProfiles

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    classes = [StockApiApplication::class]
)
@ActiveProfiles("test")
class SearchSkuInventoryLogsControllerTest {

    @Autowired
    lateinit var restTemplate: TestRestTemplate

    @Value("\${sharp.test.auth-token}")
    private lateinit var accessToken: String

    @Test
    @DisplayName("sku 재고 정보 조회 테스트")
    fun search() {

        /**
         * given
         */
        val headers = HttpHeaders()
        headers[HttpHeaders.AUTHORIZATION] = accessToken
        headers.contentType = MediaType.APPLICATION_JSON

        val request: HttpEntity<*> = HttpEntity<Any?>(headers)

        /**
         * when
         */
        val url = "/skus/1/logs?page=0&size=2"

        val response = restTemplate.exchange(
            url,
            HttpMethod.GET,
            request,
            DataResponse::class.java
        )


        /**
         * then
         */
        Assertions.assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
    }
}
