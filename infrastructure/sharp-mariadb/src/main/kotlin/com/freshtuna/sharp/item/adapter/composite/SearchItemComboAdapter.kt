package com.freshtuna.sharp.item.adapter.composite

import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.item.ItemCombo
import com.freshtuna.sharp.item.outgoing.combo.SearchItemComboPort
import com.freshtuna.sharp.item.repository.ItemComboRepository
import org.springframework.stereotype.Component

@Component
class SearchItemComboAdapter(
    private val repository: ItemComboRepository
) : SearchItemComboPort {

    override fun search(itemId: SharpID): List<ItemCombo> {
        return repository
            .findAllByParentItemId(itemId.longId())
            .map { policy -> policy.toDomain() }
            .toList()
    }
}
