package com.freshtuna.sharp.inventory.repository.inventory

import com.freshtuna.sharp.inventory.domain.inventory.InventoryStatus
import com.freshtuna.sharp.inventory.entity.MariaDBInventory

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query


interface InventoryRepository : JpaRepository<MariaDBInventory, Long> {

    @Query(
        nativeQuery = true,
        value = "select *  " +
                "from inventory " +
                "where sku_id = :skuId " +
                "and status = :#{#status.name()} " +
                "limit :limit " +
                "for update skip locked"
    )
    fun findAllBySkuIdAndStatus(skuId: Long,
                                status: InventoryStatus,
                                limit: Long) : List<MariaDBInventory>


    @Query(
        nativeQuery = true,
        value = "delete from inventory where sku_id = :skuId limit :count"
    )
    fun deleteBySkuIdWithCount(skuId: Long, count: Long)
}
