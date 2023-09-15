package com.freshtuna.sharp.product.adapter

import com.freshtuna.sharp.entity.repository.SellerRepository
import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.product.command.NewProductCommand
import com.freshtuna.sharp.product.entity.toEntity
import com.freshtuna.sharp.product.outgoing.NewProductPort
import com.freshtuna.sharp.product.repository.ProductRepository
import org.springframework.stereotype.Component

@Component
class NewProductAdapter(
    private val productRepository: ProductRepository,
    private val sellerRepository: SellerRepository
) : NewProductPort{
    override fun newProduct(command: NewProductCommand, sellerId: SharpID) {

        val seller = sellerRepository.findByPublicId(sellerId.uuid())!!
        productRepository.save(command.toEntity(seller))
    }
}
