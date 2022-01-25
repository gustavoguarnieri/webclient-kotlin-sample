import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val springBootVersion by extra("2.5.9")
val springfoxVersion = "3.0.0"

plugins {
	id("org.springframework.boot") version "2.5.9"
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
	implementation("org.springframework.boot:spring-boot-starter-web:$springBootVersion")
	implementation("org.springframework.boot:spring-boot-starter-webflux:$springBootVersion")
	implementation("org.springframework.boot:spring-boot-starter-data-mongodb:$springBootVersion")
	implementation("org.modelmapper:modelmapper:3.0.0")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("io.github.microutils:kotlin-logging:2.1.21")
	implementation("io.springfox:springfox-swagger2:${springfoxVersion}")
	implementation("io.springfox:springfox-swagger-ui:${springfoxVersion}")
	implementation("io.springfox:springfox-boot-starter:${springfoxVersion}")
	developmentOnly("org.springframework.boot:spring-boot-devtools:$springBootVersion")
	testImplementation("org.springframework.boot:spring-boot-starter-test:$springBootVersion")
	testImplementation("io.projectreactor:reactor-test")
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
