package com.example.doccare_tracker.model.Actividad

data class ModificarActividad(
    val duracion: Int,
    val emocion_actividad: String,
    val id_actividad: Int,
    val intensidad_actividad: String,
    val tipo_actividad: String
)