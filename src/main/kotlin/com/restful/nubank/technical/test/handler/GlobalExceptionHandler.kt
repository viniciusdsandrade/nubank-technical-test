package com.restful.nubank.technical.test.handler

import com.restful.nubank.technical.test.exception.BusinessRuleException
import com.restful.nubank.technical.test.exception.ResourceNotFoundException
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.http.HttpStatus.CONFLICT
import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException::class)
    fun handleNotFound(ex: ResourceNotFoundException, req: HttpServletRequest): ResponseEntity<ApiErrorResponse> =
        ResponseEntity
            .status(NOT_FOUND)
            .body(
                ApiErrorResponse(
                    status = NOT_FOUND.value(),
                    error = NOT_FOUND.reasonPhrase,
                    message = ex.message,
                    path = req.requestURI
                )
            )

    @ExceptionHandler(BusinessRuleException::class)
    fun handleBusiness(ex: BusinessRuleException, req: HttpServletRequest): ResponseEntity<ApiErrorResponse> =
        ResponseEntity
            .status(CONFLICT)
            .body(
                ApiErrorResponse(
                    status = CONFLICT.value(),
                    error = CONFLICT.reasonPhrase,
                    message = ex.message,
                    path = req.requestURI
                )
            )

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidation(
        ex: MethodArgumentNotValidException,
        req: HttpServletRequest
    ): ResponseEntity<ApiErrorResponse> {
        val fieldErrors = ex.bindingResult
            .allErrors
            .filterIsInstance<FieldError>()
            .map { FieldErrorItem(field = it.field, message = it.defaultMessage) }

        return ResponseEntity
            .status(BAD_REQUEST)
            .body(
                ApiErrorResponse(
                    status = BAD_REQUEST.value(),
                    error = BAD_REQUEST.reasonPhrase,
                    message = "Validação falhou",
                    path = req.requestURI,
                    fieldErrors = fieldErrors
                )
            )
    }

    @ExceptionHandler(Exception::class)
    fun handleGeneric(ex: Exception, req: HttpServletRequest): ResponseEntity<ApiErrorResponse> =
        ResponseEntity
            .status(INTERNAL_SERVER_ERROR)
            .body(
                ApiErrorResponse(
                    status = INTERNAL_SERVER_ERROR.value(),
                    error = INTERNAL_SERVER_ERROR.reasonPhrase,
                    message = ex.message,
                    path = req.requestURI
                )
            )
}