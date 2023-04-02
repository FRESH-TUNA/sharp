package com.freshtuna.sharp.adapter.external

import com.freshtuna.sharp.api.response.BasicResponse
import com.freshtuna.sharp.api.response.DataResponse
import com.freshtuna.sharp.config.const.Url
import com.freshtuna.sharp.id.PublicId
import com.freshtuna.sharp.inventory.command.DetailSkuCommand

import com.freshtuna.sharp.inventory.incoming.SkuDetailUseCase
import com.freshtuna.sharp.security.userDetail.UserDetailManager
import com.freshtuna.sharp.spec.SkuDetailSpec

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class SkuDetailController(
    private val skuDetailUseCase: SkuDetailUseCase
) : SkuDetailSpec{

    @GetMapping(Url.EXTERNAL.SKU_ID)
    override fun detail(@PathVariable id: String): BasicResponse {
        val command = DetailSkuCommand(
            PublicId(id),
            UserDetailManager.getPublicId()
        )

        return DataResponse.of(skuDetailUseCase.detail(command))
    }
}
