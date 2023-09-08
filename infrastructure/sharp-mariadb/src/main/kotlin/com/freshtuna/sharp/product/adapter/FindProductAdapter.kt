package com.freshtuna.sharp.product.adapter

import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.product.Product
import com.freshtuna.sharp.product.outgoing.FindProductPort
import com.freshtuna.sharp.product.repository.ProductRepository
import org.springframework.stereotype.Component

@Component
class FindProductAdapter(
    private val productRepository: ProductRepository
) : FindProductPort {

    override fun find(id: SharpID): Product {
        val product = productRepository.findById(id.longId()).get()
        return product.toDomain()
    }
}
