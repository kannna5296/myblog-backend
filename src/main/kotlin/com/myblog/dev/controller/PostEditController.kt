package com.myblog.dev.controller

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/post")
class PostEditController(
    private val service: PostEditService,
) {

    @PutMapping("/{id}")
    fun edit(@PathVariable id: String, @RequestBody form: PostEditForm): ResponseEntity<PostEditResponse> {
        val postId = service.execute()
        val result = PostEditResponse(postId)
        return ResponseEntity.ok(result)
    }
}

class PostEditForm(
    val title: String,
    val content: String,
    val userId: String,
)

class PostEditResponse(
    val postId: String,
)

@Service
class PostEditService {

    fun execute(): String {
        // TODO DBに登録
        return "1"
    }
}
