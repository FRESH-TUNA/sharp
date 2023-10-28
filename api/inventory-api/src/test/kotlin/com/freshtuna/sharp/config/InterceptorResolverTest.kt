package com.freshtuna.sharp.config

import com.freshtuna.sharp.StockApiApplication
import com.freshtuna.sharp.adapter.external.sku.InventoryInController
import com.freshtuna.sharp.config.intercepter.SellerAuthInterceptor
import com.freshtuna.sharp.config.resolver.SharpIDResolver
import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.id.SharpPublicID
import com.freshtuna.sharp.inventory.incoming.InventoryInUseCase
import com.freshtuna.sharp.security.token.spec.AuthTokenManager
import com.freshtuna.sharp.seller.incoming.SellerManageUseCase
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpHeaders

import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    classes = [StockApiApplication::class]
)
@ActiveProfiles("test")
class InterceptorResolverTest {

    private val inventoryInUseCase: InventoryInUseCase = mockk()

    private val authTokenManager: AuthTokenManager = mockk()

    private val sellerManageUseCase: SellerManageUseCase = mockk()

    private val sellerAuthInterceptor = SellerAuthInterceptor(authTokenManager, sellerManageUseCase)

    private val mockMvc = MockMvcBuilders
        .standaloneSetup(InventoryInController(inventoryInUseCase))
        .addInterceptors(sellerAuthInterceptor)
        .setCustomArgumentResolvers(SharpIDResolver())
        .build()

    @Value("\${sharp.test.auth-token}")
    private lateinit var accessToken: String

    @Test
    @DisplayName("재고 입고 시스템 테스트")
    fun stockIn() {

        // given: localId, password
        val payload = """
            {
                "reason": "NEW",
                "count": "10",
                "description": "description"
            }
            """.trimIndent()

        // when
        every { authTokenManager.validate(any()) } returns Unit
        every { authTokenManager.extractPublicId(any()) } returns SharpPublicID(accessToken)
        every { sellerManageUseCase.findOrCreateID(any()) } returns SharpID("1")
        every { inventoryInUseCase.new(any(), any()) } returns Unit
        // then
        mockMvc.perform(
            MockMvcRequestBuilders.post("/inventory/sku/1/in")
                .header(HttpHeaders.AUTHORIZATION, accessToken)
                .content(payload)
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
    }
}
