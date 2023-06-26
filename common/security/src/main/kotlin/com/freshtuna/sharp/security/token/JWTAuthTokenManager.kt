package com.freshtuna.sharp.security.token

import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.id.SharpPublicID
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
    private val prefix: String
) : AuthTokenManager {

    private val logger = KotlinLogging.logger {}

    override fun validate(token: AuthToken) {

        try {
            Jwts.parserBuilder()
                .setSigningKey(signSecret)
                .build()
                .parseClaimsJws(tokenStringWithoutPrefix(token))

        } catch (e: RuntimeException) {
            Oh.authenticationError(e.message!!)
        }
    }

    override fun extractPublicId(token: AuthToken): SharpPublicID {

        return SharpPublicID(Jwts.parserBuilder()
                .setSigningKey(signSecret)
                .build()
                .parseClaimsJws(tokenStringWithoutPrefix(token))
                .body.subject)
    }

    private fun tokenStringWithoutPrefix(token: AuthToken)
        = token.tokenString.removePrefix(prefix)
}
