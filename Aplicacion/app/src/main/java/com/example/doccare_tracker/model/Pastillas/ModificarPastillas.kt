package com.example.doccare_tracker.model.Pastillas

data class ModificarPastillas(
    val dosis: String,
    val id_pastilla: Int,
    val nombre: String,
    val periodo: Int,
    val tiempo: String
)