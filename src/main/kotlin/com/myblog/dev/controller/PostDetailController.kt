package com.myblog.dev.controller

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/post")
class PostDetailController(
    private val service: PostDetailService,
) {

    @GetMapping("/{id}")
    fun detail(@PathVariable id: String) : ResponseEntity<PostDetailResponse>  {
        val result = service.execute()
        return ResponseEntity.ok(result)
    }
}


class PostDetailResponse(
    val postId: String,
    val title: String,
    val content: String,
)

@Service
class PostDetailService {

    fun execute() : PostDetailResponse {
        //TODO DBから取得
        return PostDetailResponse(
            postId = "1",
            title = "hoge",
            content = "content"
        )
    }
}
