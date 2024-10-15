plugins {
    id("org.springframework.boot") version "3.3.2"
    id("io.spring.dependency-management") version "1.1.6"
    kotlin("jvm") version "1.9.24"
    kotlin("plugin.spring") version "1.9.24"
    id("nu.studer.jooq") version "9.0"
    id("org.jlleitschuh.gradle.ktlint") version "10.3.0"
}

group = "com.myblog"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-security:3.3.0")
    implementation("io.jsonwebtoken:jjwt-api:0.11.5")
    implementation("io.jsonwebtoken:jjwt-impl:0.11.5")
    implementation("io.jsonwebtoken:jjwt-jackson:0.11.5") // JSON processing (Jackson) のため

// 	implementation("org.springframework.boot:spring-boot-starter-data-jooq")
    runtimeOnly("com.mysql:mysql-connector-j")
// 	implementation("org.jooq:jooq")

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-jdbc")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testImplementation("org.springframework.security:spring-security-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    // jooq
    jooqGenerator("com.mysql:mysql-connector-j")
    jooqGenerator("jakarta.xml.bind:jakarta.xml.bind-api:4.0.0")

    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.6.0")

}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

jooq {
    configurations {
        create("main") {
            jooqConfiguration.apply {
                jdbc.apply {
                    url = "jdbc:mysql://localhost:3306/myblog?enabledTLSProtocols=TLSv1.2"
                    user = "root"
                    password = "Password123"
                }
                generator.apply {
                    name = "org.jooq.codegen.KotlinGenerator"
                    database.apply {
                        name = "org.jooq.meta.mysql.MySQLDatabase"
                        inputSchema = "myblog"
                        excludes = "flyway_schema_history"
                    }
                    generate.apply {
                        isDeprecated = false
                        isTables = true
                    }
                    target.apply {
                        packageName = "com.example.ktknowledgeTodo.infra.jooq"
                        directory = "$buildDir/generated/source/jooq/main"
                    }
                }
            }
        }
    }
}
