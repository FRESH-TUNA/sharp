package com.freshtuna.sharp.item.adapter

import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.item.Item
import com.freshtuna.sharp.item.entity.ItemEntity
import com.freshtuna.sharp.item.outgoing.ItemListPort
import com.freshtuna.sharp.item.repository.ItemRepository
import org.springframework.stereotype.Component

@Component
class ItemListAdapter(
    private val itemRepository: ItemRepository
) : ItemListPort {

    override fun findAllByIds(ids: List<SharpID>, sellerID: SharpID): List<Item> {
        val pks = ids.map(SharpID::longId)

        return itemRepository
            .findAllBySellerIdAndIdIn(sellerID.longId(), pks)
            .map(ItemEntity::toDomain)
    }
}
