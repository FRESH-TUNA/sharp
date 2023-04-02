package com.freshtuna.sharp.inventory.repository

import com.freshtuna.sharp.config.JPAQueryFactoryBeanConfig
import com.freshtuna.sharp.id.PublicId
import com.freshtuna.sharp.inventory.command.SearchSkuCommand
import com.freshtuna.sharp.page.SharpPageRequest
import com.freshtuna.sharp.page.SharpSort

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(JPAQueryFactoryBeanConfig::class, SKUQueryRepositoryQueryDSLImpl::class)
class SKUQueryRepositoryQueryDSLImplTest {

    @Autowired
    private lateinit var skuQueryRepository: SKUQueryRepositoryQueryDSLImpl

    private val sellerId = PublicId("dbff6d9a-6e59-4e1e-9f21-21c4e43c3ee8")

    @Test
    @DisplayName("페이지 테스트")
    fun pageTest() {
        /**
         * given
         */
        val pageSize = 2L
        val pageNumber = 1L
        val totalCount = 5L
        val command = SearchSkuCommand(
            sharpPageRequest = SharpPageRequest(pageNumber, pageSize, SharpSort()),
            sellerId = sellerId)

        /**
         * when
         */
        val results = skuQueryRepository.search(command)

        /**
         * then
         */
        assertEquals(pageNumber, results.pageNumber)
        assertEquals(pageSize, results.count)

        assertEquals(totalCount, results.totalCount)
        assertEquals(totalCount/pageSize, results.totalPageCount)
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
            sharpPageRequest = SharpPageRequest(0, pageSize, SharpSort()),
            sellerId = sellerId
        )

        /**
         * when
         */
        val results = skuQueryRepository.search(command)

        /**
         * then
         */
        results.page.stream().forEach {
            data -> assertEquals(true, data.sku.name.startsWith(query))
        }
    }

    @Test
    @DisplayName("sku 바코드로 쿼리 테스트")
    fun queryBarcodeTest() {
        /**
         * given
         */
        val query = "234523"
        val pageSize = 2L
        val command = SearchSkuCommand(
            query = query,
            sharpPageRequest = SharpPageRequest(0, pageSize, SharpSort()),
            sellerId = sellerId
        )

        /**
         * when
         */
        val results = skuQueryRepository.search(command)

        /**
         * then
         */
        results.page.stream().forEach {
                data -> assertEquals(true, data.sku.barcode.startsWith(query))
        }
    }
}
