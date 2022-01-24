package com.example.webclientimageskotlinsample

import com.example.webclientimageskotlinsample.constants.Images
import com.example.webclientimageskotlinsample.repositories.ImageRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ImageApplication(
    private val repository: ImageRepository
) : CommandLineRunner {

    override fun run(vararg args: String?) {
        repository.deleteAll()
        Images.imageCatalog.forEach(repository::save)
    }
}

fun main(args: Array<String>) {
    runApplication<ImageApplication>(*args)
}
