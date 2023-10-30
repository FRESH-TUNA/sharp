package com.freshtuna.sharp.adapter.external.item

import com.freshtuna.sharp.api.response.BasicResponse
import com.freshtuna.sharp.api.response.DataResponse
import com.freshtuna.sharp.config.const.Url
import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.id.SharpIDInjection
import com.freshtuna.sharp.item.incoming.ShowItemUseCase
import com.freshtuna.sharp.response.item.toResponse
import com.freshtuna.sharp.spec.item.ShowItemSpec
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RestController
@Tag(name = "재고(아이템) 상세 조회")
@RestController
class ShowItemController(
    private val useCase: ShowItemUseCase
) : ShowItemSpec {

    @GetMapping(Url.EXTERNAL.ITEM_ID)
    override fun show(
        @Parameter(description = "아이템 아이디") @PathVariable id: String,
        @Parameter(hidden = true) @SharpIDInjection sellerId: SharpID
    ): BasicResponse {

        val item = useCase.show(SharpID(id), sellerId)

        return DataResponse.of(item.toResponse())
    }
}
