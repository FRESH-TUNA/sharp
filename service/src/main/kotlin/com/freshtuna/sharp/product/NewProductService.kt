package com.freshtuna.sharp.product

import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.product.command.NewProductCommand
import com.freshtuna.sharp.product.incoming.NewProductUseCase
import com.freshtuna.sharp.product.outgoing.NewProductPort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class NewProductService(
    private val newProductPort: NewProductPort
) : NewProductUseCase {

    override fun new(command: NewProductCommand, sellerId: SharpID) {
        newProductPort.newProduct(command, sellerId)
    }
}
