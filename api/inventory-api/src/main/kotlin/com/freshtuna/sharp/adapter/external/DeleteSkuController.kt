package com.freshtuna.sharp.adapter.external

import com.freshtuna.sharp.api.response.BasicResponse
import com.freshtuna.sharp.api.response.MessageResponse
import com.freshtuna.sharp.config.const.Url
import com.freshtuna.sharp.id.PublicId
import com.freshtuna.sharp.inventory.command.DeleteSkuCommand
import com.freshtuna.sharp.inventory.incoming.DeleteSkuUseCase
import com.freshtuna.sharp.security.userDetail.UserDetailManager
import com.freshtuna.sharp.spec.DeleteSkuSpec
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class DeleteSkuController(
    private val useCase: DeleteSkuUseCase
) : DeleteSkuSpec {

    @DeleteMapping(Url.EXTERNAL.SKU_ID)
    override fun delete(@PathVariable id: String): BasicResponse {
        val command = DeleteSkuCommand(
            PublicId(id),
            UserDetailManager.getPublicId()
        )

        useCase.delete(command)
        return MessageResponse.OK
    }
}
