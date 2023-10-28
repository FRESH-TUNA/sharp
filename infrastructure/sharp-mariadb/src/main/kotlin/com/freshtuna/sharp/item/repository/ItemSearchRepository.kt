package com.freshtuna.sharp.item.repository

import com.freshtuna.sharp.id.SharpID

import com.freshtuna.sharp.item.command.SearchItemCommand
import com.freshtuna.sharp.item.entity.ItemEntity
import com.freshtuna.sharp.page.SharpPage

interface ItemSearchRepository {

    fun search(commend: SearchItemCommand, sellerId: SharpID): SharpPage<ItemEntity>

    fun findById(id: SharpID): ItemEntity
}
