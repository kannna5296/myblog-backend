package com.myblog.dev.auth

import com.myblog.dev.auth.AuthLessPath.Companion.AUTHLESS_PATH
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebMvcConfig(
    private val filter: JwtAuthenticationFilter,
) : WebMvcConfigurer {

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(filter).excludePathPatterns(AUTHLESS_PATH.toList())
    }
}