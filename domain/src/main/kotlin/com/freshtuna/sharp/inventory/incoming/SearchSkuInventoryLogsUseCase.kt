package com.freshtuna.sharp.inventory.incoming

import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.inventory.command.SearchSkuInventoryLogsCommand
import com.freshtuna.sharp.inventory.domain.inventory.log.InventoryLog

import com.freshtuna.sharp.page.SharpPage
import com.freshtuna.sharp.page.SharpPageRequest

interface SearchSkuInventoryLogsUseCase {

    fun search(skuId: SharpID,
               command: SearchSkuInventoryLogsCommand,
               pageRequest: SharpPageRequest,
               sellerId: SharpID): SharpPage<InventoryLog>
}
