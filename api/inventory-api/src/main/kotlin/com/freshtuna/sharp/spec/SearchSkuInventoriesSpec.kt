package com.freshtuna.sharp.spec

import com.freshtuna.sharp.api.response.BasicResponse
import com.freshtuna.sharp.id.SharpID
import org.springframework.data.domain.Pageable

interface SearchSkuInventoriesSpec {

    fun search(skuId: String, pageable: Pageable, sellerId: SharpID): BasicResponse
}
