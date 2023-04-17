package com.freshtuna.sharp.spec

import com.freshtuna.sharp.api.response.BasicResponse
import org.springframework.data.domain.Pageable

interface SearchSkuInventoriesSpec {

    fun search(skuId: String, pageable: Pageable): BasicResponse
}
