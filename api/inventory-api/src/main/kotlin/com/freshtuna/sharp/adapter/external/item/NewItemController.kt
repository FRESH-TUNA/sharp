package com.freshtuna.sharp.adapter.external.item

import com.freshtuna.sharp.api.response.BasicResponse
import com.freshtuna.sharp.api.response.DataResponse
import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.id.SharpIDInjection
import com.freshtuna.sharp.item.incoming.NewItemUseCase
import com.freshtuna.sharp.request.item.NewItemRequest
import com.freshtuna.sharp.spec.item.NewItemSpec
import io.swagger.v3.oas.annotations.Parameter
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class NewItemController(
    private val useCase: NewItemUseCase
) : NewItemSpec {

    override fun new(
        @RequestBody request: NewItemRequest,
        @Parameter(hidden = true) @SharpIDInjection sellerID: SharpID
    ): BasicResponse {
        val item = useCase.new(request.toCommand(), sellerID)
        return DataResponse.of(item.id!!.longId())
    }
}
