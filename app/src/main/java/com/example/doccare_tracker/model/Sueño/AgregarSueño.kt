package com.example.doccare_tracker.model.Sueño

data class AgregarSueño(
    val calidad_sueño: String,
    val fecha: String,
    val horas: Int,
    val pastilla: String,
    val usuario_id: Int,
    val horaDormir: String,
    val horaDespertar: String
)