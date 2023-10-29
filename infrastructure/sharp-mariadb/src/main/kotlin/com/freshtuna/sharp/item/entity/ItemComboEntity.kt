package com.freshtuna.sharp.item.entity

import com.freshtuna.sharp.entity.MariaDBDefaultEntity
import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.item.ItemCombo

import jakarta.persistence.Entity
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name = "item_combo")
class ItemComboEntity(

    @ManyToOne
    var parentItem: ItemEntity,

    @ManyToOne
    var comboItem: ItemEntity,

    var amount: Long,
) : MariaDBDefaultEntity() {

    fun toDomain(): ItemCombo {
        return ItemCombo(SharpID(id), SharpID(comboItem.id), amount)
    }
}
