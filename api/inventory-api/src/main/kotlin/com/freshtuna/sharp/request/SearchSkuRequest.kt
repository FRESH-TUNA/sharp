package com.freshtuna.sharp.request

import com.freshtuna.sharp.inventory.command.SearchSkuCommand
import com.freshtuna.sharp.util.SpringPageableConverter
import org.springframework.data.domain.Pageable

class SearchSkuRequest(
    private val query: String
) {

    fun toCommand(pageable: Pageable): SearchSkuCommand
        = SearchSkuCommand(query, SpringPageableConverter.convert(pageable))
}
