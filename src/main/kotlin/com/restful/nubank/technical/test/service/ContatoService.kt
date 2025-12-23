package com.restful.nubank.technical.test.service

import com.restful.nubank.technical.test.entity.Contato
import org.springframework.transaction.annotation.Transactional

interface ContatoService {
    @Transactional(readOnly = true)
    fun listarPorCliente(clienteId: Long): List<Contato>
    @Transactional
    fun criar(clienteId: Long, tipo: String, valor: String): Contato
}