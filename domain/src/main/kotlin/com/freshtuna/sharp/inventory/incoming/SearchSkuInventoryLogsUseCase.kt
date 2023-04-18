package com.freshtuna.sharp.inventory.incoming

import com.freshtuna.sharp.id.PublicId
import com.freshtuna.sharp.inventory.command.SearchSkuInventoryLogsCommand
import com.freshtuna.sharp.inventory.domain.inventory.log.InventoryLog

import com.freshtuna.sharp.page.SharpPage
import com.freshtuna.sharp.page.SharpPageRequest

interface SearchSkuInventoryLogsUseCase {

    fun search(skuId: PublicId,
               command: SearchSkuInventoryLogsCommand,
               pageRequest: SharpPageRequest,
               sellerId: PublicId): SharpPage<InventoryLog>
}
