package com.freshtuna.sharp.product.entity

import com.freshtuna.sharp.entity.MariaDBDefaultEntity
import com.freshtuna.sharp.entity.MariaDBSeller
import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.product.Product
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.ManyToOne

@Entity
class MariaDBProduct(

    @Column
    val status: String,

    @ManyToOne
    val seller: MariaDBSeller
    
) : MariaDBDefaultEntity() {

    fun toDomain(): Product {
        return Product(status, SharpID(seller.id))
    }
}
