package com.freshtuna.sharp.inventory.domain

import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.price.Price
import com.freshtuna.sharp.spec.Spec
import java.time.LocalDateTime

class SKU(
    val id: SharpID,
    val sellerId: SharpID,

    val name: String,
    val price: Price,

    val barcode: String,
    val description: String,

    val expireDate: LocalDateTime,
    val manufactureDate: LocalDateTime,

    /**
     * specific
     */
    val spec: Spec,

    /**
     * counts
     */
    val availableCount: Long = 0,
    val count: Long = 0,
) {

    fun checkSameSeller(id: SharpID)
        = sellerId == id
}
