package com.restful.nubank.technical.test.entity

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import java.time.Instant

@Entity
@Table(name = "clientes")
class Cliente(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    var nome: String,

    @Column(nullable = false, unique = true)
    var email: String,

    @OneToMany(
        mappedBy = "cliente",
        cascade = [CascadeType.ALL],
        orphanRemoval = true,
        fetch = FetchType.LAZY
    )
    val contatos: MutableList<Contato> = mutableListOf(),

    @Column(nullable = false, updatable = false)
    val criadoEm: Instant = Instant.now()
)