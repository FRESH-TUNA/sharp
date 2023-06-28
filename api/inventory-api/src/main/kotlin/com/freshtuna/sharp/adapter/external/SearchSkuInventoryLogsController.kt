package com.freshtuna.sharp.adapter.external

import com.freshtuna.sharp.api.response.DataResponse
import com.freshtuna.sharp.config.const.Url
import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.id.SharpIDInjection
import com.freshtuna.sharp.inventory.command.SearchSkuInventoryLogsCommand
import com.freshtuna.sharp.inventory.incoming.SearchSkuInventoryLogsUseCase
import com.freshtuna.sharp.page.SharpPage
import com.freshtuna.sharp.response.InventoryResponse
import com.freshtuna.sharp.response.toResponse
import com.freshtuna.sharp.spec.SearchSkuInventoriesSpec
import com.freshtuna.sharp.util.SpringPageableConverter
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.data.domain.Pageable

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@Tag(name = "SKU 재고 입/출고 내역 조회")
@RestController
class SearchSkuInventoryLogsController(
    private val searchSkuInventoryLogsUseCase: SearchSkuInventoryLogsUseCase
) : SearchSkuInventoriesSpec{

    @GetMapping(Url.EXTERNAL.SKU_ID_INVENTORY_LOGS)
    override fun search(
        @PathVariable("id") skuId: String,
        pageable: Pageable,
        @Parameter(hidden = true) @SharpIDInjection sellerID: SharpID
    ): DataResponse<SharpPage<InventoryResponse>> {

        val pageRequest = SpringPageableConverter.convert(pageable)

        val inventoryPage = searchSkuInventoryLogsUseCase.search(
            SharpID(skuId),
            SearchSkuInventoryLogsCommand(),
            pageRequest,
            sellerID
        )

        val resultPage = inventoryPage.page.map { i -> i.toResponse() }.toList()

        return DataResponse.of(SharpPage(resultPage, inventoryPage.totalCount, pageRequest))
    }
}
