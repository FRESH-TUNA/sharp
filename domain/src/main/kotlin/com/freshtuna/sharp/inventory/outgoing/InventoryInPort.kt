package com.freshtuna.sharp.inventory.outgoing

import com.freshtuna.sharp.inventory.command.InventoryInOutCommand

interface InventoryInPort {

    fun `in`(command: InventoryInOutCommand)
}
