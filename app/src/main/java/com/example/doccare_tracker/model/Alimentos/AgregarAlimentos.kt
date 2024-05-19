package com.example.doccare_tracker.model.Alimentos

data class AgregarAlimentos(
    val fecha: String,
    val nombre: String,
    val tipo_porcion: String,
    val usuario_id: Int
)