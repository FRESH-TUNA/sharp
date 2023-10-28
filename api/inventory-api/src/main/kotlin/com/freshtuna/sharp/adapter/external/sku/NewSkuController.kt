package com.freshtuna.sharp.adapter.external.sku

import com.freshtuna.sharp.config.const.Url
import com.freshtuna.sharp.inventory.incoming.NewSkuUseCase
import com.freshtuna.sharp.request.sku.SkuRequest
import com.freshtuna.sharp.spec.sku.NewSkuSpec

import com.freshtuna.sharp.api.response.DataResponse
import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.id.SharpIDInjection

import com.freshtuna.sharp.response.sku.NewSkuResponse
import com.freshtuna.sharp.response.sku.toNewSkuResponse
import io.github.oshai.KotlinLogging
import io.swagger.v3.oas.annotations.Parameter

import io.swagger.v3.oas.annotations.tags.Tag

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@Tag(name = "신규 SKU 등록")
@RestController
class NewSkuController(
    private val useCase: NewSkuUseCase
) : NewSkuSpec {

    val log = KotlinLogging.logger {  }

    @PostMapping(Url.EXTERNAL.SKU)
    override fun new(@RequestBody request: SkuRequest,
                     @Parameter(hidden = true) @SharpIDInjection sellerID: SharpID): DataResponse<NewSkuResponse> {

        val result = useCase.new(request.toCommand(), sellerID)
        return DataResponse.of(result.toNewSkuResponse())
    }
}
