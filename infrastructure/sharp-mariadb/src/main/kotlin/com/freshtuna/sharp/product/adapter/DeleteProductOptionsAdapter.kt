package com.freshtuna.sharp.product.adapter

import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.product.outgoing.DeleteProductOptionsPort
import com.freshtuna.sharp.product.repository.ProductOptionRepository
import org.springframework.stereotype.Component

@Component
class DeleteProductOptionsAdapter(
    private val productOptionRepository: ProductOptionRepository
) : DeleteProductOptionsPort {

    override fun ofProduct(productID: SharpID) {
        productOptionRepository.deleteAllByProductId(productID.longId())
    }
}
