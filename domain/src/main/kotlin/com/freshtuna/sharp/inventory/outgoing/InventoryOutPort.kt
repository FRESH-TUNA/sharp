package com.freshtuna.sharp.inventory.outgoing

import com.freshtuna.sharp.inventory.command.InventoryInOutCommand

interface InventoryOutPort {

    fun out(command: InventoryInOutCommand)
}
