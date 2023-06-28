package com.freshtuna.sharp.config.intercepter

import com.freshtuna.sharp.config.const.Const
import com.freshtuna.sharp.security.token.AuthToken
import com.freshtuna.sharp.security.token.spec.AuthTokenManager
import com.freshtuna.sharp.seller.incoming.SellerManageUseCase
import com.freshtuna.tooth.util.HeaderUtil
import io.github.oshai.KotlinLogging
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import org.springframework.web.servlet.HandlerInterceptor

@Component
class SellerAuthInterceptor(
    private val authTokenManager: AuthTokenManager,
    private val sellerManageUseCase: SellerManageUseCase
) : HandlerInterceptor {

    val log = KotlinLogging.logger {  }

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {

        val token = HeaderUtil.getAuthorizationHeaderValue(request)

        if (!StringUtils.hasText(token))
            return true

        val jwt = AuthToken(token!!)

        try {
            authTokenManager.validate(jwt)
        } catch (e: RuntimeException) {
            return true
        }

        val publicID = authTokenManager.extractPublicId(jwt)

        request.setAttribute(Const.SHARP_ID_HEADER_KEY, sellerManageUseCase.findOrCreateID(publicID))
        return true
    }
}
