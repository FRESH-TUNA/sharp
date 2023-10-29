package com.freshtuna.sharp.adapter.external.item

import com.freshtuna.sharp.api.response.BasicResponse
import com.freshtuna.sharp.api.response.MessageResponse
import com.freshtuna.sharp.config.const.Url
import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.id.SharpIDInjection
import com.freshtuna.sharp.item.incoming.InventoryFromItemUseCase
import com.freshtuna.sharp.request.sku.InventoryRequest
import com.freshtuna.sharp.spec.item.InventoryFromItemSpec
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@Tag(name = "아이템 출고")
@RestController
class InventoryFromItemController(
    private val useCase: InventoryFromItemUseCase
) : InventoryFromItemSpec {

    @PostMapping(Url.EXTERNAL.ITEM_OUT)
    override fun from(
        @RequestBody request: InventoryRequest,
        @Parameter(description = "SKU 아이디") @PathVariable id: Long,
        @Parameter(hidden = true) @SharpIDInjection sellerId: SharpID
    ): BasicResponse {

        useCase.from(request.toCommand(), SharpID(id), sellerId)
        return MessageResponse.OK
    }
}
