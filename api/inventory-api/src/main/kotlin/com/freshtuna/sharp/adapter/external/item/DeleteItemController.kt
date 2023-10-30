package com.freshtuna.sharp.adapter.external.item

import com.freshtuna.sharp.api.response.BasicResponse
import com.freshtuna.sharp.api.response.MessageResponse
import com.freshtuna.sharp.config.const.Url
import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.id.SharpIDInjection
import com.freshtuna.sharp.item.incoming.DeleteItemUseCase
import com.freshtuna.sharp.spec.item.DeleteItemSpec
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@Tag(name = "재고 타입 삭제")
@RestController
class DeleteItemController(
    private val useCase: DeleteItemUseCase
) : DeleteItemSpec {

    @DeleteMapping(Url.EXTERNAL.ITEM_ID)
    override fun update(
        @Parameter(description = "아이템 아이디") @PathVariable id: String,
        @Parameter(hidden = true) @SharpIDInjection sellerId: SharpID
    ): BasicResponse {

        useCase.delete(SharpID(id), sellerId)
        return MessageResponse.OK
    }
}
