package com.freshtuna.sharp.inventory.repository

import com.freshtuna.sharp.entity.MariaDBSeller
import com.freshtuna.sharp.entity.repository.SellerRepository
import com.freshtuna.sharp.inventory.domain.inventory.InventoryStatus
import com.freshtuna.sharp.inventory.entity.MariaDBInventory
import com.freshtuna.sharp.inventory.entity.MariaDBSKU
import com.freshtuna.sharp.inventory.repository.inventory.InventoryRepository
import com.freshtuna.sharp.inventory.repository.sku.SKURepository
import com.freshtuna.sharp.price.Currency
import com.freshtuna.sharp.price.entity.MariaDBPrice
import com.freshtuna.sharp.spec.DimensionScale
import com.freshtuna.sharp.spec.WeightScale
import com.freshtuna.sharp.spec.entity.MariaDBDimension
import com.freshtuna.sharp.spec.entity.MariaDBSpecification
import com.freshtuna.sharp.spec.entity.MariaDBWeight

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ActiveProfiles
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.UUID

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class InventoryRepositoryTest {

    @Autowired
    private lateinit var skuRepository: SKURepository

    @Autowired
    private lateinit var inventoryRepository: InventoryRepository

    @Autowired
    private lateinit var sellerRepository: SellerRepository

    private val testSellerId = UUID.randomUUID()

    @Test
    @DisplayName("재고 갯수 체크 테스트")
    fun countTest() {

        // given
        val testSeller = createTestSeller()
        val testCheckSku = createTestSKU(testSeller)
        val testSku = createTestSKU(testSeller)
        val testCheckStatus = InventoryStatus.READY
        val expectedCount = 2L

        inventoryRepository.save(MariaDBInventory(testCheckSku, testCheckStatus))
        inventoryRepository.save(MariaDBInventory(testCheckSku, testCheckStatus))
        inventoryRepository.save(MariaDBInventory(testCheckSku, InventoryStatus.ORDER))

        inventoryRepository.save(MariaDBInventory(testSku, testCheckStatus))
        inventoryRepository.save(MariaDBInventory(testSku, InventoryStatus.CART))

        // when
        val resultCount = inventoryRepository.countBySkuIdAndStatus(testCheckSku.id, testCheckStatus)

        // then
        assertEquals(expectedCount, resultCount)
    }

    @AfterEach
    fun destroy() {
        inventoryRepository.deleteAll()
        skuRepository.deleteAll()
        sellerRepository.deleteAll()
    }

    /**
     * helpers
     */
    fun createTestSeller(): MariaDBSeller {
        return sellerRepository.save(MariaDBSeller(testSellerId))
    }
    fun createTestSKU(seller: MariaDBSeller): MariaDBSKU {
        val sku =
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
            )

        return skuRepository.save(sku)
    }
}
