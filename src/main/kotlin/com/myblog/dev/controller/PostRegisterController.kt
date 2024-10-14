package com.myblog.dev.controller

import com.myblog.dev.service.PostRegisterService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/post")
class PostRegisterController(
    private val service: PostRegisterService,
) {

    @PostMapping
    fun post(@RequestBody form: PostRegisterForm): ResponseEntity<PostRegisterResponse> {
        val result = service.execute(form)
        return ResponseEntity.ok(result)
    }
}

class PostRegisterForm(
    val title: String,
    val content: String,
    val userId: String,
)

class PostRegisterResponse(
    val postId: String,
)
