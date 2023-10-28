package com.freshtuna.sharp.adapter.external.item

import com.freshtuna.sharp.api.response.BasicResponse
import com.freshtuna.sharp.api.response.MessageResponse
import com.freshtuna.sharp.config.const.Url
import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.id.SharpIDInjection
import com.freshtuna.sharp.item.incoming.UpdateItemUseCase
import com.freshtuna.sharp.request.item.ItemRequest
import com.freshtuna.sharp.spec.item.UpdateItemSpec
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@Tag(name = "아이템 정보 수정")
@RestController
class UpdateItemController(
    private val updateItemUseCase: UpdateItemUseCase
) : UpdateItemSpec {

    @PutMapping(Url.EXTERNAL.ITEM_ID)
    override fun update(
        @RequestBody request: ItemRequest,
        @Parameter(description = "SKU 아이디") @PathVariable id: String,
        @Parameter(hidden = true) @SharpIDInjection sellerId: SharpID
    ): BasicResponse {

        updateItemUseCase.update(request.toCommand(), SharpID(id), sellerId)
        return MessageResponse.OK
    }
}
