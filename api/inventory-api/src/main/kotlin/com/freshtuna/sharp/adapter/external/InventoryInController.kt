package com.freshtuna.sharp.adapter.external

import com.freshtuna.sharp.config.const.Url
import com.freshtuna.sharp.request.InventoryRequest
import com.freshtuna.sharp.spec.InventoryInSpec
import com.freshtuna.sharp.inventory.incoming.InventoryInUseCase
import com.freshtuna.sharp.api.response.BasicResponse

import com.freshtuna.sharp.api.response.MessageResponse
import com.freshtuna.sharp.id.PublicId
import com.freshtuna.sharp.security.userDetail.UserDetailManager
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.PathVariable

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
@Tag(name = "SKU 재고 입고/출고")
@RestController
class InventoryInController(
    private val useCase: InventoryInUseCase
) : InventoryInSpec {

    @PostMapping(Url.EXTERNAL.SKU_ID_INVENTORY_IN)
    override fun new(@RequestBody request: InventoryRequest,
                     @Parameter(description = "입/출고 SKU 아이디") @PathVariable id: Long): BasicResponse {

        val skuId = PublicId(id)
        val sellerId = UserDetailManager.getPublicId()

        useCase.new(request.toCommand(skuId), sellerId)

        return MessageResponse.OK
    }
}
