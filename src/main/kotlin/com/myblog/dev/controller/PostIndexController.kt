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
class PostIndexController(
    private val service: PostIndexService,
) {

    @GetMapping
    fun index() : ResponseEntity<PostIndexResponse>  {
        val result = service.execute()
        return ResponseEntity.ok(result)
    }
}

class PostIndexResponse(
    val postId: String,
    val title: String,
)

@Service
class PostIndexService {

    fun execute() : PostIndexResponse {
        //TODO DBから取得
        return PostIndexResponse(
            postId = "1",
            title = "hoge",
        )
    }
}
