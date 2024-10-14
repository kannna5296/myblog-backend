package com.myblog.dev.controller

import com.myblog.dev.service.PostDetailService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/post")
class PostDetailController(
    private val service: PostDetailService,
) {

    @GetMapping("/{id}")
    fun detail(@PathVariable id: String): ResponseEntity<PostDetailResponse> {
        val result = service.execute(postId = id.toLong())
        return ResponseEntity.ok(result)
    }
}

class PostDetailResponse(
    val postId: String,
    val title: String,
    val content: String,
    val commentList: List<CommentDetailDesponse>,
)

class CommentDetailDesponse(
    val content: String,
    val userId: String,
)
