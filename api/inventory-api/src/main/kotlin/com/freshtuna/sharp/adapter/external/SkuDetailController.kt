package com.freshtuna.sharp.adapter.external

import com.freshtuna.sharp.api.response.BasicResponse
import com.freshtuna.sharp.api.response.DataResponse
import com.freshtuna.sharp.config.const.Url
import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.id.SharpIDInjection
import com.freshtuna.sharp.inventory.command.DetailSkuCommand

import com.freshtuna.sharp.inventory.incoming.SkuDetailUseCase
import com.freshtuna.sharp.response.toResponse
import com.freshtuna.sharp.spec.SkuDetailSpec
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@Tag(name = "SKU 상제정보 조회")
@RestController
class SkuDetailController(
    private val skuDetailUseCase: SkuDetailUseCase
) : SkuDetailSpec{

    @GetMapping(Url.EXTERNAL.SKU_ID)
    override fun detail(@Parameter(description = "SKU 아이디") @PathVariable id: String,
                        @SharpIDInjection sellerID: SharpID): BasicResponse {

        val command = DetailSkuCommand(SharpID(id))

        return DataResponse.of(skuDetailUseCase.detail(command, sellerID).toResponse())
    }
}
