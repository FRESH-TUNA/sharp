package com.freshtuna.sharp.inventory.outgoing

import com.freshtuna.sharp.inventory.command.InventoryInOutCommand
import com.freshtuna.sharp.inventory.domain.inventory.log.InventoryLog

interface NewInventoryLogPort {

    fun new(command: InventoryInOutCommand): InventoryLog
}
