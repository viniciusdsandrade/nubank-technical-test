package com.restful.nubank.technical.test.repository

import com.restful.nubank.technical.test.entity.Contato
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ContatoRepository : JpaRepository<Contato, Long> {
    fun findAllByClienteId(clienteId: Long): List<Contato>
}