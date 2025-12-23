package com.restful.nubank.technical.test.service

import com.restful.nubank.technical.test.entity.Cliente

interface ClienteService {
    @Transactional
    fun criar(nome: String, email: String): Cliente
    @Transactional(readOnly = true)
    fun listarTodosComContatos(): List<Cliente>
    @Transactional(readOnly = true)
    fun buscarPorId(id: Long): Cliente
}