package com.freshtuna.sharp.adapter.external

import com.freshtuna.sharp.config.const.Url
import com.freshtuna.sharp.request.StockInRequest
import com.freshtuna.sharp.spec.StockInSpec
import com.freshtuna.sharp.inventory.incoming.StockInUseCase
import com.freshtuna.sharp.api.response.BasicResponse

import com.freshtuna.sharp.api.response.MessageResponse
import com.freshtuna.sharp.security.userDetail.UserDetailManager

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class StockInController(
    private val useCase: StockInUseCase
) : StockInSpec {

    @PostMapping(Url.EXTERNAL.STOCK)
    override fun stockIn(@RequestBody request: StockInRequest): BasicResponse {

        useCase.stockIn(request.toCommand(UserDetailManager.getPublicId()))
        return MessageResponse.OK
    }
}