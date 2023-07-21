package com.freshtuna.sharp.inventory

import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.inventory.command.DeleteSkuCommand
import com.freshtuna.sharp.inventory.command.InventoryInOutCommand
import com.freshtuna.sharp.inventory.domain.SKU
import com.freshtuna.sharp.inventory.incoming.InventoryInUseCase
import com.freshtuna.sharp.inventory.outgoing.DeleteSkuPort
import com.freshtuna.sharp.inventory.outgoing.FindSkuPort
import com.freshtuna.sharp.inventory.outgoing.InventoryInPort
import com.freshtuna.sharp.inventory.outgoing.NewInventoryLogPort
import com.freshtuna.sharp.oh.exception.SharpException
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class InventoryInServiceTest {

    private val newInventoryLogPort: NewInventoryLogPort = mockk()
    private val inventoryInPort: InventoryInPort = mockk()
    private val findSkuPort: FindSkuPort = mockk()

    private val service: InventoryInUseCase = InventoryInService(
        newInventoryLogPort, inventoryInPort, findSkuPort
    )

    @Test
    @DisplayName("사유가 입고에 맞지 않으면 불가")
    fun reasonFailCheck() {

        // given
        val sellerID = SharpID(1L)
        val skuID = SharpID(1L)
        val command = mockk<InventoryInOutCommand>()

        // when
        val sku = mockk<SKU>()
        every { command.skuId } returns skuID
        every { findSkuPort.find(command.skuId) } returns sku
        every { command.isIN() } returns false

        // then
        org.junit.jupiter.api.assertThrows<SharpException> { service.new(command, sellerID) }
    }

    @Test
    @DisplayName("셀러가 다르면 입고 불가")
    fun differentSellerFailCheck() {

        // given
        val sellerID = SharpID(1L)
        val skuID = SharpID(1L)
        val command = mockk<InventoryInOutCommand>()

        // when
        val sku = mockk<SKU>()
        every { command.skuId } returns skuID
        every { findSkuPort.find(command.skuId) } returns sku
        every { command.isIN() } returns true
        every { sku.checkSameSeller(sellerID) } returns false

        // then
        org.junit.jupiter.api.assertThrows<SharpException> { service.new(command, sellerID) }
    }

    @Test
    @DisplayName("갯수가 1이상이어야만 입고 가능")
    fun countNotValidFailCheck() {

        // given
        val sellerID = SharpID(1L)
        val skuID = SharpID(1L)
        val command = mockk<InventoryInOutCommand>()

        // when
        val sku = mockk<SKU>()
        every { command.skuId } returns skuID
        every { findSkuPort.find(command.skuId) } returns sku
        every { command.isIN() } returns true
        every { sku.checkSameSeller(sellerID) } returns true
        every { command.countIsNotValid() } returns true

        // then
        org.junit.jupiter.api.assertThrows<SharpException> { service.new(command, sellerID) }
    }

    @Test
    @DisplayName("입고 성공 테스트")
    fun successTest() {

        // given
        val sellerID = SharpID(1L)
        val skuID = SharpID(1L)
        val command = mockk<InventoryInOutCommand>()

        // when
        val sku = mockk<SKU>()
        every { command.skuId } returns skuID
        every { findSkuPort.find(command.skuId) } returns sku
        every { command.isIN() } returns true
        every { sku.checkSameSeller(sellerID) } returns true
        every { command.countIsNotValid() } returns false

        every { newInventoryLogPort.new(command) } returns mockk()
        every { inventoryInPort.`in`(command) } returns mockk()

        // then
        org.junit.jupiter.api.assertDoesNotThrow { service.new(command, sellerID) }
    }
}
