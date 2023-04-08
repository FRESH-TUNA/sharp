package com.freshtuna.sharp.config

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springdoc.core.models.GroupedOpenApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class SwaggerConfig {
    @Bean
    fun openAPI(): OpenAPI {

        // SecuritySecheme명
        val jwtSchemeName = "jwt 토큰"

        // API 요청헤더에 인증정보 포함
        val securityRequirement: SecurityRequirement = SecurityRequirement().addList(jwtSchemeName)

        // SecuritySchemes 등록
        val components = Components()
            .addSecuritySchemes(
                jwtSchemeName, SecurityScheme()
                    .name(jwtSchemeName)
                    .type(SecurityScheme.Type.HTTP) // HTTP 방식
                    .scheme("bearer")
                    .bearerFormat("JWT")
            )

        return OpenAPI()
            .components(components)
            .addSecurityItem(securityRequirement)
            .info(apiInfo())
    }

    @Bean
    fun inventoryGroup(): GroupedOpenApi? {
        return GroupedOpenApi.builder()
            .group("재고관리")
            .pathsToMatch("/inventory/**") // .packagesToScan("com.example.swagger") // package 필터 설정
            .build()
    }

    private fun apiInfo() = Info()
        .title("Sharp API")
        .description("Sharp API 명세서")
        .version("1.0.0")
}
