package com.freshtuna.sharp.adapter.external

import com.freshtuna.sharp.StockApiApplication
import com.freshtuna.sharp.api.response.BasicResponse
import com.freshtuna.sharp.api.response.DataResponse
import com.freshtuna.sharp.config.const.Url
import com.freshtuna.sharp.inventory.repository.SKURepository
import com.freshtuna.sharp.inventory.repository.StockRepository
import com.freshtuna.sharp.price.Currency
import com.freshtuna.sharp.spec.DimensionScale
import com.freshtuna.sharp.spec.WeightScale
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
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
class StockInControllerSystemTest {

    @Autowired
    lateinit var restTemplate: TestRestTemplate

    @Value("\${sharp.test.auth-token}")
    private lateinit var accessToken: String

    @Test
    @DisplayName("재고 입고 시스템 테스트")
    fun stockIn() {
        /**
         * given
         */
        val requestBody = mapOf(
            "skuId" to "1",
            "count" to 3
        )

        val headers = HttpHeaders()
        headers[HttpHeaders.AUTHORIZATION] = accessToken
        headers.contentType = MediaType.APPLICATION_JSON
        val entity = HttpEntity(requestBody, headers)

        /**
         * when
         */
        val response = restTemplate.postForEntity(
            Url.EXTERNAL.STOCK, entity, BasicResponse::class.java)

        /**
         * then
         */
        Assertions.assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
    }
}
