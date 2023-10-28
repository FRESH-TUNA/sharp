package com.freshtuna.sharp.item.adapter.composite

import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.item.ItemCombo
import com.freshtuna.sharp.item.ItemComboDetail
import com.freshtuna.sharp.item.command.ItemComboCommand
import com.freshtuna.sharp.item.entity.ItemComboEntity
import com.freshtuna.sharp.item.outgoing.composite.NewItemComboPort
import com.freshtuna.sharp.item.repository.composite.ItemCompoRepository
import com.freshtuna.sharp.item.repository.ItemRepository
import org.springframework.stereotype.Component

@Component
class NewItemComboAdapter(
    private val itemRepository: ItemRepository,
    private val itemCompoRepository: ItemCompoRepository
) : NewItemComboPort {

    override fun new(commands: List<ItemComboCommand>, rootItemId: SharpID): List<ItemCombo> {

        val rootItem = itemRepository.getReferenceById(rootItemId.longId())

        if(commands.isEmpty())
            return emptyList()

        val newPolicies = mutableListOf<ItemComboEntity>()

        for(command in commands) {
            val item = itemRepository.findById(command.itemId.longId())
            newPolicies.add(ItemComboEntity(rootItem, item.get(), command.amount))
        }

        return itemCompoRepository.saveAll(newPolicies)
            .map { entity -> entity.toDomain() }
            .toList()
    }
}
