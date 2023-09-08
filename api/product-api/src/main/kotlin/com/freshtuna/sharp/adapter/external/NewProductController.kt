package com.freshtuna.sharp.adapter.external

import com.freshtuna.sharp.api.response.BasicResponse
import com.freshtuna.sharp.api.response.MessageResponse
import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.product.incoming.NewProductUseCase
import com.freshtuna.sharp.request.NewProductRequest
import com.freshtuna.sharp.spec.NewProductSpec
import org.springframework.web.bind.annotation.RestController

@RestController
class NewProductController(
    private val newProductUseCase: NewProductUseCase
) : NewProductSpec {

    override fun new(request: NewProductRequest, sellerId: SharpID): BasicResponse {

        newProductUseCase.new(request.toCommand())

        return MessageResponse.OK
    }
}
