package com.restful.nubank.technical.test.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Positive

data class ContatoRequestDTO(
    @field:Positive(message = "clienteId deve ser positivo")
    val clienteId: Long,

    @field:NotBlank(message = "tipo é obrigatório")
    val tipo: String,

    @field:NotBlank(message = "valor é obrigatório")
    val valor: String
)