package com.freshtuna.sharp.item.entity

import com.freshtuna.sharp.entity.MariaDBDefaultEntity
import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.item.ItemCompositePolicy

import jakarta.persistence.Entity
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name = "item_composite_policy")
class ItemCompositePolicyEntity(

    @ManyToOne
    var rootItem: ItemEntity,

    @ManyToOne
    var item: ItemEntity,

    var amount: Long,
) : MariaDBDefaultEntity() {

    fun toDomain(): ItemCompositePolicy {
        return ItemCompositePolicy(SharpID(id), SharpID(item.id), amount)
    }
}
