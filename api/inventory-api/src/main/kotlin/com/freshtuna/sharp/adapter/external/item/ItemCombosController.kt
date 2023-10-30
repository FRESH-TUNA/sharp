package com.freshtuna.sharp.adapter.external.item

import com.freshtuna.sharp.api.response.BasicResponse
import com.freshtuna.sharp.api.response.DataResponse
import com.freshtuna.sharp.config.const.Url
import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.id.SharpIDInjection
import com.freshtuna.sharp.item.incoming.ItemCombosUseCase
import com.freshtuna.sharp.response.item.toResponse
import com.freshtuna.sharp.spec.item.ItemCombosSpec
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@Tag(name = "재고의 콤보 구성 조회")
@RestController
class ItemCombosController(
    private val useCase: ItemCombosUseCase
) : ItemCombosSpec {

    @GetMapping(Url.EXTERNAL.ITEM_COMBOS)
    override fun combos(
        @Parameter(description = "아이템 아이디") @PathVariable id: String,
        @Parameter(hidden = true) @SharpIDInjection sellerID: SharpID
    ): BasicResponse {

        val combos = useCase
            .combos(SharpID(id), sellerID)
            .map{ combo -> combo.toResponse() }

        return DataResponse.of(combos)
    }
}
