package com.restful.nubank.technical.test.repository

import com.restful.nubank.technical.test.entity.Cliente
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface ClienteRepository : JpaRepository<Cliente, Long> {

    fun existsByEmailIgnoreCase(email: String): Boolean

    @EntityGraph(attributePaths = ["contatos"])
    @Query("select c from Cliente c")
    fun findAllWithContatos(): List<Cliente>
}