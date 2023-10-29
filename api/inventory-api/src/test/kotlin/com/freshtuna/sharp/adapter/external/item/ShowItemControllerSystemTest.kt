package com.freshtuna.sharp.adapter.external.item

import com.freshtuna.sharp.StockApiApplication
import com.freshtuna.sharp.api.response.DataResponse
import com.freshtuna.sharp.config.const.Url
import org.assertj.core.api.Assertions
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
class ShowItemControllerSystemTest {

    @Autowired
    lateinit var restTemplate: TestRestTemplate

    @Value("\${sharp.test.auth-token}")
    private lateinit var accessToken: String

    @Test
    @DisplayName("아이템 상세 시스템 테스트")
    fun detail() {
        /**
         * given
         */
        val itemId = "/1"

        /**
         * when
         */
        val headers = HttpHeaders()
        headers[HttpHeaders.AUTHORIZATION] = accessToken
        headers.contentType = MediaType.APPLICATION_JSON
        val entity = HttpEntity(null, headers)

        val response = restTemplate.exchange(
            Url.EXTERNAL.ITEM+itemId,
            HttpMethod.GET,
            entity,
            DataResponse::class.java
        )

        /**
         * then
         */
        Assertions.assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
    }

    @Test
    @DisplayName("콤보 아이템 상세 시스템 테스트")
    fun detailCombo() {
        /**
         * given
         */
        val itemId = "/7"

        /**
         * when
         */
        val headers = HttpHeaders()
        headers[HttpHeaders.AUTHORIZATION] = accessToken
        headers.contentType = MediaType.APPLICATION_JSON
        val entity = HttpEntity(null, headers)

        val response = restTemplate.exchange(
            Url.EXTERNAL.ITEM+itemId,
            HttpMethod.GET,
            entity,
            DataResponse::class.java
        )

        /**
         * then
         */
        Assertions.assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
    }
}
