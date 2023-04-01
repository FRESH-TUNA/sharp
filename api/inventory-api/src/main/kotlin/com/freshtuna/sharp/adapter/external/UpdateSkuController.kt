package com.freshtuna.sharp.adapter.external

import com.freshtuna.sharp.api.response.BasicResponse
import com.freshtuna.sharp.api.response.MessageResponse
import com.freshtuna.sharp.config.const.Url
import com.freshtuna.sharp.id.PublicId
import com.freshtuna.sharp.inventory.incoming.UpdateSkuUseCase
import com.freshtuna.sharp.request.SkuRequest
import com.freshtuna.sharp.security.userDetail.UserDetailManager
import com.freshtuna.sharp.spec.UpdateSkuSpec
import org.springframework.web.bind.annotation.PathVariable

import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UpdateSkuController(
    private val useCase: UpdateSkuUseCase
) : UpdateSkuSpec{

    @PutMapping(Url.EXTERNAL.SKU_ID)
    override fun update(@RequestBody request: SkuRequest, @PathVariable id: String): BasicResponse {
        useCase.update(request.toUpdateCommand(UserDetailManager.getPublicId(), PublicId(id)))
        return MessageResponse.OK
    }
}
