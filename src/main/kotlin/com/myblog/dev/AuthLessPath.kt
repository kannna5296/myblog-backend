package com.myblog.dev

import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import org.springframework.security.web.util.matcher.OrRequestMatcher

class AuthLessPath {

    companion object {
        val AUTHLESS_PATH = setOf("/auth/login", "/auth/register")
        val AUTHLESS_PATH_MATCHERS = OrRequestMatcher(AUTHLESS_PATH.map { AntPathRequestMatcher(it) })
    }
}