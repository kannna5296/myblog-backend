package com.myblog.dev.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController {

    @PostMapping("/login")
    fun login(): ResponseEntity<String> {
        println("login")
        return ResponseEntity.ok("login requested")
    }

    @PostMapping("/register")
    fun register(): ResponseEntity<String> {
        println("register")
        return ResponseEntity.ok("register requested")
    }
}