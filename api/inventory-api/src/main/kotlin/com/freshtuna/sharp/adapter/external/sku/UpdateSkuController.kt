package com.freshtuna.sharp.adapter.external.sku

import com.freshtuna.sharp.api.response.BasicResponse
import com.freshtuna.sharp.api.response.MessageResponse
import com.freshtuna.sharp.config.const.Url
import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.id.SharpIDInjection
import com.freshtuna.sharp.inventory.incoming.UpdateSkuUseCase
import com.freshtuna.sharp.request.sku.SkuRequest
import com.freshtuna.sharp.spec.sku.UpdateSkuSpec
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.PathVariable

import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@Tag(name = "SKU 정보 수정")
@RestController
class UpdateSkuController(
    private val useCase: UpdateSkuUseCase
) : UpdateSkuSpec {

    @PutMapping(Url.EXTERNAL.SKU_ID)
    override fun update(@RequestBody request: SkuRequest,
                        @Parameter(description = "SKU 아이디") @PathVariable id: String,
                        @Parameter(hidden = true) @SharpIDInjection sellerID: SharpID): BasicResponse {

        useCase.update(request.toCommand(), SharpID(id), sellerID)
        return MessageResponse.OK
    }
}
