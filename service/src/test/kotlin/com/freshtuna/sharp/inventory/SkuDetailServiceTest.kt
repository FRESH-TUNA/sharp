package com.freshtuna.sharp.inventory

import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.inventory.command.DeleteSkuCommand
import com.freshtuna.sharp.inventory.command.DetailSkuCommand
import com.freshtuna.sharp.inventory.domain.SKU
import com.freshtuna.sharp.inventory.incoming.SkuDetailUseCase
import com.freshtuna.sharp.inventory.outgoing.FindSkuPort
import com.freshtuna.sharp.oh.exception.SharpException
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName

class SkuDetailServiceTest {

    private val findSkuPort: FindSkuPort = mockk()

    private val useCase: SkuDetailUseCase = SkuDetailService(findSkuPort)

    @Test
    @DisplayName("해당 SKU를 관리하는 셀러라면 조회 가능")
    fun mustSameSeller() {

        // given
        val sellerID = SharpID(1L)
        val skuID = SharpID(1L)
        val command = DetailSkuCommand(skuID)

        // when
        val sku = mockk<SKU>()

        every { findSkuPort.find(command.skuId) } returns sku
        every { sku.id } returns skuID
        every { sku.checkSameSeller(sellerID) } returns true

        // then
        org.junit.jupiter.api.assertDoesNotThrow { useCase.detail(command, sellerID) }
    }

    @Test
    @DisplayName("해당 SKU를 관리하는 셀러가 아니면 삭제 불가능")
    fun deleteFailIFCorrectSeller() {

        // given
        val sellerID = SharpID(1L)
        val skuID = SharpID(1L)
        val command = DetailSkuCommand(skuID)

        // when
        val sku = mockk<SKU>()

        every { findSkuPort.find(command.skuId) } returns sku
        every { sku.checkSameSeller(sellerID) } returns false

        // then
        org.junit.jupiter.api.assertThrows<SharpException> { useCase.detail(command, sellerID) }
    }
}
