package com.example.doccare_tracker.model.Pastillas

data class LeerPastillasRespuestaItem(
    val dosis: String,
    val fecha: String,
    val id: Int,
    val nombre: String,
    val periodo_pastilla: Int,
    val tiempo_pastilla: String
)