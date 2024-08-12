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

    @Bean
    fun corsConfigurer(): WebMvcConfigurer {
        return object : WebMvcConfigurer {
            override fun addCorsMappings(registry: CorsRegistry) {
                registry.addMapping("/**")
                    .allowedOriginPatterns("http://127.0.0.1:5173", "http://localhost:5173") //TODO 環境ごとにいじれるようにする
                    .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                    .allowedHeaders("*")
                    .allowCredentials(true)
            }
        }
    }

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(filter).excludePathPatterns(AUTHLESS_PATH.toList())
    }
}