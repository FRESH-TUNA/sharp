package com.freshtuna.sharp.adapter.external.item

import com.freshtuna.sharp.StockApiApplication
import com.freshtuna.sharp.config.const.Url
import com.freshtuna.sharp.price.Currency
import com.freshtuna.sharp.spec.DimensionScale
import com.freshtuna.sharp.spec.WeightScale
import com.freshtuna.sharp.api.response.DataResponse
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
import java.time.LocalDateTime

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    classes = [StockApiApplication::class]
)
@ActiveProfiles("test")
class NewItemControllerSystemTest {

    @Autowired
    lateinit var restTemplate: TestRestTemplate

    @Value("\${sharp.test.auth-token}")
    private lateinit var accessToken: String

    @Test
    @DisplayName("신규 아이템 테스트")
    fun test() {
        /**
         * given
         */
        val requestBody = mapOf(
            "name" to "바바고 만두 500g",
            "category" to "FOOD",
            "description" to "맛있는 만두",

            "sku" to mapOf(
                "skuId" to "바바고/만두/500g",
                "barcode" to "4358345843572",
                "description" to "",

                "cost" to "5000",
                "currency" to Currency.KRW.name,

                "weight" to "100",
                "weightScale" to WeightScale.GRAM.name,

                "width" to "6",
                "height" to "6",
                "depth" to "8",
                "dimensionScale" to DimensionScale.CM.name,

                "expireDate" to LocalDateTime.of(2030, 1, 1, 0, 0, 0).toString(),
                "manufactureDate" to LocalDateTime.now().toString()
            )
        )

        val headers = HttpHeaders()
        headers[HttpHeaders.AUTHORIZATION] = accessToken
        headers.contentType = MediaType.APPLICATION_JSON
        val entity = HttpEntity(requestBody, headers)

        /**
         * when
         */
        val response = restTemplate.postForEntity(
            Url.EXTERNAL.ITEM, entity, DataResponse::class.java)

        /**
         * then
         */
        Assertions.assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
    }
}
