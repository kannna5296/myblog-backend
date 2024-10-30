package com.myblog.dev.auth

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import org.springframework.web.servlet.HandlerExceptionResolver

@Component
class JwtAuthenticationFilter(
    private val jwtUtil: JwtUtil,
    private val userDetailsService: UserDetailsService,
    @Qualifier("handlerExceptionResolver") // ErrorAttributs?とNoUniqueBeanDefinitionExceptionで競合するので一旦命名
    private val myHandlerExceptionHandler: HandlerExceptionResolver,
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            // スキップするパスの場合、フィルターを通さずに次のフィルターへ進む
            if (AuthLessPath.AUTHLESS_PATH_MATCHERS.matches(request)) {
                filterChain.doFilter(request, response)
                return
            }

            val authorizationHeader = request.getHeader("Authorization")
            if (authorizationHeader.isNullOrBlank()) {
                println("Authorizationヘッダなし")
                throw UnAuthorizedException()
            }
            val jwt = if (authorizationHeader.startsWith("Bearer ")) {
                authorizationHeader.substring(7)
            } else {
                println("Authorizationヘッダの形式が違う")
                throw UnAuthorizedException()
            }
            val username = jwtUtil.extractUsername(jwt)

            // usernameを元にDBからユーザ情報取得
            val userDetails = userDetailsService.loadUserByUsername(username)

            if (!jwtUtil.validateToken(jwt, userDetails)) {
                println("トークンが有効ではない")
                throw UnAuthorizedException()
            }

            // これないと以下のログが出て403になる
            // o.s.s.w.a.AnonymousAuthenticationFilter  : Set SecurityContextHolder to anonymous SecurityContext
            // o.s.s.w.a.Http403ForbiddenEntryPoint     : Pre-authenticated entry point called. Rejecting access
            SecurityContextHolder.getContext().authentication = UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.authorities
            )

            filterChain.doFilter(request, response)
        } catch (e: UnAuthorizedException) {
            myHandlerExceptionHandler.resolveException(request, response, null, e)
        }
    }
}
