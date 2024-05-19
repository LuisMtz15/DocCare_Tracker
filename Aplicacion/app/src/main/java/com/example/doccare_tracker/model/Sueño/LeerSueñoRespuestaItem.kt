package com.example.doccare_tracker.model.Sueño

data class LeerSueñoRespuestaItem(
    val calidad_sueño: String,
    val fecha: String,
    val horas: Int,
    val id: Int,
    val pastilla_sueño: String,
    val horaDormir: String,
    val horaDespertar: String
)