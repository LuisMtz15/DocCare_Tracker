package com.example.doccare_tracker.model.Pastillas

data class AgregarPastillas(
    val dosis: String,
    val fecha: String,
    val nombre: String,
    val periodo: Int,
    val tiempo: String,
    val usuario_id: Int
)