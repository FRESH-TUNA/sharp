package com.freshtuna.sharp.item.repository

import com.freshtuna.sharp.item.entity.ItemEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ItemRepository : JpaRepository<ItemEntity, Long> {

    fun findAllByIdIn(ids: List<Long>): List<ItemEntity>
}
