package com.example.accesibilidadauditiva

data class Usuario(val email: String, val password: String)

var usuariosRegistrados: MutableList<Usuario> = mutableListOf(
    Usuario("user1@example.com", "password1"),
    Usuario("user2@example.com", "password2"),
    Usuario("user3@example.com", "password3"),
    Usuario("user4@example.com", "password4"),
    Usuario("user5@example.com", "password5")
)
