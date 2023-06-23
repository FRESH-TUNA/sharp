package com.freshtuna.sharp.config.env

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "sharp.cors")
class CORSProperties(
    var allowedOrigins: String = "",
    var allowedMethods: String = "",
    var allowedHeaders: String = "",
    var exposedHeaders: String = "",
    var maxAge: Long = 0L
)
