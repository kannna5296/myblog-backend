package com.myblog.dev.controller

import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/login_user")
class GetLoginUserController {

    @GetMapping
    fun post(): ResponseEntity<LoginUserResponse> {
        // SecurityContextから認証情報を取得
        val authentication = SecurityContextHolder.getContext().authentication
        return ResponseEntity.ok(LoginUserResponse(name = authentication.name))
    }
}

class LoginUserResponse(val name: String)
