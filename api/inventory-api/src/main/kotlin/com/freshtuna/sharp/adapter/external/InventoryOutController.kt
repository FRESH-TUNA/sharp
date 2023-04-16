package com.freshtuna.sharp.adapter.external

import com.freshtuna.sharp.api.response.BasicResponse
import com.freshtuna.sharp.api.response.MessageResponse
import com.freshtuna.sharp.config.const.Url
import com.freshtuna.sharp.id.PublicId
import com.freshtuna.sharp.inventory.incoming.InventoryOutUseCase

import com.freshtuna.sharp.request.InventoryRequest
import com.freshtuna.sharp.security.userDetail.UserDetailManager
import com.freshtuna.sharp.spec.InventoryOutSpec
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "재고 출고")
@RestController
class InventoryOutController(
    private val useCase: InventoryOutUseCase
) : InventoryOutSpec {

    @PostMapping(Url.EXTERNAL.SKU_ID_INVENTORY_OUT)
    override fun new(request: InventoryRequest, id: Long): BasicResponse {
        val skuId = PublicId(id)
        val sellerId = UserDetailManager.getPublicId()

        useCase.new(request.toCommand(skuId), sellerId)

        return MessageResponse.OK
    }
}
