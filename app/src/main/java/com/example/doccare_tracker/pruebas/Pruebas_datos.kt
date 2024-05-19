package com.example.doccare_tracker.pruebas

//Clase simulando los datos jalados
data class Persona(
    val id: Int,
    val nombre: String,
    val edad: Int,
    val sexo: String
)

data class Alimentos(
    val nombre: String,
    val tamaño: String, //Chico, Mediano, Grande, Jumbo
    val fecha: String
)

data class Actividades(
    val tipoAct: String,
    val intensidad: String, //Baja, Moderada, Alta
    val duracion: String
)


data class Presion(
    val hora: String,
    val ps: Int,
    val pd: Int,
    val sentimento: String
)

data class Ansiedad(
    val sintomas: String,
    val hora: String,
    val intensidad: String,
)

data class Sueño(
    val Pastilla: String,
    val horaDormir: String,
    val horaDespertar: String,
    val sentimiento: String,
)

data class Pastillas(
    val nombre: String,
    val mg: Int,
    val periodo: String,
    val tiempo: String
)

class Pruebas_datos {
    val personas = listOf(
        Persona(1, "Juan Carlos", 25, "M"),
        Persona(2, "María Magdanela", 30, "F"),
        Persona(3, "Carlos Ruiz", 40, "M"),
        Persona(4, "Andrea Martinez", 98, "F"),
        Persona(5, "Andrea Martinez", 98, "F"),
        Persona(6, "Andrea Martinez", 98, "F"),
    )

    val personas2 = listOf(
        Persona(1, "Juanitooo", 25, "M"),
        Persona(2, "Maríaaaaa", 30, "F"),
        Persona(3, "Carlitoooos", 40, "M"),
        Persona(4, "Andrea bonita", 98, "F"),
        Persona(5, "Andrea Preciosa", 98, "F"),
        Persona(6, "Andrea Hermosa", 98, "F"),
    )
    // Datos de ejemplo para la data class Alimentos
    val alimentosList = listOf(
        Alimentos("Manzana", "M", "2024-05-20"),
        Alimentos("Ensalada", "G", "2024-05-21"),
        Alimentos("Pasta", "J", "2024-05-22"),
        Alimentos("Pollo", "M", "2024-05-23"),
        Alimentos("Hamburguesa", "CH", "2024-05-24"),
        Alimentos("Pizza", "J", "2024-05-25"),
        Alimentos("Sushi", "M", "2024-05-26")
    )

    // Datos de ejemplo para la data class Actividades
    val actividadesList = listOf(
        Actividades("Aeróbico", "Alta", "30"),
        Actividades("Equlibrado", "Moderada", "45"),
        Actividades("Aeróbico", "Alta", "60"),
        Actividades("Anaeróbico", "Baja", "65"),
        Actividades("Anaeróbico", "Moderada", "35"),
        Actividades("Anaeróbico", "Alta", "45"),
        Actividades("Aeróbico", "Moderada", "30")
    )

    // Datos de ejemplo para la data class Presion
    val presionList = listOf(
        Presion("08:00", 120, 80, "Presionado"),
        Presion("12:00", 130, 85, "Relajado"),
        Presion("16:00", 125, 82, "Tranquilo"),
        Presion("20:00", 122, 78, "Feliz"),
        Presion("00:00", 118, 76, "Descansado"),
        Presion("04:00", 115, 75, "Feliz"),
        Presion("09:00", 123, 79, "Energizado")
    )

    // Datos de ejemplo para la data class Ansiedad
    val ansiedadList = listOf(
        Ansiedad("Nerviosismo", "08:30", "Moderada"),
        Ansiedad("Ataques de pánico", "15:00", "Alta"),
        Ansiedad("Inquietud", "11:45", "Baja"),
        Ansiedad("Preocupación constante", "20:30", "Moderada"),
        Ansiedad("Insomnio", "03:00", "Alta"),
        Ansiedad("Desesperanza", "17:20", "Baja"),
        Ansiedad("Palpitaciones", "09:15", "Moderada")
    )

    // Datos de ejemplo para la data class Sueño
    val suenoList = listOf(
        Sueño("SI", "22:30", "06:30", "Incomodo"),
        Sueño("NO", "00:00", "08:00", "No sentí la noche"),
        Sueño("SI", "23:00", "07:30", "Tranquilo"),
        Sueño("NO", "21:45", "05:45", "Normal"),
        Sueño("SI", "22:15", "06:45", "Molesto"),
        Sueño("NO", "00:30", "09:00", "Normal"),
        Sueño("SI", "01:00", "08:30", "Incomodo")
    )

    // Datos de ejemplo para la data class Pastillas
    val pastillasList = listOf(
        Pastillas("Ibuprofeno", 200, "8", "Cron"),
        Pastillas("Paracetamol", 500, "6", "Temp"),
        Pastillas("Omeprazol", 20, "6", "Cron"),
        Pastillas("Lorazepam", 2, "12", "Temp"),
        Pastillas("Vitamina C", 1000, "6", "Temp"),
        Pastillas("Cetirizina", 10, "6", "Cron"),
        Pastillas("Calcio", 600, "6", "Cron")
    )




}