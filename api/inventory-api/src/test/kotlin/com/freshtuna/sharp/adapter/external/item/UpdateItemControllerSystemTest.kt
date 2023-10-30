package com.freshtuna.sharp.adapter.external.item

import com.freshtuna.sharp.StockApiApplication
import com.freshtuna.sharp.api.response.BasicResponse
import com.freshtuna.sharp.api.response.DataResponse
import com.freshtuna.sharp.config.const.Url
import com.freshtuna.sharp.price.Currency
import com.freshtuna.sharp.spec.DimensionScale
import com.freshtuna.sharp.spec.WeightScale
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.*
import org.springframework.test.context.ActiveProfiles
import java.time.LocalDateTime

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    classes = [StockApiApplication::class]
)
@ActiveProfiles("test")
class UpdateItemControllerSystemTest {

    @Autowired
    lateinit var restTemplate: TestRestTemplate

    @Value("\${sharp.test.auth-token}")
    private lateinit var accessToken: String

    @Test
    @DisplayName("아이템 업데이트 테스트")
    fun test() {
        /**
         * given
         */
        val requestBody = mapOf(
            "name" to "파카리스웨트",
            "category" to "FOOD",
            "description" to "맛있는 피자",

            "sku" to mapOf(
                "skuId" to "파카리스웨트",
                "barcode" to "4928428239420",
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
        val response = restTemplate.exchange(
            Url.EXTERNAL.ITEM+"/1",
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