package com.freshtuna.sharp.inventory.entity

import com.freshtuna.sharp.entity.MariaDBDefaultEntity
import com.freshtuna.sharp.id.PublicId
import com.freshtuna.sharp.inventory.SKU
import com.freshtuna.sharp.inventory.command.NewSkuCommand
import com.freshtuna.sharp.price.entity.MariaDBPrice
import com.freshtuna.sharp.price.entity.toEntity
import com.freshtuna.sharp.spec.entity.MariaDBSpecification
import com.freshtuna.sharp.spec.entity.toEntity

import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import java.util.*

@Entity
@Table(name = "sku")
class MariaDBSKU(

    val sellerId: UUID,

    val name: String,

    val barcode: String,

    val description: String,

    @Embedded
    val price: MariaDBPrice,

    @Embedded
    val specification: MariaDBSpecification
) : MariaDBDefaultEntity() {

    @OneToMany(mappedBy = "sku")
    private var _stocks: MutableList<MariaDBStock> = mutableListOf()
    val stocks: List<MariaDBStock>
        get() = _stocks.toList()

    fun toDomain() = SKU(
        id = PublicId(id.toString()),
        name = name,
        barcode = barcode,
        description = description,
        spec = specification.toDomain(),
        price = price.toDomain(),
    )
}

/**
 * external
 */
fun NewSkuCommand.toEntity() = MariaDBSKU(
    name = this.name,
    barcode = this.barcode,
    description = this.description,
    price = this.price.toEntity(),
    specification = this.spec.toEntity(),
    sellerId = UUID.fromString(sellerId.toString())
)
