package com.freshtuna.sharp.entity.repository

import com.freshtuna.sharp.item.entity.ItemComboEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ItemCompositePolicyRepository : JpaRepository<ItemComboEntity, Long> {

    fun deleteAllByRootItemId(id: Long)
}
