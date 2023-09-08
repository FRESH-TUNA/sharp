package com.freshtuna.sharp.product.adapter

import com.freshtuna.sharp.product.command.NewProductCommand
import com.freshtuna.sharp.product.outgoing.NewProductPort
import org.springframework.stereotype.Component

@Component
class NewProductAdapter(

) : NewProductPort{
    override fun newProduct(command: NewProductCommand) {
        TODO("Not yet implemented")
    }
}
