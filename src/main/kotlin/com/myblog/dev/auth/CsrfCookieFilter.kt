package com.myblog.dev.auth

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import org.springframework.security.web.csrf.CookieCsrfTokenRepository
import org.springframework.security.web.csrf.CsrfToken
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler
import org.springframework.security.web.csrf.XorCsrfTokenRequestAttributeHandler
import org.springframework.util.StringUtils
import org.springframework.web.filter.OncePerRequestFilter
import java.time.Duration
import java.util.function.Supplier

fun HttpSecurity.myCsrfConfig(): HttpSecurity =
    this.csrf {
        it
            .ignoringRequestMatchers("/auth/login")
            .csrfTokenRepository(cookieCsrfTokenRepository())
            .csrfTokenRequestHandler(MyHandler())
        // TODO 例外処理
    }.addFilterAfter(CsrfCookieFilter(), BasicAuthenticationFilter::class.java)

private fun cookieCsrfTokenRepository(): CookieCsrfTokenRepository {
    val repo = CookieCsrfTokenRepository.withHttpOnlyFalse()

    repo.setCookieCustomizer { customizer ->
        customizer
            .path("/") // [/api]とかにするとWebページ「Hoge.com/api/**」でしかcookie扱えない
            .maxAge(Duration.ofHours(1))
    }
    return repo
}

private class MyHandler : CsrfTokenRequestAttributeHandler() {
    private val de = XorCsrfTokenRequestAttributeHandler()
    override fun handle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        deferredCsrfToken: Supplier<CsrfToken>
    ) {
        de.handle(request, response, deferredCsrfToken)
    }

    override fun resolveCsrfTokenValue(request: HttpServletRequest, csrfToken: CsrfToken): String? {
        return if (StringUtils.hasText(request.getHeader(csrfToken.headerName))) {
            super.resolveCsrfTokenValue(request, csrfToken)
        } else {
            runCatching { de.resolveCsrfTokenValue(request, csrfToken) }.getOrNull()
        }
    }
}

private class CsrfCookieFilter : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val csrfToken = request.getAttribute("_csrf") as CsrfToken
        // Render the token value to a cookie by causing the deferred token to be loaded
        csrfToken.token

        filterChain.doFilter(request, response)
    }
}
