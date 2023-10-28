package com.freshtuna.sharp.response.sku

import com.freshtuna.sharp.inventory.domain.SKU
import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "신규 SKU 정보")
class NewSkuResponse(

    @Schema(description = "신규 SKU 아이디")
    val id: String
)

fun SKU.toNewSkuResponse() = NewSkuResponse(this.id.stringId())
