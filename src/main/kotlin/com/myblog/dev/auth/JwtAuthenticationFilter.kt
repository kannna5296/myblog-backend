package com.myblog.dev.auth

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor

@Component
class JwtAuthenticationFilter(
    private val jwtUtil: JwtUtil,
    private val userDetailsService: UserDetailsService
) : HandlerInterceptor {

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {

        val authorizationHeader = request.getHeader("Authorization")
        if (authorizationHeader.isNullOrBlank()) {
            println("Authorizationヘッダなし")
            return false
        }
        val jwt = if (authorizationHeader.startsWith("Bearer ")) {
            authorizationHeader.substring(7)
        } else {
            println("Authorizationヘッダの形式が違う")
            return false
        }
        val username = jwtUtil.extractUsername(jwt)

        // usernameを元にDBからユーザ情報取得
        val userDetails = userDetailsService.loadUserByUsername(username)

        if (!jwtUtil.validateToken(jwt, userDetails)) {
            println("トークンが有効ではない")
            return false
        }

        return true
    }

}
