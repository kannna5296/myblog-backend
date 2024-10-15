package com.myblog.dev.controller

import com.myblog.dev.service.PostIndexService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/post")
@Tag(name = "POST", description = "投稿管理API")
class PostIndexController(
    private val service: PostIndexService,
) {

    @Operation(summary = "ポスト一覧", description = "投稿内容の一覧を取得する")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "OK"),
            ApiResponse(responseCode = "400", description = "Bad Request"),
        ]
    )
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
