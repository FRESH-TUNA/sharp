package com.freshtuna.sharp.inventory

import com.freshtuna.sharp.id.PublicId
import com.freshtuna.sharp.inventory.command.SearchSkuStocksCommand
import com.freshtuna.sharp.inventory.domain.Inventory
import com.freshtuna.sharp.inventory.incoming.SearchSkuInventoriesUseCase
import com.freshtuna.sharp.inventory.outgoing.FindSkuPort
import com.freshtuna.sharp.inventory.outgoing.SearchSkuInventoriesPort
import com.freshtuna.sharp.oh.Oh
import com.freshtuna.sharp.page.SharpPage
import com.freshtuna.sharp.page.SharpPageRequest

import org.springframework.stereotype.Service

@Service
class SearchSkuInventoriesService(
    private val findSkuPort: FindSkuPort,
    private val searchSkuInventoriesPort: SearchSkuInventoriesPort,
) : SearchSkuInventoriesUseCase {

    override fun search(skuId: PublicId,
                        command: SearchSkuStocksCommand,
                        pageRequest: SharpPageRequest,
                        sellerId: PublicId
    ): SharpPage<Inventory> {

        val sku = findSkuPort.find(skuId)

        if(!sku.checkSameSeller(sellerId))
            Oh.badRequest()

        return searchSkuInventoriesPort.search(skuId, command, pageRequest)
    }
}
