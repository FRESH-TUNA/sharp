package com.freshtuna.sharp.inventory

import com.freshtuna.sharp.id.PublicId
import com.freshtuna.sharp.inventory.command.SearchSkuInventoryLogsCommand
import com.freshtuna.sharp.inventory.domain.inventory.log.InventoryLog
import com.freshtuna.sharp.inventory.incoming.SearchSkuInventoryLogsUseCase
import com.freshtuna.sharp.inventory.outgoing.FindSkuPort
import com.freshtuna.sharp.inventory.outgoing.SearchSkuInventoriesPort
import com.freshtuna.sharp.oh.Oh
import com.freshtuna.sharp.page.SharpPage
import com.freshtuna.sharp.page.SharpPageRequest

import org.springframework.stereotype.Service

@Service
class SearchSkuInventoryLogsService(
    private val findSkuPort: FindSkuPort,
    private val searchSkuInventoriesPort: SearchSkuInventoriesPort,
) : SearchSkuInventoryLogsUseCase {

    override fun search(skuId: PublicId,
                        command: SearchSkuInventoryLogsCommand,
                        pageRequest: SharpPageRequest,
                        sellerId: PublicId
    ): SharpPage<InventoryLog> {

        val sku = findSkuPort.find(skuId)

        if(!sku.checkSameSeller(sellerId))
            Oh.badRequest()

        return searchSkuInventoriesPort.search(skuId, command, pageRequest)
    }
}
