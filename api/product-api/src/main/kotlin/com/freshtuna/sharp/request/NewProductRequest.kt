package com.freshtuna.sharp.request

import com.freshtuna.sharp.product.command.NewProductCommand
import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "상품 생성 요청")
class NewProductRequest(

    @Schema(description = "상품 이름")
    private val name: String
) {

    fun toCommand() = NewProductCommand(name)
}
