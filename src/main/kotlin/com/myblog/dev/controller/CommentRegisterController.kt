package com.myblog.dev.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/post")
@Tag(name = "POST", description = "投稿管理API")
class CommentRegisterController(
    private val service: CommentRegisterService,
) {

    @Operation(summary = "ポストに対するコメント投稿", description = "ポストに対するコメントを投稿する")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "OK"),
            ApiResponse(responseCode = "400", description = "Bad Request"),
        ]
    )
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
