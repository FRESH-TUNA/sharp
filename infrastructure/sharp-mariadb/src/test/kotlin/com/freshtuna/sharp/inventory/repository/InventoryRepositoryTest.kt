package com.freshtuna.sharp.inventory.repository

import com.freshtuna.sharp.inventory.domain.inventory.InventoryCondition
import com.freshtuna.sharp.inventory.domain.inventory.InventoryStatus
import com.freshtuna.sharp.inventory.entity.MariaDBInventory
import com.freshtuna.sharp.inventory.repository.inventory.InventoryRepository
import com.freshtuna.sharp.inventory.repository.sku.SKURepository
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class InventoryRepositoryTest {

    @Autowired
    private lateinit var skuRepository: SKURepository

    @Autowired
    private lateinit var inventoryRepository: InventoryRepository


    @Test
    @DisplayName("신규 저장 테스트")
    fun pageTest() {

        val sku = skuRepository.findById(1L).get()

        inventoryRepository.save(MariaDBInventory(sku, InventoryStatus.READY))
    }
}
