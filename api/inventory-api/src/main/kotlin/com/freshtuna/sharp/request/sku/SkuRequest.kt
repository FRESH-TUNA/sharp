package com.freshtuna.sharp.request.sku

import com.freshtuna.sharp.inventory.command.SkuCommand
import com.freshtuna.sharp.price.Currency
import com.freshtuna.sharp.price.Price
import com.freshtuna.sharp.spec.*
import com.freshtuna.sharp.validator.DigitPreChecker
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Pattern
import java.time.LocalDateTime

@Schema(description = "SKU 생성/수정 요청")
class SkuRequest(

    @Schema(description = "SKU 고유 식별자")
    val skuId: String,

    @Schema(description = "바코드")
    val barcode: String = "",

    @Schema(description = "비고")
    val description: String = "",

    @Schema(description = "공급원가")
    val cost: String = "",

    @Schema(description = "공급원가 화폐규격")
    @Pattern(regexp = "^(USD|KRW)$")
    val currency: Currency = Currency.KRW,

    @Schema(description = "무게")
    val weight: String = "",

    @Schema(description = "무게단위")
    @Pattern(regexp = "^(GRAM|KILOGRAM)$")
    val weightScale: WeightScale,

    @Schema(description = "가로")
    val width: String = "",

    @Schema(description = "세로")
    val height: String = "",

    @Schema(description = "높이")
    val depth: String = "",

    @Schema(description = "가로/세로/높이 단위")
    @Pattern(regexp = "^(CM|M|INCH|FOOT)$")
    val dimensionScale: DimensionScale = DimensionScale.CM,

    @Schema(description = "필요시 유통기한 기입")
    val expireDate: LocalDateTime = LocalDateTime.of(0, 1, 1, 0, 0, 0),

    @Schema(description = "필요시 제조일자 기입")
    val manufactureDate: LocalDateTime = LocalDateTime.of(0, 1, 1, 0, 0, 0)
) {
    fun toCommand() : SkuCommand {

        val weight = DigitPreChecker.check(weight)

        val width = DigitPreChecker.check(width)
        val height = DigitPreChecker.check(height)
        val depth = DigitPreChecker.check(depth)

        return SkuCommand(
            skuId = skuId,
            barcode = barcode,
            description = description,

            price = Price(cost, currency),
            spec = Spec(Weight(weight, weightScale), Dimension(width, height, depth, dimensionScale)),

            expireDate = expireDate,
            manufactureDate = manufactureDate
        )
    }
}
