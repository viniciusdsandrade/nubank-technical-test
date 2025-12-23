package com.restful.nubank.technical.test.controller

import com.restful.nubank.technical.test.dto.ClienteRequestDTO
import com.restful.nubank.technical.test.dto.ContatoResponseDTO
import com.restful.nubank.technical.test.dto.toResponse
import com.restful.nubank.technical.test.service.ClienteService
import com.restful.nubank.technical.test.service.ContatoService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.ok
import org.springframework.http.ResponseEntity.status
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import kotlin.collections.map

@RestController
@RequestMapping("/clientes")
class ClienteController(
    private val clienteService: ClienteService,
    private val contatoService: ContatoService
) {

    @PostMapping
    fun criarCliente(@Valid @RequestBody req: ClienteRequestDTO): ResponseEntity<Any> {
        val cliente = clienteService.criar(req.nome, req.email)
        return status(CREATED).body(cliente.toResponse())
    }

    @GetMapping
    fun listarClientes(): ResponseEntity<Any> {
        val clientes = clienteService.listarTodosComContatos().map { it.toResponse() }
        return ok(clientes)
    }

    @GetMapping("/{id}/contatos")
    fun listarContatosDoCliente(@PathVariable id: Long): ResponseEntity<List<ContatoResponseDTO>> {
        val contatos = contatoService.listarPorCliente(id).map { it.toResponse() }
        return ok(contatos)
    }
}