package com.freshtuna.sharp.inventory.entity

import com.freshtuna.sharp.entity.MariaDBDefaultEntity
import com.freshtuna.sharp.entity.MariaDBSeller
import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.inventory.domain.SKU
import com.freshtuna.sharp.inventory.command.SkuCommand

import com.freshtuna.sharp.price.entity.MariaDBPrice
import com.freshtuna.sharp.price.entity.toEntity
import com.freshtuna.sharp.spec.entity.MariaDBSpecification
import com.freshtuna.sharp.spec.entity.toEntity
import jakarta.persistence.*

import java.time.LocalDateTime

@Entity
@Table(name = "sku")
class MariaDBSKU(

    @ManyToOne
    val seller: MariaDBSeller,

    var name: String,

    var barcode: String,

    var description: String,

    @Embedded
    var price: MariaDBPrice,

    @Embedded
    var specification: MariaDBSpecification,

    var expireDate: LocalDateTime,

    var manufactureDate: LocalDateTime,

    ) : MariaDBDefaultEntity() {

    @OneToMany(mappedBy = "sku")
    private var _inventories: MutableList<MariaDBInventory> = mutableListOf()
    val inventories: List<MariaDBInventory>
        get() = _inventories.toList()

    fun update(command: SkuCommand) {
        name = command.name
        barcode = command.barcode
        description = command.description
        specification = command.spec.toEntity()
        price = command.price.toEntity()

        expireDate = command.expireDate
        manufactureDate = command.manufactureDate
    }

    fun toDomain() = SKU(
        id = SharpID(id),
        sellerId = SharpID(seller.id),
        name = name,
        barcode = barcode,
        description = description,
        spec = specification.toDomain(),
        price = price.toDomain(),

        expireDate = this.expireDate,
        manufactureDate = this.manufactureDate,

        count = inventories.size.toLong()
    )
}

/**
 * external
 */
fun SkuCommand.toEntity(seller: MariaDBSeller) = MariaDBSKU(
    name = this.name,
    barcode = this.barcode,
    description = this.description,
    price = this.price.toEntity(),
    specification = this.spec.toEntity(),
    seller = seller,

    expireDate = this.expireDate,
    manufactureDate = this.manufactureDate,
)
