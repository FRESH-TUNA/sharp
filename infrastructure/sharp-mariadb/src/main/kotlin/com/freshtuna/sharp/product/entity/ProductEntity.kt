package com.freshtuna.sharp.product.entity

import com.freshtuna.sharp.entity.MariaDBDefaultEntity
import com.freshtuna.sharp.entity.MariaDBSeller
import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.product.Product
import com.freshtuna.sharp.product.command.NewProductCommand
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.ManyToOne

@Entity
class ProductEntity(

    @Column
    val name: String,

    @ManyToOne
    val seller: MariaDBSeller
    
) : MariaDBDefaultEntity() {

    fun toDomain(): Product {
        return Product(name, SharpID(seller.id))
    }
}

fun NewProductCommand.toEntity(seller: MariaDBSeller): ProductEntity{
    return ProductEntity(
        name = name,
        seller
    )
}
