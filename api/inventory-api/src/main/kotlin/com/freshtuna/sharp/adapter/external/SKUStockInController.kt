package com.freshtuna.sharp.adapter.external

import com.freshtuna.sharp.config.const.Url
import com.freshtuna.sharp.request.SkuStockInRequest
import com.freshtuna.sharp.spec.SKUStockInSpec
import com.freshtuna.sharp.inventory.incoming.StockInUseCase
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
@Tag(name = "재고 입고")
@RestController
class SKUStockInController(
    private val useCase: StockInUseCase
) : SKUStockInSpec {

    @PostMapping(Url.EXTERNAL.SKU_ID_STOCK)
    override fun stockIn(@RequestBody request: SkuStockInRequest,
                         @Parameter(description = "입고할 SKU 아이디") @PathVariable id: Long): BasicResponse {

        val skuId = PublicId(id)
        val sellerId = UserDetailManager.getPublicId()

        useCase.stockIn(request.toCommand(skuId), sellerId)

        return MessageResponse.OK
    }
}
