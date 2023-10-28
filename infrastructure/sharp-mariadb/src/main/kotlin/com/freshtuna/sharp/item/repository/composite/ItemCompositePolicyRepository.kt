package com.freshtuna.sharp.item.repository.composite

import com.freshtuna.sharp.item.entity.ItemCompositePolicyEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ItemCompositePolicyRepository: JpaRepository<ItemCompositePolicyEntity, Long> {

    fun findAllByItemId(itemId: Long): List<ItemCompositePolicyEntity>
}
