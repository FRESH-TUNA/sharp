package com.freshtuna.sharp.spec

import com.freshtuna.sharp.api.response.BasicResponse
import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.request.SearchSkuRequest
import org.springframework.data.domain.Pageable

interface SearchSkuSpec {

    fun search(request: SearchSkuRequest, pageable: Pageable, sellerId: SharpID): BasicResponse
}
