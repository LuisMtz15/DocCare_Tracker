package com.example.doccare_tracker.model.Ansiedad

data class ModificarAnsiedad(
    val hora: String,
    val id_ansiedad: Int,
    val intensidad: String,
    val nota: String,
    val sintoma: String
)