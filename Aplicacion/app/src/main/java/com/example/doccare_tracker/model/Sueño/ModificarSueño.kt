package com.example.doccare_tracker.model.Sueño

data class ModificarSueño(
    val calidad_sueño: String,
    val horas: Int,
    val id_sueño: Int,
    val pastilla: String,
    val horaDormir: String,
    val horaDespertar: String
)