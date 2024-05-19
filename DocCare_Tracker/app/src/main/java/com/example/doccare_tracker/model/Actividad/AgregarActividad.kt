package com.example.doccare_tracker.model.Actividad

data class AgregarActividad(
    val duracion: Int,
    val emocion_actividad: String,
    val fecha: String,
    val intensidad_actividad: String,
    val tipo_actividad: String,
    val usuario_id: Int
)