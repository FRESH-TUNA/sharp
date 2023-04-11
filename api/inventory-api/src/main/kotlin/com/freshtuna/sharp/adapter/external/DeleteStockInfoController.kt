package com.freshtuna.sharp.adapter.external

import com.freshtuna.sharp.api.response.BasicResponse
import com.freshtuna.sharp.api.response.MessageResponse
import com.freshtuna.sharp.config.const.Url
import com.freshtuna.sharp.id.PublicId
import com.freshtuna.sharp.inventory.incoming.DeleteStockInfoUseCase
import com.freshtuna.sharp.security.userDetail.UserDetailManager
import com.freshtuna.sharp.spec.DeleteStockInfoSpec
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@Tag(name = "SKU 재고 입고 취소")
@RestController
class DeleteStockInfoController(
    private val deleteStockInfoUseCase: DeleteStockInfoUseCase
) : DeleteStockInfoSpec{

    @DeleteMapping(Url.EXTERNAL.STOCK_INFO_ID)
    override fun delete(@PathVariable id: String): BasicResponse {
        deleteStockInfoUseCase.delete(PublicId(id), UserDetailManager.getPublicId())
        return MessageResponse.OK
    }
}
