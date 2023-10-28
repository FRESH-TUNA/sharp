package com.freshtuna.sharp.item.repository.composite

import com.freshtuna.sharp.item.entity.ItemComboEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ItemCompoRepository: JpaRepository<ItemComboEntity, Long> {

    fun findAllByItemId(itemId: Long): List<ItemComboEntity>
}
