package com.freshtuna.sharp.item.adapter

import com.freshtuna.sharp.entity.repository.SellerRepository
import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.inventory.repository.sku.SKURepository
import com.freshtuna.sharp.item.Item
import com.freshtuna.sharp.item.command.ItemCommand
import com.freshtuna.sharp.item.entity.toEntity
import com.freshtuna.sharp.item.outgoing.NewItemPort
import com.freshtuna.sharp.item.repository.ItemRepository
import org.springframework.stereotype.Component

@Component
class NewItemAdapter(
    private val itemRepository: ItemRepository,
    private val skuRepository: SKURepository,
    private val sellerRepository: SellerRepository,
) : NewItemPort {

    override fun new(command: ItemCommand, skuId: SharpID, sellerID: SharpID): Item {

        val sku = skuRepository.getReferenceById(skuId.longId())

        val seller = sellerRepository.getReferenceById(sellerID.longId())

        val newItem = command.toEntity(seller, sku)

        return itemRepository.save(newItem).toDomain()
    }
}
