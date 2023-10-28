package com.freshtuna.sharp.adapter.external.item

import com.freshtuna.sharp.api.response.BasicResponse
import com.freshtuna.sharp.api.response.DataResponse
import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.id.SharpIDInjection
import com.freshtuna.sharp.item.incoming.SearchItemUseCase
import com.freshtuna.sharp.page.SharpPage
import com.freshtuna.sharp.request.item.SearchItemRequest
import com.freshtuna.sharp.response.item.toResponse

import com.freshtuna.sharp.spec.item.SearchItemSpec
import com.freshtuna.sharp.util.SpringPageableConverter
import io.swagger.v3.oas.annotations.Parameter
import jakarta.annotation.Nullable
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RestController

@RestController
class SearchItemController(
    private val searchItemUseCase: SearchItemUseCase
) : SearchItemSpec{

    override fun search(
        @ModelAttribute request: SearchItemRequest,
        @Nullable pageable: Pageable,
        @Parameter(hidden = true) @SharpIDInjection sellerID: SharpID
    ): BasicResponse {

        val command = request.toCommand(pageable)

        val result = searchItemUseCase.search(command, sellerID)

        val skuSearchResult = result.page.stream()
            .map { item -> item.toResponse() }
            .toList()

        val resultPage = SharpPage(skuSearchResult, result.totalCount, SpringPageableConverter.convert(pageable))

        return DataResponse.of(resultPage)
    }
}
