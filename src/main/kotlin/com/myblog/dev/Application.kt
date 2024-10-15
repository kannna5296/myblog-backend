package com.myblog.dev

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
// http://localhost:8080/api/swagger-ui/index.html →Swagger UI
// http://localhost:8080/api/v3/api-docs.yaml →Swagger yml
@OpenAPIDefinition(
    info = Info(
        title = "API Title",
        version = "0.0",
        description = "My API. yaml→ [http://localhost:8080/api/v3/api-docs.yaml](http://localhost:8080/api/v3/api-docs.yaml)",
    ),
)
class DevApplication

fun main(args: Array<String>) {
    runApplication<DevApplication>(*args)
}
