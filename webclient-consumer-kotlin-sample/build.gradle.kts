import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val springBootVersion by extra("2.6.2")
val kotlinxCoroutinesVersion by extra("1.6.0")

plugins {
    id("org.springframework.boot") version "2.6.2"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.6.10"
    kotlin("plugin.spring") version "1.6.10"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web:${springBootVersion}")
    implementation("org.springframework.boot:spring-boot-starter-webflux:${springBootVersion}")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${kotlinxCoroutinesVersion}")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("io.github.microutils:kotlin-logging:2.1.21")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:${kotlinxCoroutinesVersion}")
    developmentOnly("org.springframework.boot:spring-boot-devtools:$springBootVersion")
    testImplementation("org.springframework.boot:spring-boot-starter-test:$springBootVersion")
    testImplementation("io.projectreactor:reactor-test")
    testImplementation("io.mockk:mockk:1.12.2")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:${kotlinxCoroutinesVersion}")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.test {
    useJUnitPlatform()
}
