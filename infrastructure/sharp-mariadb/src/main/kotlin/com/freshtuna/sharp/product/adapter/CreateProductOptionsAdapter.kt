package com.freshtuna.sharp.product.adapter

import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.product.command.NewProductOptionCommand
import com.freshtuna.sharp.product.outgoing.CreateProductOptionsPort
import com.freshtuna.sharp.product.repository.ProductOptionRepository
import org.springframework.stereotype.Component

@Component
class CreateProductOptionsAdapter(
    private val productOptionRepository: ProductOptionRepository
) : CreateProductOptionsPort {

    override fun create(productID: SharpID, commands: List<NewProductOptionCommand>) {

    }
}
