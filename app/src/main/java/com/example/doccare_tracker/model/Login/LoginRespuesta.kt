package com.example.doccare_tracker.model.Login

data class LoginRespuesta(
    val id: Int,
    val role: String,
    val status: String,
    val token: String,
    val clave: String,
    val nombre: String,
    val apellidos: String
)