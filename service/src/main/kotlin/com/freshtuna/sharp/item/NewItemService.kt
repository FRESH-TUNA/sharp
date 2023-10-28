package com.freshtuna.sharp.item

import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.inventory.outgoing.NewSkuPort
import com.freshtuna.sharp.item.command.ItemCommand
import com.freshtuna.sharp.item.incoming.NewItemUseCase
import com.freshtuna.sharp.item.outgoing.composite.NewItemComboPort
import com.freshtuna.sharp.item.outgoing.NewItemPort

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class NewItemService(
    private val newItemPort: NewItemPort,
    private val newSkuPort: NewSkuPort,
    private val newItemComboPort: NewItemComboPort
) : NewItemUseCase {

    override fun new(command: ItemCommand, sellerID: SharpID): Item {

        val sku = newSkuPort.new(command.sku, sellerID)

        val newItem = newItemPort.new(command, sku.id, sellerID)

        newItemComboPort.new(command.composites, newItem.id)

        return newItem
    }
}
