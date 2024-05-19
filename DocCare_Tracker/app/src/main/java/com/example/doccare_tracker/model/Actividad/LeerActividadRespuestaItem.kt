package com.example.doccare_tracker.model.Actividad

data class LeerActividadRespuestaItem(
    val duracion: Int,
    val emocion_actividad: String,
    val fecha: String,
    val id: Int,
    val intensidad_actividad: String,
    val tipo_actividad: String
)