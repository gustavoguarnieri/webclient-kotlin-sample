package com.example.webclientconsumerkotlinsample.config

import com.example.webclientconsumerkotlinsample.model.response.BaseExceptionResponse
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.ServletWebRequest
import org.springframework.web.context.request.WebRequest
import org.springframework.web.reactive.function.client.WebClientResponseException
import java.time.LocalDateTime

@ControllerAdvice
class ExceptionHandlerConfig {

    @ExceptionHandler(WebClientResponseException.NotFound::class)
    private fun handleWebClientResponseNotFoundException(
        ex: WebClientResponseException,
        request: WebRequest
    ): ResponseEntity<Any?> {
        val baseExceptionResponse = BaseExceptionResponse(
            LocalDateTime.now(),
            HttpStatus.NOT_FOUND.value(),
            HttpStatus.NOT_FOUND.reasonPhrase,
            ex.localizedMessage,
            (request as ServletWebRequest).request.requestURI
        )
        return ResponseEntity(baseExceptionResponse, HttpHeaders(), HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(WebClientResponseException::class)
    private fun handleWebClientResponseException(
        ex: WebClientResponseException,
        request: WebRequest
    ): ResponseEntity<String?> {
        return ResponseEntity.status(ex.rawStatusCode).body(ex.responseBodyAsString)
    }
}