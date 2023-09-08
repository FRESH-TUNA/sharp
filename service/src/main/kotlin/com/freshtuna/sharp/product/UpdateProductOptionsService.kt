package com.freshtuna.sharp.product

import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.oh.Oh
import com.freshtuna.sharp.product.command.NewProductOptionCommand
import com.freshtuna.sharp.product.incoming.UpdateProductOptionsUseCase
import com.freshtuna.sharp.product.outgoing.FindProductPort
import com.freshtuna.sharp.product.outgoing.CreateProductOptionsPort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class UpdateProductOptionsService(
    private val createProductOptionsPort: CreateProductOptionsPort,
    private val createProductOptionsPort: DeleteProductOptionsPort,
    private val findProductPort: FindProductPort,
) : UpdateProductOptionsUseCase
{
    override fun update(
        productID: SharpID,
        sellerID: SharpID,
        commands: List<NewProductOptionCommand>
    ) {

        val product = findProductPort.find(productID)

        if (!product.isOwner(sellerID))
            Oh.badRequest()

        createProductOptionsPort.create(productID, commands)
    }
}
