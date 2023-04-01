package com.freshtuna.sharp.adapter.external

import com.freshtuna.sharp.config.const.Url
import com.freshtuna.sharp.inventory.incoming.NewSkuUseCase
import com.freshtuna.sharp.request.SkuRequest
import com.freshtuna.sharp.security.userDetail.UserDetailManager
import com.freshtuna.sharp.spec.NewSkuSpec
import com.freshtuna.sharp.api.response.BasicResponse
import com.freshtuna.sharp.api.response.DataResponse
import io.github.oshai.KotlinLogging

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class NewSkuController(
    private val useCase: NewSkuUseCase
) : NewSkuSpec {

    val log = KotlinLogging.logger {  }

    @PostMapping(Url.EXTERNAL.SKU)
    override fun new(@RequestBody request: SkuRequest): BasicResponse {
        val result = useCase.new(request.toCommand(UserDetailManager.getUserDetail().id))
        return DataResponse.of(result)
    }
}
