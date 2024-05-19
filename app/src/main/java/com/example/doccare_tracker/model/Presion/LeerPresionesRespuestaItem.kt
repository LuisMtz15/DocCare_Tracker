package com.example.doccare_tracker.model.Presion

data class LeerPresionesRespuestaItem(
    val diastolica: Int,
    val emocion_presion: String,
    val fecha: String,
    val hora: String,
    val id: Int,
    val sistolica: Int
)