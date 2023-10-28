package com.freshtuna.sharp.item.adapter

import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.item.command.ItemCommand
import com.freshtuna.sharp.item.outgoing.UpdateItemPort
import com.freshtuna.sharp.item.repository.ItemRepository
import org.springframework.stereotype.Component

@Component
class UpdateItemAdapter(
    private val itemRepository: ItemRepository
) : UpdateItemPort{

    override fun update(command: ItemCommand, id: SharpID) {
        itemRepository.findById(id.longId()).get().update(command)
    }
}
