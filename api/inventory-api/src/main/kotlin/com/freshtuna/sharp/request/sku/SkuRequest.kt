package com.freshtuna.sharp.request.sku

import com.freshtuna.sharp.inventory.command.SkuCommand
import com.freshtuna.sharp.price.Currency
import com.freshtuna.sharp.price.Price
import com.freshtuna.sharp.spec.*
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Pattern
import java.math.BigDecimal
import java.time.LocalDateTime

@Schema(description = "SKU 생성/수정 요청")
class SkuRequest(

    @Schema(description = "SKU의 이름")
    val name: String,

    @Schema(description = "바코드")
    val barcode: String,

    @Schema(description = "비고")
    val description: String = "",

    @Schema(description = "가격")
    val cost: BigDecimal,

    @Schema(description = "화폐규격")
    @Pattern(regexp = "^(USD|KRW)$")
    val currency: Currency,

    @Schema(description = "무게")
    val weight: BigDecimal,

    @Schema(description = "무게단위")
    @Pattern(regexp = "^(GRAM|KILOGRAM)$")
    val weightScale: WeightScale,

    @Schema(description = "가로")
    val width: BigDecimal,

    @Schema(description = "세로")
    val height: BigDecimal,

    @Schema(description = "높이")
    val depth: BigDecimal,

    @Schema(description = "가로/세로/높이 단위")
    @Pattern(regexp = "^(CM|M|INCH|FOOT)$")
    val dimensionScale: DimensionScale,

    @Schema(description = "필요시 유통기한 기입")
    val expireDate: LocalDateTime = LocalDateTime.of(0, 1, 1, 0, 0, 0),

    @Schema(description = "필요시 제조일자 기입")
    val manufactureDate: LocalDateTime = LocalDateTime.of(0, 1, 1, 0, 0, 0)
) {
    fun toCommand() = SkuCommand(
        name = name,
        barcode = barcode,
        description = description,

        price = Price(cost, currency),
        spec = Spec(Weight(weight, weightScale), Dimension(width, height, depth, dimensionScale)),

        expireDate = expireDate,
        manufactureDate = manufactureDate
    )
}
