package com.freshtuna.sharp.security.token.const

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "sharp.auth-token")
class AuthTokenProperties(
    var secret: String = "",
    var refreshTokenSecret: String = "",
    var accessTokenExpiredMileSeconds: String = "",
    var refreshTokenExpiredMileSeconds: String = "",
    var roleKey: String = "",
    var prefix: String = ""
)
