package com.freshtuna.sharp.security.config

import com.freshtuna.sharp.security.token.JWTAuthTokenManager
import com.freshtuna.sharp.security.token.const.AuthTokenProperties
import com.freshtuna.sharp.security.token.spec.AuthTokenManager
import io.jsonwebtoken.security.Keys
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.Base64

@Configuration
class AuthTokenManagerConfig(
    private val authTokenProperties: AuthTokenProperties
) {

    @Bean
    fun authTokenManager(): AuthTokenManager {

        return JWTAuthTokenManager(
            Keys.hmacShaKeyFor(authTokenProperties.secret.toByteArray()),
            authTokenProperties.roleKey,
            authTokenProperties.prefix
        )
    }
}
