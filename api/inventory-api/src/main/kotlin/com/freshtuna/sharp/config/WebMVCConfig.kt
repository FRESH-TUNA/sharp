package com.freshtuna.sharp.config

import com.freshtuna.sharp.config.env.CORSProperties
import com.freshtuna.sharp.config.intercepter.SellerAuthInterceptor
import com.freshtuna.sharp.config.resolver.SharpIDResolver
import io.github.oshai.KotlinLogging
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


@Configuration
class WebMVCConfig(
    private val sellerAuthInterceptor: SellerAuthInterceptor,
    private val sharpIDResolver: SharpIDResolver,
    private val corsProperties: CORSProperties
) : WebMvcConfigurer {

    private val logger = KotlinLogging.logger {}

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(sellerAuthInterceptor)
            .addPathPatterns("/**")
    }

    override fun addArgumentResolvers(argumentResolvers: MutableList<HandlerMethodArgumentResolver?>) {
        argumentResolvers.add(sharpIDResolver)
    }

    /** CORS 설정  */
    @Bean
    fun corsConfigurationSource(): UrlBasedCorsConfigurationSource {
        val corsConfigSource = UrlBasedCorsConfigurationSource()
        val corsConfig = CorsConfiguration()
        corsConfig.allowedHeaders = corsProperties.allowedHeaders.split(",")
        corsConfig.allowedMethods = corsProperties.allowedMethods.split(",")
        corsConfig.allowedOrigins = corsProperties.allowedOrigins.split(",")
        corsConfig.exposedHeaders = corsProperties.exposedHeaders.split(",")
        corsConfig.maxAge = corsProperties.maxAge
        corsConfig.allowCredentials = true
        corsConfigSource.registerCorsConfiguration("/**", corsConfig)
        return corsConfigSource
    }
}
