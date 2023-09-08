package com.freshtuna.sharp.inventory.adapter

import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.inventory.command.sku.SearchSkuCommand
import com.freshtuna.sharp.inventory.domain.SKU
import com.freshtuna.sharp.inventory.dto.SkuInventoriesDto
import com.freshtuna.sharp.inventory.entity.MariaDBSKU
import com.freshtuna.sharp.inventory.outgoing.SearchSkuPort
import com.freshtuna.sharp.inventory.repository.sku.SKUQueryRepository
import com.freshtuna.sharp.page.SharpPage
import com.freshtuna.sharp.page.SharpPageRequest
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class SearchSkuAdapterTest {

    private val repository: SKUQueryRepository = mockk()

    private val port: SearchSkuPort = SearchSkuAdapter(repository)

    @Test
    fun search() {

        // given
        val count: Long = 10
        val pageNumber: Long = 0
        val pageSize: Long = 10
        val totalPageCount: Long = 3
        val totalCount: Long = 21

        val pageRequest = SharpPageRequest()
        val command = SearchSkuCommand(sharpPageRequest = pageRequest)
        val sellerId: SharpID = mockk()


        // when
        val searchResult = SharpPage(
            mockk<List<MariaDBSKU>>(), count, pageNumber, pageSize, totalPageCount, totalCount)

        every {
            repository.search(command, sellerId)
        } returns searchResult

        val searchInventoriesResult = createSearchInventoriesResult(count)

        every {
            repository.skuWithInventories(searchResult.page)
        } returns searchInventoriesResult

        val finalResult = port.search(command, sellerId)

        // then
        assertEquals(finalResult.count, count)
        assertEquals(finalResult.pageNumber, pageNumber)
        assertEquals(finalResult.pageSize, pageSize)
        assertEquals(finalResult.totalPageCount, totalPageCount)
        assertEquals(finalResult.totalCount, totalCount)
    }

    private fun createSearchInventoriesResult(count: Long): List<SkuInventoriesDto> {
        val result = ArrayList<SkuInventoriesDto>()
        var count = count

        while (count>0) {
            val dto = mockk<SkuInventoriesDto>()

            every {
                dto.toDomain()
            } returns mockk()

            result.add(dto)
            count -= 1
        }
        return result
    }
}
