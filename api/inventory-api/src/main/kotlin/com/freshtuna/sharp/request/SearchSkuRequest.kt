package com.freshtuna.sharp.request

import com.freshtuna.sharp.inventory.command.sku.SearchSkuCommand
import com.freshtuna.sharp.util.SpringPageableConverter
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.data.domain.Pageable

class SearchSkuRequest(
    @Schema(description = "쿼리", nullable = false, example = "파카리슈웨트")
    private val query: String = ""
) {

    fun toCommand(pageable: Pageable): SearchSkuCommand
        = SearchSkuCommand(query, SpringPageableConverter.convert(pageable))
}
