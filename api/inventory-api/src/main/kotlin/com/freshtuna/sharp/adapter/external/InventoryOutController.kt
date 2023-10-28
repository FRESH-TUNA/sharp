package com.freshtuna.sharp.adapter.external

import com.freshtuna.sharp.api.response.BasicResponse
import com.freshtuna.sharp.api.response.MessageResponse
import com.freshtuna.sharp.config.const.Url
import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.id.SharpIDInjection
import com.freshtuna.sharp.inventory.incoming.InventoryOutUseCase
import com.freshtuna.sharp.request.InventoryOutRequest

import com.freshtuna.sharp.spec.sku.InventoryOutSpec
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@Tag(name = "재고 출고")
@RestController
class InventoryOutController(
    private val useCase: InventoryOutUseCase
) : InventoryOutSpec {

    @PostMapping(Url.EXTERNAL.SKU_ID_INVENTORY_OUT)
    override fun new(@RequestBody request: InventoryOutRequest,
                     @Parameter(description = "출고 SKU 아이디") @PathVariable id: Long,
                     @Parameter(hidden = true) @SharpIDInjection sellerID: SharpID): BasicResponse {

        val skuId = SharpID(id)

        useCase.out(request.toCommand(), skuId, sellerID)

        return MessageResponse.OK
    }
}
