package com.myblog.dev.controller

import com.myblog.dev.service.PostRegisterService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/post")
@Tag(name = "POST", description = "投稿管理API")
class PostRegisterController(
    private val service: PostRegisterService,
) {

    @Operation(summary = "ポスト投稿", description = "投稿する")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "OK"),
            ApiResponse(responseCode = "400", description = "Bad Request"),
        ]
    )
    @PostMapping
    fun post(@RequestBody form: PostRegisterForm): ResponseEntity<PostRegisterResponse> {
        val result = service.execute(form)
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
