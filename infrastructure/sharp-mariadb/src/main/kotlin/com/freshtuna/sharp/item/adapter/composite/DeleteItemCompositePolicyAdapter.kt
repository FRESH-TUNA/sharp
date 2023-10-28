package com.freshtuna.sharp.item.adapter.composite

import com.freshtuna.sharp.entity.repository.ItemCompositePolicyRepository
import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.item.outgoing.composite.DeleteItemCompositePolicyPort
import org.springframework.stereotype.Component

@Component
class DeleteItemCompositePolicyAdapter(
    private val itemCompositePolicyRepository: ItemCompositePolicyRepository
) : DeleteItemCompositePolicyPort {

    override fun deleteAllByItemId(id: SharpID) {
        itemCompositePolicyRepository.deleteAllByRootItemId(id.longId())
    }
}
