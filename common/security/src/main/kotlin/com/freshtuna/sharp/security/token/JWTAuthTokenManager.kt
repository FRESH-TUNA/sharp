package com.freshtuna.sharp.security.token

import com.freshtuna.sharp.id.PublicId
import com.freshtuna.sharp.member.constant.Role
import com.freshtuna.sharp.oh.Oh
import com.freshtuna.sharp.security.token.spec.AuthTokenManager
import io.github.oshai.KotlinLogging

import io.jsonwebtoken.Jwts
import java.security.Key

/**
 * https://darutk.medium.com/understanding-id-token-5f83f50fa02e
 */
class JWTAuthTokenManager(
    private val signSecret: Key,
    private val roleKey: String,
    private val prefix: String
) : AuthTokenManager {

    private val logger = KotlinLogging.logger {}

    override fun validate(token: AuthToken) {
        logger.info(tokenStringWithoutPrefix(token))

        try {
            Jwts.parserBuilder()
                .setSigningKey(signSecret)
                .build()
                .parseClaimsJws(tokenStringWithoutPrefix(token))

        } catch (e: RuntimeException) {
            Oh.authenticationError(e.message!!)
        }
    }

    override fun extractPublicId(token: AuthToken): PublicId {

        return PublicId(Jwts.parserBuilder()
                .setSigningKey(signSecret)
                .build()
                .parseClaimsJws(tokenStringWithoutPrefix(token))
                .body.subject)
    }

    override fun extractRoles(token: AuthToken): List<Role> {

        val roles =Jwts.parserBuilder()
            .setSigningKey(signSecret)
            .build()
            .parseClaimsJws(tokenStringWithoutPrefix(token))
            .body.get(roleKey, List::class.java) as List<String>

        return roles.stream().map { role -> Role.valueOf(role.toString()) }.toList()
    }

    private fun tokenStringWithoutPrefix(token: AuthToken)
        = token.tokenString.removePrefix(prefix)
}
