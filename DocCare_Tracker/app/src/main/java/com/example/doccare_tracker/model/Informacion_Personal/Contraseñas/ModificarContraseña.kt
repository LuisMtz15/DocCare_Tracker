package com.example.doccare_tracker.model.Informacion_Personal.Contraseñas

data class ModificarContraseña(
    val Oldpassword: String,
    val password: String,
    val usuario_id: Int
)