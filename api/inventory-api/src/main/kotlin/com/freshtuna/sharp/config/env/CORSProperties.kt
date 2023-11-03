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
) {

    fun allowedOriginArray(): Array<String> {
        return allowedOrigins.split(",").toTypedArray()
    }

    fun allowedMethodArray(): Array<String> {
        return allowedMethods.split(",").toTypedArray()
    }

    fun allowedHeaderArray(): Array<String> {
        return allowedHeaders.split(",").toTypedArray()
    }

    fun exposedHeaderArray(): Array<String> {
        return exposedHeaders.split(",").toTypedArray()
    }
}
