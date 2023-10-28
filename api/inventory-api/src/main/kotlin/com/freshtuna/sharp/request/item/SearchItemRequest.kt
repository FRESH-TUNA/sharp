package com.freshtuna.sharp.request.item

import com.freshtuna.sharp.item.command.SearchItemCommand
import com.freshtuna.sharp.util.SpringPageableConverter
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.data.domain.Pageable

class SearchItemRequest(
    @Schema(description = "쿼리", nullable = false, example = "파카리슈웨트")
    private val query: String = ""
) {

    fun toCommand(pageable: Pageable): SearchItemCommand
            = SearchItemCommand(query, SpringPageableConverter.convert(pageable))
}
