package com.freshtuna.sharp.adapter.external.item

import com.freshtuna.sharp.StockApiApplication
import com.freshtuna.sharp.api.response.BasicResponse
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    classes = [StockApiApplication::class]
)
@ActiveProfiles("test")
class InventoryFromItemControllerSystemTest {

    @Autowired
    lateinit var restTemplate: TestRestTemplate

    @Value("\${sharp.test.auth-token}")
    private lateinit var accessToken: String

    @Test
    @DisplayName("아이템 재고 출고 시스템 테스트")
    fun stockOut() {

        /**
         * given
         */
        val requestBody = mapOf(
            "reason" to "SHIPPED",
            "count" to 1,
            "description" to "출고 테스트"
        )

        val headers = HttpHeaders()
        headers[HttpHeaders.AUTHORIZATION] = accessToken
        headers.contentType = MediaType.APPLICATION_JSON
        val entity = HttpEntity(requestBody, headers)

        /**
         * when
         */
        val response1 = restTemplate.postForEntity(
            "/items/4/out", entity, BasicResponse::class.java)

        val response2 = restTemplate.postForEntity(
            "/items/5/out", entity, BasicResponse::class.java)

        /**
         * then
         */
        Assertions.assertThat(response1.statusCode).isEqualTo(HttpStatus.OK)
        Assertions.assertThat(response2.statusCode).isEqualTo(HttpStatus.OK)
    }

    @Test
    @DisplayName("콤보 아이템 재고 출고 시스템 테스트")
    fun comboStockOut() {

        /**
         * given
         */
        val requestBody = mapOf(
            "reason" to "SHIPPED",
            "count" to 1,
            "description" to "출고 테스트"
        )

        val headers = HttpHeaders()
        headers[HttpHeaders.AUTHORIZATION] = accessToken
        headers.contentType = MediaType.APPLICATION_JSON
        val entity = HttpEntity(requestBody, headers)

        /**
         * when
         */
        val response = restTemplate.postForEntity(
            "/items/7/out", entity, BasicResponse::class.java)

        /**
         * then
         */
        Assertions.assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
    }
}
