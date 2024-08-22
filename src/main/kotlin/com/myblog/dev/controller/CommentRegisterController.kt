package com.myblog.dev.controller

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/post")
class CommentRegisterController(
    private val service: CommentRegisterService,
) {

    @PostMapping("/{id}/comment")
    fun post(@RequestBody form: CommentRegisterForm): ResponseEntity<CommentRegisterResponse> {
        val commentId = service.execute()
        val result = CommentRegisterResponse(commentId)
        return ResponseEntity.ok(result)
    }
}

class CommentRegisterForm(
    val content: String,
    val userId: String,
)

class CommentRegisterResponse(
    val commentId: String,
)

@Service
class CommentRegisterService {

    fun execute(): String {
        // TODO DBに登録
        return "1"
    }
}
