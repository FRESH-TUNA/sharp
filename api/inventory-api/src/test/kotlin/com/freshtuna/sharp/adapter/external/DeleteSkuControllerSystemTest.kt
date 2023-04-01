package com.freshtuna.sharp.adapter.external

import com.freshtuna.sharp.StockApiApplication
import com.freshtuna.sharp.api.response.BasicResponse
import com.freshtuna.sharp.api.response.DataResponse
import com.freshtuna.sharp.config.const.Url

import com.freshtuna.sharp.inventory.incoming.NewSkuUseCase

import com.freshtuna.sharp.price.Currency
import com.freshtuna.sharp.spec.*
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
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
class DeleteSkuControllerSystemTest {

    @Autowired
    lateinit var restTemplate: TestRestTemplate

    @Autowired
    lateinit var newSkuUseCase: NewSkuUseCase

    @Value("\${sharp.test.auth-token}")
    private lateinit var accessToken: String

    @Test
    fun delete() {
        /**
         * given
         */
        val skuId = "/"+createSku()

        /**
         * when
         */
        val headers = HttpHeaders()
        headers[HttpHeaders.AUTHORIZATION] = accessToken
        headers.contentType = MediaType.APPLICATION_JSON
        val entity = HttpEntity(null, headers)

        val response = restTemplate.exchange(
            Url.EXTERNAL.SKU+skuId,
            HttpMethod.DELETE,
            entity,
            BasicResponse::class.java
        )

        /**
         * then
         */
        Assertions.assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
    }

    /**
     * helpers
     */
    fun createSku(): String {

        val requestBody = mapOf(
            "name" to "패닉카카오 100g",
            "barcode" to "4358345843572",
            "description" to "",

            "cost" to "5000",
            "currency" to Currency.KRW.name,

            "weight" to "100",
            "weightScale" to WeightScale.GRAM.name,

            "width" to "6",
            "height" to "6",
            "depth" to "8",
            "dimensionScale" to DimensionScale.CM.name
        )

        val headers = HttpHeaders()
        headers[HttpHeaders.AUTHORIZATION] = accessToken
        headers.contentType = MediaType.APPLICATION_JSON
        val entity = HttpEntity(requestBody, headers)

        val response = restTemplate.postForEntity(
            Url.EXTERNAL.SKU, entity, DataResponse::class.java)


        /**
         * cleaning
         */
        val data = response.body!!.data as LinkedHashMap<String, String>
        return data["id"]!!
    }
}
