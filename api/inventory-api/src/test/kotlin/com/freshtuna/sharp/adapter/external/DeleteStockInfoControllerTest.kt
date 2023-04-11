package com.freshtuna.sharp.adapter.external

import com.freshtuna.sharp.StockApiApplication
import com.freshtuna.sharp.api.response.BasicResponse
import com.freshtuna.sharp.config.const.Url
import com.freshtuna.sharp.inventory.StockRequestStatus
import com.freshtuna.sharp.inventory.StockStatus
import com.freshtuna.sharp.inventory.entity.MariaDBSKU
import com.freshtuna.sharp.inventory.entity.MariaDBStock
import com.freshtuna.sharp.inventory.entity.MariaDBStockInfo
import com.freshtuna.sharp.inventory.repository.SKURepository
import com.freshtuna.sharp.inventory.repository.stock.StockInfoRepository
import com.freshtuna.sharp.inventory.repository.stock.StockRepository
import io.mockk.InternalPlatformDsl.toStr
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
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
class DeleteStockInfoControllerTest {

    @Autowired
    lateinit var restTemplate: TestRestTemplate

    @Autowired
    private lateinit var stockInfoRepository: StockInfoRepository

    @Autowired
    private lateinit var stockRepository: StockRepository

    @Autowired
    private lateinit var skuRepository: SKURepository

    @Value("\${sharp.test.auth-token}")
    private lateinit var accessToken: String

    @Test
    fun delete() {
        /**
         * given
         */
        val skuId = 1L
        val sku = skuRepository.findById(skuId).get()
        val stockInfo = createStockInfo(sku)
        val stockCount = 10L
        val stocks = createStocks(sku, stockInfo, stockCount)

        /**
         * when
         */
        val headers = HttpHeaders()
        headers[HttpHeaders.AUTHORIZATION] = accessToken
        headers.contentType = MediaType.APPLICATION_JSON
        val entity = HttpEntity(null, headers)

        val response = restTemplate.exchange(
            "/inventory/stock-info/"+stockInfo.id.toStr(),
            HttpMethod.DELETE,
            entity,
            BasicResponse::class.java
        )

        /**
         * then
         */
        Assertions.assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
        Assertions.assertThat(stockInfoRepository.findById(stockInfo.id).isPresent).isEqualTo(false)
        Assertions.assertThat(
            stockRepository.findAllById(stocks.map { s -> s.id }.toList()).isEmpty()).isEqualTo(true)
    }

    private fun createStockInfo(sku: MariaDBSKU): MariaDBStockInfo {
        return stockInfoRepository.save(
            MariaDBStockInfo(
                sku = sku,
                requestStatus = StockRequestStatus.COMPLETE,
                hasExpire = true,
                expireDate = LocalDateTime.now(),
                hasManufacture = true,
                manufactureDate = LocalDateTime.now()
            )
        )
    }

    private fun createStocks(sku: MariaDBSKU,
                             stockInfo: MariaDBStockInfo,
                             count: Long): List<MariaDBStock> {

        return stockRepository.saveAll(
            List(count.toInt()) { MariaDBStock(sku, StockStatus.AVAILABLE, stockInfo) })
    }
}
