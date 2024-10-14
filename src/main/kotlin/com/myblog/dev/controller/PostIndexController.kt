package com.myblog.dev.controller

import com.myblog.dev.service.PostIndexService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/post")
class PostIndexController(
    private val service: PostIndexService,
) {

    @GetMapping
    fun index(): ResponseEntity<List<PostIndexResponse>> {
        val result = service.execute(userId = 1) // TODO 今後ログイン中のユーザの情報を取れるようにする
        return ResponseEntity.ok(result)
    }
}

class PostIndexResponse(
    val postId: String,
    val title: String,
)
