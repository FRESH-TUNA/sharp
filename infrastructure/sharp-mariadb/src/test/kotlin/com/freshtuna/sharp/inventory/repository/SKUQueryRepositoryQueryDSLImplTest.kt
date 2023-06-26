package com.freshtuna.sharp.inventory.repository

import com.freshtuna.sharp.config.JPAQueryFactoryBeanConfig
import com.freshtuna.sharp.entity.MariaDBSeller
import com.freshtuna.sharp.entity.repository.SellerRepository
import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.inventory.command.sku.SearchSkuCommand
import com.freshtuna.sharp.inventory.entity.MariaDBSKU
import com.freshtuna.sharp.inventory.repository.sku.SKUQueryRepositoryQueryDSLImpl
import com.freshtuna.sharp.inventory.repository.sku.SKURepository
import com.freshtuna.sharp.page.SharpPageRequest
import com.freshtuna.sharp.page.SharpSort
import com.freshtuna.sharp.price.Currency
import com.freshtuna.sharp.price.entity.MariaDBPrice
import com.freshtuna.sharp.spec.DimensionScale
import com.freshtuna.sharp.spec.WeightScale
import com.freshtuna.sharp.spec.entity.MariaDBDimension
import com.freshtuna.sharp.spec.entity.MariaDBSpecification
import com.freshtuna.sharp.spec.entity.MariaDBWeight
import org.junit.jupiter.api.AfterAll

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*

@DataJpaTest
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(JPAQueryFactoryBeanConfig::class, SKUQueryRepositoryQueryDSLImpl::class)
class SKUQueryRepositoryQueryDSLImplTest {

    @Autowired
    private lateinit var skuQueryRepository: SKUQueryRepositoryQueryDSLImpl

    @Autowired
    private lateinit var skuRepository: SKURepository

    @Autowired
    private lateinit var sellerRepository: SellerRepository

    private val sellerPublicId = SharpID(UUID.randomUUID())

    private var sellerId: SharpID? = null

    @BeforeAll
    fun create() {
        val seller = createTestSeller()

        sellerId = SharpID(seller.id)

        createTestSKUS(seller)
    }

    @AfterAll
    fun destroy() {
        skuRepository.deleteAll()
        sellerRepository.deleteAll()
    }

    @Test
    @DisplayName("페이지 테스트")
    fun pageTest() {
        /**
         * given
         */
        val pageSize = 2L
        val pageNumber = 0L
        val command = SearchSkuCommand(
            sharpPageRequest = SharpPageRequest(pageNumber, pageSize, SharpSort()))

        /**
         * when
         */
        val results = skuQueryRepository.search(command, sellerId!!)

        /**
         * then
         */
        assertEquals(pageNumber, results.pageNumber)
        assertEquals(pageSize, results.count)

        assertEquals(results.totalCount/pageSize, results.totalPageCount)
    }

    @Test
    @DisplayName("sku 이름으로 쿼리 테스트")
    fun queryNameTest() {
        /**
         * given
         */
        val query = "초코"
        val pageSize = 2L
        val command = SearchSkuCommand(
            query = query,
            sharpPageRequest = SharpPageRequest(0, pageSize, SharpSort())
        )

        /**
         * when
         */
        val results = skuQueryRepository.search(command, sellerId!!)

        /**
         * then
         */
        assertEquals(results.page.size, 2)

        results.page.stream().forEach {
            data -> assertEquals(true, data.name.startsWith(query))
        }
    }

    /**
     * helpers
     */
    fun createTestSeller(): MariaDBSeller {
        return sellerRepository.save(MariaDBSeller(sellerPublicId.uuid()))
    }
    fun createTestSKUS(seller: MariaDBSeller): List<MariaDBSKU> {

        val skus = listOf(
            MariaDBSKU(
                seller,
                name = "초코칩",
                barcode = "12",
                description = "description",
                price = MariaDBPrice(BigDecimal.TEN, Currency.KRW),
                specification = MariaDBSpecification(
                    MariaDBWeight(BigDecimal.TEN, WeightScale.GRAM),
                    MariaDBDimension(BigDecimal.TEN, BigDecimal.TEN, BigDecimal.TEN, DimensionScale.CM)
                ),
                expireDate = LocalDateTime.now(),
                manufactureDate = LocalDateTime.now()
            ),
            MariaDBSKU(
                seller,
                name = "초코바",
                barcode = "barcode",
                description = "description",
                price = MariaDBPrice(BigDecimal.TEN, Currency.KRW),
                specification = MariaDBSpecification(
                    MariaDBWeight(BigDecimal.TEN, WeightScale.GRAM),
                    MariaDBDimension(BigDecimal.TEN, BigDecimal.TEN, BigDecimal.TEN, DimensionScale.CM)
                ),
                expireDate = LocalDateTime.now(),
                manufactureDate = LocalDateTime.now()
            ),
            MariaDBSKU(
                seller,
                name = "초장",
                barcode = "barcode",
                description = "description",
                price = MariaDBPrice(BigDecimal.TEN, Currency.KRW),
                specification = MariaDBSpecification(
                    MariaDBWeight(BigDecimal.TEN, WeightScale.GRAM),
                    MariaDBDimension(BigDecimal.TEN, BigDecimal.TEN, BigDecimal.TEN, DimensionScale.CM)
                ),
                expireDate = LocalDateTime.now(),
                manufactureDate = LocalDateTime.now()
            )
        )
        return skuRepository.saveAll(skus)
    }
}
