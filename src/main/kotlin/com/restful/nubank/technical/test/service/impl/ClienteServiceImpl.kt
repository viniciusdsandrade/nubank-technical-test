package com.restful.nubank.technical.test.service.impl

import com.restful.nubank.technical.test.entity.Cliente
import com.restful.nubank.technical.test.exception.BusinessRuleException
import com.restful.nubank.technical.test.exception.ResourceNotFoundException
import com.restful.nubank.technical.test.repository.ClienteRepository
import com.restful.nubank.technical.test.service.ClienteService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ClienteServiceImpl (
    private val clienteRepository: ClienteRepository
): ClienteService {

    @Transactional
    override fun criar(nome: String, email: String): Cliente {
        if (clienteRepository.existsByEmailIgnoreCase(email)) {
            throw BusinessRuleException("Já existe cliente cadastrado com este email.")
        }
        return clienteRepository.save(Cliente(nome = nome, email = email))
    }

    @Transactional(readOnly = true)
    override fun listarTodosComContatos(): List<Cliente> =
        clienteRepository.findAllWithContatos()

    @Transactional(readOnly = true)
    override fun buscarPorId(id: Long): Cliente =
        clienteRepository.findById(id).orElseThrow {
            ResourceNotFoundException("Cliente $id não encontrado.")
        }
}