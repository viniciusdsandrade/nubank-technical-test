package com.restful.nubank.technical.test.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

data class ClienteRequestDTO(
    @field:NotBlank(message = "nome é obrigatório")
    val nome: String,

    @field:NotBlank(message = "email é obrigatório")
    @field:Email(message = "email inválido")
    val email: String
)
