package com.example.doccare_tracker.model.Registros

data class RegistrarUsuario(
    val apellido_materno: String,
    val apellido_paterno: String,
    val email: String,
    val fecha_nacimiento: String,
    val nombre: String,
    val password: String,
    val rol: String
)