package com.example.doccare_tracker.model.Ansiedad

data class LeerAnsiedadItem(
    val fecha: String,
    val hora: String,
    val id: Int,
    val intensidad_ansiedad: String,
    val nota: String,
    val sintoma_ansiedad: String
)