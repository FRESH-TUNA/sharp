package com.freshtuna.sharp.config

import com.freshtuna.sharp.member.constant.Role
import com.freshtuna.sharp.security.filter.AuthTokenFilter
import io.github.oshai.KotlinLogging
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy

import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter


@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val authTokenFilter: AuthTokenFilter
) {

    private val logger = KotlinLogging.logger {}


    /** Spring Security Filter Chain 관련 설정  */
    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {

        return http
            .cors()
            .and() /* session 사용하지 않음 (STATELESS) */
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .formLogin().disable()
            .httpBasic().disable()
            .csrf().disable()
            .exceptionHandling() /* 유효한 자격증명을 제공하지 않는 경우 */
            .and() /* URI 기반 인증/인가 설정 */
            .authorizeHttpRequests()
            .anyRequest().hasAnyAuthority(Role.ADMIN.name, Role.SELLER.name)
            .and()
            .addFilterBefore(authTokenFilter, UsernamePasswordAuthenticationFilter::class.java)
            .build()
    }
}
