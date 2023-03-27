package com.freshtuna.sharp.adapter.external

import com.freshtuna.sharp.config.const.Url
import com.freshtuna.sharp.request.StockInRequest
import com.freshtuna.sharp.spec.StockInSpec
import com.freshtuna.sharp.inventory.incoming.StockInUseCase
import com.freshtuna.tooth.api.response.BasicResponse
import com.freshtuna.tooth.api.response.MessageResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class StockInController(
    private val useCase: StockInUseCase
) : StockInSpec {

    @PostMapping(Url.EXTERNAL.STOCK_IN)
    override fun stockIn(request: StockInRequest): BasicResponse {
        useCase.stockIn(request.toCommand())
        return MessageResponse.OK
    }
}
