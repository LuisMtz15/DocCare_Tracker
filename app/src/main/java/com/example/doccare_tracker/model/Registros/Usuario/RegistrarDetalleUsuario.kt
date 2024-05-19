package com.example.doccare_tracker.model.Registros.Usuario

data class RegistrarDetalleUsuario(
    val altura: Int,
    val circunferencia_abdominal: Int,
    val clave_unica: String,
    val peso: Int,
    val sexo: String,
    val usuario_id: Int
)