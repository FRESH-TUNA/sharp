package com.freshtuna.sharp.item.adapter

import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.item.Item
import com.freshtuna.sharp.item.outgoing.ShowItemPort
import com.freshtuna.sharp.item.repository.ItemSearchRepository

import org.springframework.stereotype.Component

@Component
class ShowItemAdapter(
    private val itemRepository: ItemSearchRepository
) : ShowItemPort {

    override fun show(id: SharpID): Item {
        return itemRepository.findById(id).toDomain()
    }
}
