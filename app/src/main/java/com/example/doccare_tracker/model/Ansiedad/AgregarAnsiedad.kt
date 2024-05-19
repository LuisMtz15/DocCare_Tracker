package com.example.doccare_tracker.model.Ansiedad

data class AgregarAnsiedad(
    val fecha: String,
    val hora: String,
    val intensidad: String,
    val nota: String,
    val sintoma: String,
    val usuario_id: Int
)