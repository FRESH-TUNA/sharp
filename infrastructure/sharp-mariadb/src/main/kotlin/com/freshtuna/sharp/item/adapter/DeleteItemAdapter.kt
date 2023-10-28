package com.freshtuna.sharp.item.adapter

import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.item.outgoing.DeleteItemPort
import com.freshtuna.sharp.item.repository.ItemRepository
import org.springframework.stereotype.Component

@Component
class DeleteItemAdapter(
    private val itemRepository: ItemRepository
) : DeleteItemPort {

    override fun delete(id: SharpID) {
        itemRepository.deleteById(id.longId())
    }
}
