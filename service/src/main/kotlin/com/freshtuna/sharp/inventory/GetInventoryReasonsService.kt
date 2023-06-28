package com.freshtuna.sharp.inventory

import com.freshtuna.sharp.inventory.domain.inventory.log.InventoryLogReason
import com.freshtuna.sharp.inventory.incoming.GetInventoryInReasonUseCase
import com.freshtuna.sharp.inventory.incoming.GetInventoryOutReasonUseCase
import org.springframework.stereotype.Service

@Service
class GetInventoryReasonsService : GetInventoryInReasonUseCase, GetInventoryOutReasonUseCase {

    override fun getInventoryInReasons() = InventoryLogReason.IN_REASONS

    override fun getInventoryOutReasons()= InventoryLogReason.OUT_REASONS
}
