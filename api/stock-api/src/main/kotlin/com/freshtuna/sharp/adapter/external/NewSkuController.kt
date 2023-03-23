package com.freshtuna.sharp.adapter.external

import com.freshtuna.sharp.inventory.incoming.NewSkuUseCase
import com.freshtuna.sharp.request.NewSkuRequest
import com.freshtuna.sharp.spec.NewSkuSpec
import com.freshtuna.tooth.api.response.BasicResponse
import com.freshtuna.tooth.api.response.MessageResponse
import org.springframework.web.bind.annotation.RestController

@RestController
class NewSkuController(
    private val useCase: NewSkuUseCase
) : NewSkuSpec {

    override fun new(request: NewSkuRequest): BasicResponse {
        useCase.new(request.toCommand())
        return MessageResponse.OK
    }
}
