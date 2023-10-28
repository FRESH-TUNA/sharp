package com.freshtuna.sharp.item.adapter.composite

import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.item.ItemCompositePolicy
import com.freshtuna.sharp.item.command.ItemCompositePolicyCommand
import com.freshtuna.sharp.item.entity.ItemCompositePolicyEntity
import com.freshtuna.sharp.item.outgoing.composite.NewItemCompositePolicyPort
import com.freshtuna.sharp.item.repository.composite.ItemCompositePolicyRepository
import com.freshtuna.sharp.item.repository.ItemRepository
import org.springframework.stereotype.Component

@Component
class NewItemCompositePolicyAdapter(
    private val itemRepository: ItemRepository,
    private val itemCompositeRepository: ItemCompositePolicyRepository
) : NewItemCompositePolicyPort {

    override fun new(commands: List<ItemCompositePolicyCommand>, rootItemId: SharpID): List<ItemCompositePolicy> {

        val rootItem = itemRepository.getReferenceById(rootItemId.longId())

        if(commands.isEmpty())
            return emptyList()

        val newPolicies = mutableListOf<ItemCompositePolicyEntity>()

        for(command in commands) {
            val item = itemRepository.findById(command.itemId.longId())
            newPolicies.add(ItemCompositePolicyEntity(rootItem, item.get(), command.amount))
        }

        return itemCompositeRepository.saveAll(newPolicies)
            .map { entity -> entity.toDomain() }
            .toList()
    }
}
