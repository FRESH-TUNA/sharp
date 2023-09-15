package com.freshtuna.sharp.product.entity

import com.freshtuna.sharp.entity.MariaDBDefaultEntity
import com.freshtuna.sharp.inventory.entity.MariaDBSKU
import com.freshtuna.sharp.product.ProductOption
import com.freshtuna.sharp.product.command.NewProductOptionCommand
import jakarta.persistence.Entity
import jakarta.persistence.ManyToOne

@Entity
class ProductOptionEntity(
    @ManyToOne
    var product: ProductEntity,

    @ManyToOne
    var sku: MariaDBSKU
): MariaDBDefaultEntity() {

    fun toDomain(): ProductOption {
        return ProductOption()
    }
}
