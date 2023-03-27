package com.freshtuna.sharp.inventory.repository

import com.freshtuna.sharp.config.JPAQueryFactoryBeanConfig
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

    @Test
    @DisplayName("페이지의 사이즈대로 잘 검색되는지 테스트")
    fun searchTest() {
        /**
         * given
         */
        val pageSize = 2L
        val command = SearchSkuCommand(sharpPageRequest = SharpPageRequest(0, pageSize, SharpSort()))

        /**
         * when
         */
        val results = skuQueryRepository.search(command)

        /**
         * then
         */
        assertEquals(pageSize, results.count)
    }
}
