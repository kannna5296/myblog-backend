package com.myblog.dev.controller

import com.myblog.dev.service.PostEditService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/post")
@Tag(name = "POST", description = "投稿管理API")
class PostEditController(
    private val service: PostEditService,
) {

    @Operation(summary = "ポスト更新", description = "投稿内容を更新する")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "OK"),
            ApiResponse(responseCode = "400", description = "Bad Request"),
        ]
    )
    @PutMapping("/{id}")
    fun edit(@PathVariable id: String, @RequestBody form: PostEditForm): ResponseEntity<Void> {
        service.execute(id, form)
        return ResponseEntity.ok().build()
    }
}

class PostEditForm(
    val title: String,
    val content: String,
    val userId: String,
)
