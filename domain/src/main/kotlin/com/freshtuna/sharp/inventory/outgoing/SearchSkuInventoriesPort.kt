package com.freshtuna.sharp.inventory.outgoing

import com.freshtuna.sharp.id.PublicId
import com.freshtuna.sharp.inventory.domain.inventory.log.InventoryLog
import com.freshtuna.sharp.inventory.command.SearchSkuInventoryLogsCommand
import com.freshtuna.sharp.page.SharpPage
import com.freshtuna.sharp.page.SharpPageRequest

interface SearchSkuInventoriesPort {

    fun search(skuId: PublicId,
               command: SearchSkuInventoryLogsCommand,
               pageRequest: SharpPageRequest): SharpPage<InventoryLog>
}
