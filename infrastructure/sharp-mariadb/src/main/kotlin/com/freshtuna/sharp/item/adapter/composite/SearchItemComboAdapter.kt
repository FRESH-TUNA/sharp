package com.freshtuna.sharp.item.adapter.composite

import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.item.ItemCombo
import com.freshtuna.sharp.item.outgoing.composite.SearchItemComboPort
import com.freshtuna.sharp.item.repository.composite.ItemCompoRepository
import org.springframework.stereotype.Component

@Component
class SearchItemComboAdapter(
    private val repository: ItemCompoRepository
) : SearchItemComboPort {

    override fun search(itemId: SharpID): List<ItemCombo> {
        return repository
            .findAllByItemId(itemId.longId())
            .map { policy -> policy.toDomain() }
            .toList()
    }
}
