package com.myblog.dev.controller

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
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
        val postId = service.execute()
        val result = PostRegisterResponse(postId)
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

@Service
class PostRegisterService {

    fun execute(): String {
        // TODO DBに登録
        return "1"
    }
}
