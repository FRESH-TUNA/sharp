package com.freshtuna.sharp.inventory.incoming

import com.freshtuna.sharp.inventory.domain.inventory.log.InventoryLogReason

interface GetInventoryInReasonUseCase {

    fun getInventoryInReasons(): List<InventoryLogReason>
}
