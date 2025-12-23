package com.restful.nubank.technical.test.dto

class ClienteResponseDTO(
    val id: Long,
    val nome: String,
    val email: String,
    val contatos: List<ContatoResponseDTO>
)