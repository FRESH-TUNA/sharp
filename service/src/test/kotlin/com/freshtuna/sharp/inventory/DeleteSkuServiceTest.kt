package com.freshtuna.sharp.inventory

import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.inventory.command.DeleteSkuCommand
import com.freshtuna.sharp.inventory.domain.SKU
import com.freshtuna.sharp.inventory.outgoing.DeleteSkuPort
import com.freshtuna.sharp.inventory.outgoing.FindSkuPort
import com.freshtuna.sharp.oh.exception.SharpException
import io.mockk.every

import io.mockk.mockk
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class DeleteSkuServiceTest {
    private val findSkuPort: FindSkuPort = mockk()
    private val deleteSkuPort: DeleteSkuPort = mockk()
    private val service = DeleteSkuService(findSkuPort, deleteSkuPort)
    @Test
    @DisplayName("해당 SKU를 관리하는 셀러라면 삭제 가능")
    fun deleteSuccessIFCorrectSeller() {

        // given
        val sellerID = SharpID(1L)
        val skuID = SharpID(1L)
        val command = DeleteSkuCommand(skuID)

        // when
        val sku = mockk<SKU>()

        every { findSkuPort.find(command.skuId) } returns sku
        every { sku.id } returns skuID
        every { sku.checkSameSeller(sellerID) } returns true
        every { deleteSkuPort.delete(skuID) } returns Unit

        // then
        assertDoesNotThrow { service.delete(command, sellerID) }
    }

    @Test
    @DisplayName("해당 SKU를 관리하는 셀러가 아니면 삭제 불가능")
    fun deleteFailIFCorrectSeller() {

        // given
        val sellerID = SharpID(1L)
        val skuID = SharpID(1L)
        val command = DeleteSkuCommand(skuID)

        // when
        val sku = mockk<SKU>()

        every { findSkuPort.find(command.skuId) } returns sku
        every { sku.checkSameSeller(sellerID) } returns false

        // then
        assertThrows<SharpException> { service.delete(command, sellerID) }
    }
}
