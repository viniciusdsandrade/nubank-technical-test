package com.restful.nubank.technical.test.entity

import jakarta.persistence.CascadeType.ALL
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType.IDENTITY
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table


@Entity
@Table(name = "clientes")
class Cliente(

    @Id
    @GeneratedValue(strategy = IDENTITY)
    var id: Long = 0,

    @Column(nullable = false)
    var nome: String,

    @Column(nullable = false, unique = true)
    var email: String
) {
    @OneToMany(
        mappedBy = "cliente",
        cascade = [ALL],
        orphanRemoval = true
    )
    var contatos: MutableList<Contato> = mutableListOf()
}