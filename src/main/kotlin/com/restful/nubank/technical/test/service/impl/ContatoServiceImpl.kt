package com.restful.nubank.technical.test.service.impl

import com.restful.nubank.technical.test.entity.Contato
import com.restful.nubank.technical.test.exception.ResourceNotFoundException
import com.restful.nubank.technical.test.repository.ClienteRepository
import com.restful.nubank.technical.test.repository.ContatoRepository
import com.restful.nubank.technical.test.service.ContatoService
import org.springframework.transaction.annotation.Transactional
import org.springframework.stereotype.Service

@Service
class ContatoServiceImpl(
    private val clienteRepository: ClienteRepository,
    private val contatoRepository: ContatoRepository
) : ContatoService {

    @Transactional
    override fun criar(clienteId: Long, tipo: String, valor: String): Contato {
        val cliente = clienteRepository.findById(clienteId).orElseThrow {
            ResourceNotFoundException("Cliente $clienteId não encontrado.")
        }

        val contato = Contato(
            tipo = tipo,
            valor = valor,
            cliente = cliente
        )
        return contatoRepository.save(contato)
    }

    @Transactional(readOnly = true)
    override fun listarPorCliente(clienteId: Long): List<Contato> {
        if (!clienteRepository.existsById(clienteId)) {
            throw ResourceNotFoundException("Cliente $clienteId não encontrado.")
        }
        return contatoRepository.findAllByClienteId(clienteId)
    }
}
