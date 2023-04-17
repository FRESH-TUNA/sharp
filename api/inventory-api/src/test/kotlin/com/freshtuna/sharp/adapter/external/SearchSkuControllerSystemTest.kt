package com.freshtuna.sharp.adapter.external

import com.freshtuna.sharp.StockApiApplication
import com.freshtuna.sharp.api.response.DataResponse
import com.freshtuna.sharp.config.const.Url
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
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
class SearchSkuControllerSystemTest {

    @Autowired
    lateinit var restTemplate: TestRestTemplate

    @Value("\${sharp.test.auth-token}")
    private lateinit var accessToken: String

    @Test
    @DisplayName("sku 검색 시스템 테스트")
    fun search() {

        /**
         * given
         */
        val query = "초코"

        val queryString = "?page=0&size=1&query=${query}"

        val headers = HttpHeaders()
        headers[HttpHeaders.AUTHORIZATION] = accessToken
        headers.contentType = MediaType.APPLICATION_JSON

        val request: HttpEntity<*> = HttpEntity<Any?>(headers)

        /**
         * when
         */
        val response = restTemplate.exchange(
            Url.EXTERNAL.SKU+queryString,
            HttpMethod.GET,
            request,
            DataResponse::class.java
        )

        val data = response.body!!.data as HashMap<String, String>

        /**
         * then
         */
        Assertions.assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
        Assertions.assertThat(1).isEqualTo(data["totalCount"])
        Assertions.assertThat(1).isEqualTo(data["count"])
    }
}
