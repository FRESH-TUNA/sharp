package com.freshtuna.sharp.adapter.external.sku

import com.freshtuna.sharp.StockApiApplication
import com.freshtuna.sharp.api.response.BasicResponse

import com.freshtuna.sharp.config.const.Url
import com.freshtuna.sharp.price.Currency
import com.freshtuna.sharp.spec.DimensionScale
import com.freshtuna.sharp.spec.WeightScale
import org.assertj.core.api.Assertions
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
class UpdateSkuControllerSystemTest {

    @Autowired
    lateinit var restTemplate: TestRestTemplate

    @Value("\${sharp.test.auth-token}")
    private lateinit var accessToken: String

    @Test
    fun update() {

        /**
         * given
         */
        val skuId = "/5"
        val requestBody = mapOf(
            "name" to "머니메이드 주스 자몽맛",
            "barcode" to "846722345234",
            "description" to "",

            "cost" to "3500",
            "currency" to Currency.KRW.name,

            "weight" to "500",
            "weightScale" to WeightScale.GRAM.name,

            "width" to "10",
            "height" to "10",
            "depth" to "30",
            "dimensionScale" to DimensionScale.CM.name
        )

        val headers = HttpHeaders()
        headers[HttpHeaders.AUTHORIZATION] = accessToken
        headers.contentType = MediaType.APPLICATION_JSON
        val entity = HttpEntity(requestBody, headers)

        /**
         * when
         */

        val response = restTemplate.exchange(
            Url.EXTERNAL.SKU+skuId,
            HttpMethod.PUT,
            entity,
            BasicResponse::class.java
        )

        /**
         * then
         */
        Assertions.assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
    }
}
