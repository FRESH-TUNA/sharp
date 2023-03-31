package com.freshtuna.sharp.security.filter

import com.freshtuna.sharp.security.token.AuthToken
import com.freshtuna.sharp.security.token.spec.AuthTokenManager
import com.freshtuna.sharp.security.userDetail.SharpUserDetail
import com.freshtuna.tooth.util.HeaderUtil
import io.github.oshai.KotlinLogging
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder

import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import org.springframework.web.filter.OncePerRequestFilter

@Component
class AuthTokenFilter(
    private val authTokenManager: AuthTokenManager
) : OncePerRequestFilter() {

    private val log = KotlinLogging.logger {}

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {

        val token = HeaderUtil.getAuthorizationHeaderValue(request)

        if (!StringUtils.hasText(token)) {
            filterChain.doFilter(request, response)
            return
        }

        val jwt = AuthToken(token!!)

        try {
            authTokenManager.validate(jwt)
        } catch (e: RuntimeException) {
            filterChain.doFilter(request, response)
            return
        }

        val authorities: List<SimpleGrantedAuthority> = authTokenManager.extractRoles(jwt)
            .map { s -> SimpleGrantedAuthority(s.name) }

        val userDetail = SharpUserDetail(
            authTokenManager.extractPublicId(jwt),
            authorities
        )

        val authentication: Authentication = UsernamePasswordAuthenticationToken(
            userDetail, "", authorities
        )

        SecurityContextHolder.getContext().authentication = authentication

        filterChain.doFilter(request, response)
    }
}
