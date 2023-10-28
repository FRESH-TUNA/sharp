package com.freshtuna.sharp.spec.item

import com.freshtuna.sharp.api.response.BasicResponse
import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.request.item.SearchItemRequest
import org.springframework.data.domain.Pageable

interface SearchItemSpec {
    fun search(request: SearchItemRequest, pageable: Pageable, sellerID: SharpID): BasicResponse
}
