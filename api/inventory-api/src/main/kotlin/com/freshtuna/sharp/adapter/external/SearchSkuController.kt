package com.freshtuna.sharp.adapter.external

import com.freshtuna.sharp.api.response.DataResponse
import com.freshtuna.sharp.config.const.Url
import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.id.SharpIDInjection
import com.freshtuna.sharp.inventory.incoming.SearchSkuUseCase

import com.freshtuna.sharp.page.SharpPage

import com.freshtuna.sharp.request.SearchSkuRequest
import com.freshtuna.sharp.response.SKUSearchResponse
import com.freshtuna.sharp.response.toSearchResponse

import com.freshtuna.sharp.spec.SearchSkuSpec
import com.freshtuna.sharp.util.SpringPageableConverter
import io.github.oshai.KotlinLogging
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.annotation.Nullable
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
    override fun search(
        @ModelAttribute request: SearchSkuRequest,
        @Nullable pageable: Pageable,
        @Parameter(hidden = true) @SharpIDInjection sellerID: SharpID
    ): DataResponse<SharpPage<SKUSearchResponse>> {

        val skuPage = useCase.search(request.toCommand(pageable), sellerID)

        val skuSearchResult = skuPage.page.stream()
            .map { sku -> sku.toSearchResponse() }
            .toList()

        val resultPage = SharpPage(skuSearchResult, skuPage.totalCount, SpringPageableConverter.convert(pageable))

        return DataResponse.of(resultPage)
    }
}
