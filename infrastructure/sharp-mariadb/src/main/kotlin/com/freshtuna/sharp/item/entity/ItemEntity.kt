package com.freshtuna.sharp.item.entity

import com.freshtuna.sharp.category.SharpCategory
import com.freshtuna.sharp.entity.MariaDBDefaultEntity
import com.freshtuna.sharp.entity.MariaDBSeller
import com.freshtuna.sharp.id.SharpID

import com.freshtuna.sharp.inventory.entity.MariaDBSKU
import com.freshtuna.sharp.item.Item
import com.freshtuna.sharp.item.ItemSummary
import com.freshtuna.sharp.item.command.ItemCommand

import jakarta.persistence.Entity
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(name = "item")
class ItemEntity(

    @ManyToOne
    val seller: MariaDBSeller,

    var name: String,

    var category: SharpCategory,

    @ManyToOne
    var sku: MariaDBSKU,

    var description: String,

    @OneToMany(mappedBy = "rootItem")
    val policies: List<ItemComboEntity> = mutableListOf()
) : MariaDBDefaultEntity() {

    fun toDomain(): Item {
        return Item(
            id = SharpID(this.id),
            name = name,
            category = category,
            sellerId = SharpID(seller.id),
            skuId = SharpID(sku.id),
            description = description
        )
    }

    fun toSummary(count: Long): ItemSummary {
        return ItemSummary(
            id = SharpID(this.id),
            name = name,
            category = category,
            sellerId = SharpID(seller.id),
            skuId = SharpID(sku.id),
            count = count
        )
    }

    fun update(command: ItemCommand) {
        this.name = command.name
        this.category = command.category
        this.description = command.description
    }
}

/**
 * external
 */
fun ItemCommand.toEntity(seller: MariaDBSeller, sku: MariaDBSKU) = ItemEntity(
    seller = seller,
    name = this.name,
    category = this.category,
    sku = sku,
    description = this.description
)
