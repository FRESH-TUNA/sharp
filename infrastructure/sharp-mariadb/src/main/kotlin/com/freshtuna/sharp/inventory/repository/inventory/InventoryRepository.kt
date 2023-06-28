package com.freshtuna.sharp.inventory.repository.inventory

import com.freshtuna.sharp.inventory.domain.inventory.InventoryStatus
import com.freshtuna.sharp.inventory.entity.MariaDBInventory
import jakarta.persistence.NamedNativeQuery
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface InventoryRepository : JpaRepository<MariaDBInventory, Long> {

    @Query(
        value = "select count(i.id) " +
                "from MariaDBInventory i " +
                "where i.sku.id = :skuId " +
                "and i.status = :status"
    )
    fun countBySkuIdAndStatus(skuId: Long,
                              status: InventoryStatus) : Long

    @Query(
        nativeQuery = true,
        value = "delete from inventory where sku_id = :skuId limit :count"
    )
    fun deleteBySkuIdWithCount(skuId: Long, count: Long)
}

