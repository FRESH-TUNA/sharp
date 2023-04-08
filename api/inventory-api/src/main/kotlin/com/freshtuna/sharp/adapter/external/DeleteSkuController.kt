package com.freshtuna.sharp.adapter.external

import com.freshtuna.sharp.api.response.BasicResponse
import com.freshtuna.sharp.api.response.MessageResponse
import com.freshtuna.sharp.config.const.Url
import com.freshtuna.sharp.id.PublicId
import com.freshtuna.sharp.inventory.command.DeleteSkuCommand
import com.freshtuna.sharp.inventory.incoming.DeleteSkuUseCase
import com.freshtuna.sharp.security.userDetail.UserDetailManager
import com.freshtuna.sharp.spec.DeleteSkuSpec
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@Tag(name = "SKU 삭제")
@RestController
class DeleteSkuController(
    private val useCase: DeleteSkuUseCase
) : DeleteSkuSpec {

    @DeleteMapping(Url.EXTERNAL.SKU_ID)
    override fun delete(@Parameter(description = "SKU 아이디") @PathVariable id: String): BasicResponse {
        val command = DeleteSkuCommand(
            PublicId(id),
            UserDetailManager.getPublicId()
        )

        useCase.delete(command)
        return MessageResponse.OK
    }
}
