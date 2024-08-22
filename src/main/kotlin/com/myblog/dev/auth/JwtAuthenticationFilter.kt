package com.myblog.dev.auth

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthenticationFilter(
    private val jwtUtil: JwtUtil,
    private val userDetailsService: UserDetailsService
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        // スキップするパスの場合、フィルターを通さずに次のフィルターへ進む
        if (AuthLessPath.AUTHLESS_PATH_MATCHERS.matches(request)) {
            filterChain.doFilter(request, response)
            return
        }

        val authorizationHeader = request.getHeader("Authorization")
        if (authorizationHeader.isNullOrBlank()) {
            println("Authorizationヘッダなし")
            throw Exception()
        }
        val jwt = if (authorizationHeader.startsWith("Bearer ")) {
            authorizationHeader.substring(7)
        } else {
            println("Authorizationヘッダの形式が違う")
            throw Exception()
        }
        val username = jwtUtil.extractUsername(jwt)

        // usernameを元にDBからユーザ情報取得
        val userDetails = userDetailsService.loadUserByUsername(username)

        if (!jwtUtil.validateToken(jwt, userDetails)) {
            println("トークンが有効ではない")
            throw Exception()
        }

        val authentication = UsernamePasswordAuthenticationToken(
            userDetails, null, userDetails.authorities
        )
        SecurityContextHolder.getContext().authentication = authentication

        filterChain.doFilter(request, response)
    }
}
