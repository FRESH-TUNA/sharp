package com.freshtuna.sharp.item.adapter.composite

import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.item.outgoing.combo.DeleteItemCompositePolicyPort
import com.freshtuna.sharp.item.repository.ItemComboRepository
import org.springframework.stereotype.Component

@Component
class DeleteItemCompositePolicyAdapter(
    private val itemComboRepository: ItemComboRepository
) : DeleteItemCompositePolicyPort {

    override fun deleteAllByItemId(id: SharpID) {
        itemComboRepository.deleteAllByParentItemId(id.longId())
    }
}
