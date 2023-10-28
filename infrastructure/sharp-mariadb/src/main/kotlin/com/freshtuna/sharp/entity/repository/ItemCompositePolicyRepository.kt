package com.freshtuna.sharp.entity.repository

import com.freshtuna.sharp.item.entity.ItemCompositePolicyEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ItemCompositePolicyRepository : JpaRepository<ItemCompositePolicyEntity, Long> {

    fun deleteAllByRootItemId(id: Long)
}
