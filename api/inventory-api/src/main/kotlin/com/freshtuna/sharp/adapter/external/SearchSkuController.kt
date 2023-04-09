package com.freshtuna.sharp.adapter.external

import com.freshtuna.sharp.api.response.BasicResponse
import com.freshtuna.sharp.api.response.DataResponse
import com.freshtuna.sharp.config.const.Url
import com.freshtuna.sharp.inventory.incoming.SearchSkuUseCase

import com.freshtuna.sharp.request.SearchSkuRequest
import com.freshtuna.sharp.security.userDetail.UserDetailManager

import com.freshtuna.sharp.spec.SearchSkuSpec
import io.github.oshai.KotlinLogging
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute

import org.springframework.web.bind.annotation.RestController

@Tag(name = "SKU 조회")
@RestController
class SearchSkuController(
    private val useCase: SearchSkuUseCase
) : SearchSkuSpec {

    val log = KotlinLogging.logger {  }

    @GetMapping(Url.EXTERNAL.SKU)
    override fun search(@ModelAttribute request: SearchSkuRequest, pageable: Pageable): BasicResponse {

        return DataResponse.of(useCase.search(request.toCommand(pageable, UserDetailManager.getPublicId()), UserDetailManager.getPublicId()))
    }
}
