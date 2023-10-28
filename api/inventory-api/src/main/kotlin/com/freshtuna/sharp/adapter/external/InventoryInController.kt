package com.freshtuna.sharp.adapter.external

import com.freshtuna.sharp.config.const.Url
import com.freshtuna.sharp.request.InventoryInRequest
import com.freshtuna.sharp.spec.sku.InventoryInSpec
import com.freshtuna.sharp.inventory.incoming.InventoryInUseCase
import com.freshtuna.sharp.api.response.BasicResponse

import com.freshtuna.sharp.api.response.MessageResponse
import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.id.SharpIDInjection
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.PathVariable

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
@Tag(name = "재고 입고")
@RestController
class InventoryInController(
    private val useCase: InventoryInUseCase
) : InventoryInSpec {

    @PostMapping(Url.EXTERNAL.SKU_ID_INVENTORY_IN)
    override fun new(@RequestBody request: InventoryInRequest,
                     @Parameter(description = "입고 SKU 아이디") @PathVariable id: Long,
                     @Parameter(hidden = true) @SharpIDInjection sellerID: SharpID): BasicResponse {

        val skuId = SharpID(id)

        useCase.new(request.toCommandOf(skuId), sellerID)

        return MessageResponse.OK
    }
}
