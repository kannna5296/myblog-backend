package com.myblog.dev.auth

import jakarta.servlet.http.HttpServletRequest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource

@Configuration
class CorsConfig : CorsConfigurationSource {

    @Bean
    override fun getCorsConfiguration(request: HttpServletRequest): CorsConfiguration {
        val corsConfiguration = CorsConfiguration()
        corsConfiguration.allowedOrigins = listOf("http://localhost:5173")
        corsConfiguration.allowedMethods = listOf("GET", "POST", "PUT", "DELETE", "OPTIONS")
        corsConfiguration.allowedHeaders = listOf("*")
        corsConfiguration.allowCredentials = true
        return corsConfiguration
    }
}
