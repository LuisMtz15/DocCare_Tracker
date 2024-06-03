package com.example.doccare_tracker.model.Registros.Usuario

data class RegistrarDetalleUsuario(
    val altura: Float,
    val circunferencia_abdominal: Float,
    val clave_unica: String,
    val peso: Float,
    val sexo: String,
    val usuario_id: Int
)