package com.freshtuna.sharp.adapter.external

import com.freshtuna.sharp.api.response.BasicResponse
import com.freshtuna.sharp.api.response.DataResponse
import com.freshtuna.sharp.config.const.Url
import com.freshtuna.sharp.id.PublicId
import com.freshtuna.sharp.inventory.command.SearchSkuStocksCommand
import com.freshtuna.sharp.inventory.incoming.SearchStockInfoUseCase
import com.freshtuna.sharp.security.userDetail.UserDetailManager
import com.freshtuna.sharp.spec.SearchSkuStockInfoSpec
import com.freshtuna.sharp.util.SpringPageableConverter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.data.domain.Pageable

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@Tag(name = "SKU의 재고 입고 내역 조회")
@RestController
class SearchSkuStockInfoController(
    private val searchSkuStockInfoUseCase: SearchStockInfoUseCase
) : SearchSkuStockInfoSpec{

    @GetMapping(Url.EXTERNAL.SKU_ID_STOCK_INFO)
    override fun search(@PathVariable("id") skuId: String, pageable: Pageable): BasicResponse {

        return DataResponse.of(
            searchSkuStockInfoUseCase.search(
                PublicId(skuId),
                SearchSkuStocksCommand(),
                SpringPageableConverter.convert(pageable),
                UserDetailManager.getPublicId()
        ))
    }
}
