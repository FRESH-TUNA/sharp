package com.freshtuna.sharp.spec.sku

import com.freshtuna.sharp.api.response.BasicResponse
import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.request.sku.SearchSkuRequest
import org.springframework.data.domain.Pageable

interface SearchSkuSpec {

    fun search(request: SearchSkuRequest, pageable: Pageable, sellerId: SharpID): BasicResponse
}
