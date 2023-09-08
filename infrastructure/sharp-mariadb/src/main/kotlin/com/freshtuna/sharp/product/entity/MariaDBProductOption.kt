package com.freshtuna.sharp.product.entity

import com.freshtuna.sharp.entity.MariaDBDefaultEntity
import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.inventory.entity.MariaDBSKU
import com.freshtuna.sharp.product.Product
import com.freshtuna.sharp.product.ProductOption
import jakarta.persistence.Entity
import jakarta.persistence.ManyToOne

@Entity
class MariaDBProductOption(
    @ManyToOne
    var product: MariaDBProduct,

    @ManyToOne
    var sku: MariaDBSKU
): MariaDBDefaultEntity() {

    fun toDomain(): ProductOption {
        return ProductOption()
    }
}
