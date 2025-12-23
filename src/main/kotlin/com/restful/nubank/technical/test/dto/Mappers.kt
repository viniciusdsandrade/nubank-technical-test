package com.restful.nubank.technical.test.dto

import com.restful.nubank.technical.test.entity.Cliente
import com.restful.nubank.technical.test.entity.Contato


fun Cliente.toResponse(): ClienteResponseDTO =
    ClienteResponseDTO(
        id = this.id,
        nome = this.nome,
        email = this.email,
        contatos = this.contatos.map { it.toResponse() }
    )

fun Contato.toResponse(): ContatoResponseDTO =
    ContatoResponseDTO(
        id = this.id,
        tipo = this.tipo,
        valor = this.valor
    )