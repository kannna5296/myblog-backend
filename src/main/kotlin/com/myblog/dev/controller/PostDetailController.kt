package com.myblog.dev.controller

import com.myblog.dev.service.PostDetailService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/post")
@Tag(name = "POST", description = "投稿管理API")
class PostDetailController(
    private val service: PostDetailService,
) {

    @Operation(summary = "ポスト詳細", description = "投稿内容の詳細を取得する")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "OK"),
            ApiResponse(responseCode = "400", description = "Bad Request"),
        ]
    )
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
