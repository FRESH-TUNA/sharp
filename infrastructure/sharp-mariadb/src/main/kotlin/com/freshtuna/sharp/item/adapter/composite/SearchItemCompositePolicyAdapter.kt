package com.freshtuna.sharp.item.adapter.composite

import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.item.ItemCompositePolicy
import com.freshtuna.sharp.item.outgoing.composite.SearchItemCompositePolicyPort
import com.freshtuna.sharp.item.repository.composite.ItemCompositePolicyRepository
import org.springframework.stereotype.Component

@Component
class SearchItemCompositePolicyAdapter(
    private val repository: ItemCompositePolicyRepository
) : SearchItemCompositePolicyPort {

    override fun search(itemId: SharpID): List<ItemCompositePolicy> {
        return repository
            .findAllByItemId(itemId.longId())
            .map { policy -> policy.toDomain() }
            .toList()
    }
}
