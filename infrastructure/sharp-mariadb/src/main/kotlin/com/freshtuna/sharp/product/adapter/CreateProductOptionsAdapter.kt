package com.freshtuna.sharp.product.adapter

import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.inventory.repository.sku.SKURepository
import com.freshtuna.sharp.product.command.NewProductOptionCommand
import com.freshtuna.sharp.product.entity.ProductOptionEntity
import com.freshtuna.sharp.product.outgoing.CreateProductOptionsPort
import com.freshtuna.sharp.product.repository.ProductOptionRepository
import com.freshtuna.sharp.product.repository.ProductRepository
import org.springframework.stereotype.Component

@Component
class CreateProductOptionsAdapter(
    private val productRepository: ProductRepository,
    private val skuRepository: SKURepository,
    private val productOptionRepository: ProductOptionRepository
) : CreateProductOptionsPort {

    override fun ofProduct(productID: SharpID, commands: List<NewProductOptionCommand>) {

        val options = mutableListOf<ProductOptionEntity>()

        val skuIds = commands.map { command -> command.skuId.longId() }

        val product = productRepository.findById(productID.longId()).get()

        skuRepository.findAllById(skuIds)

        for (command in commands) {
            val sku = skuRepository.findById(command.skuId.longId()).get()
            options.add(ProductOptionEntity(product, sku))
        }

        productOptionRepository.saveAll(options)
    }
}
