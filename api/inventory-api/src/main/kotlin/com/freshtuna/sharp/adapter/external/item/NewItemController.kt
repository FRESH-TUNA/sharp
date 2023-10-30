package com.freshtuna.sharp.adapter.external.item

import com.freshtuna.sharp.api.response.BasicResponse
import com.freshtuna.sharp.api.response.DataResponse
import com.freshtuna.sharp.config.const.Url
import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.id.SharpIDInjection
import com.freshtuna.sharp.item.incoming.NewItemUseCase
import com.freshtuna.sharp.request.item.ItemRequest
import com.freshtuna.sharp.spec.item.NewItemSpec
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@Tag(name = "아이템(재고 타입) 추가")
@RestController
class NewItemController(
    private val useCase: NewItemUseCase
) : NewItemSpec {

    @PostMapping(Url.EXTERNAL.ITEM)
    override fun new(
        @RequestBody request: ItemRequest,
        @Parameter(hidden = true) @SharpIDInjection sellerID: SharpID
    ): BasicResponse {

        val item = useCase.new(request.toCommand(), sellerID)
        return DataResponse.of(mapOf("id" to item.id!!.longId()))
    }
}
