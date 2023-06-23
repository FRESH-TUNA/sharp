package com.freshtuna.sharp.adapter.external


import com.freshtuna.sharp.api.response.DataResponse
import com.freshtuna.sharp.config.const.Url

import com.freshtuna.sharp.inventory.incoming.GetInventoryInReasonUseCase
import com.freshtuna.sharp.inventory.incoming.GetInventoryOutReasonUseCase
import com.freshtuna.sharp.spec.InventoryInReasonsSpec
import com.freshtuna.sharp.spec.InventoryOutReasonsSpec
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class GetInventoryReasonsController(
    private val getInventoryInReasonUseCase: GetInventoryInReasonUseCase,
    private val getInventoryOutReasonUseCase: GetInventoryOutReasonUseCase
) : InventoryInReasonsSpec, InventoryOutReasonsSpec {

    @Tag(name = "SKU 조회")
    @GetMapping(Url.EXTERNAL.IN_REASONS)
    override fun getInventoryInReasons()
        = DataResponse.of(getInventoryInReasonUseCase.getInventoryInReasons())

    @Tag(name = "SKU 조회")
    @GetMapping(Url.EXTERNAL.OUT_REASONS)
    override fun getInventoryOutReasons()
        = DataResponse.of(getInventoryOutReasonUseCase.getInventoryOutReasons())
}
