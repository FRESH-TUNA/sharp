package com.freshtuna.sharp.item.repository

import com.freshtuna.sharp.item.entity.ItemComboEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ItemComboRepository: JpaRepository<ItemComboEntity, Long> {

    fun deleteAllByParentItemId(id: Long)

    fun findAllByParentItemId(id: Long): List<ItemComboEntity>
}
