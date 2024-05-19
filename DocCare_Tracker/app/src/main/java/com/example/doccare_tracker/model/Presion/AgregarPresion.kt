package com.example.doccare_tracker.model.Presion

data class AgregarPresion(
    val diastolica: Int,
    val emocion: String,
    val fecha: String,
    val hora: String,
    val sistolica: Int,
    val usuario_id: Int
)