
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.doccare_tracker.model.Actividad.LeerActividadRespuesta
import com.example.doccare_tracker.model.Alimentos.LeerAlimentosRespuesta
import com.example.doccare_tracker.model.Ansiedad.LeerAnsiedadRespuesta
import com.example.doccare_tracker.model.Pastillas.LeerPastillasRespuesta
import com.example.doccare_tracker.model.Presion.LeerPresionesRespuesta
import com.example.doccare_tracker.model.Sueño.LeerSueñoRespuesta
import com.example.ejemplosapis.viewModel.AppViewModel
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@Composable
fun Alimentos_user(
    navController: NavHostController,
    viewModel: AppViewModel,
    alimentos_user: MutableState<LeerAlimentosRespuesta>,

) {

    Spacer(modifier = Modifier.padding(top = 40.dp))
    Box(modifier = Modifier.padding(start = 40.dp)) {
        Row() {
            Box() {
                Text(text = "Alimento", style = MaterialTheme.typography.titleMedium)
            }
            Spacer(modifier = Modifier.padding(end = 65.dp))
            Box() {
                Text(text = "Tamaño", style = MaterialTheme.typography.titleMedium)
            }
            Spacer(modifier = Modifier.padding(end = 35.dp))
            Box() {
                Text(text = "Fecha", style = MaterialTheme.typography.titleMedium)
            }
        }
    }

    LazyColumn {
        items(items = alimentos_user.value) { alimento ->
            Card_view_Alimentos_won(
                alimento = alimento,
                onClick = {
                    viewModel.setIdAlimento(alimento.id)
                    viewModel.setNombreAlimento(alimento.nombre)
                    viewModel.setPorcionAlimento(alimento.tipo_porcion)
                    navController.navigate(route = AppScreens.AlimentosEditar.route)
                }
            )
        }
    }
}

@Composable
fun Actividad_user(
    navController: NavHostController,
    viewModel: AppViewModel,
    actividades_user: MutableState<LeerActividadRespuesta>
) {

    Spacer(modifier = Modifier.padding(top = 40.dp))
    Box(modifier = Modifier.padding(start = 40.dp)) {
        Row() {
            Box() {
                Text(text = "Actividad", style = MaterialTheme.typography.titleMedium)
            }
            Spacer(modifier = Modifier.padding(end = 65.dp))
            Box() {
                Text(text = "Intensidad", style = MaterialTheme.typography.titleMedium)
            }
            Spacer(modifier = Modifier.padding(end = 40.dp))
            Box() {
                Text(text = "Duracion(min)", style = MaterialTheme.typography.titleMedium)
            }
        }
    }

    LazyColumn {
        items(items = actividades_user.value){ actividad ->
            Card_view_Actividad_won(
                actividad = actividad,
                onClick = {
                    viewModel.setIdActividad(actividad.id)
                    viewModel.setDuracionActividad(actividad.duracion)
                    viewModel.setEmocionActividad(actividad.emocion_actividad)
                    viewModel.setIntensidadActividad(actividad.intensidad_actividad)
                    viewModel.setTipoActividad(actividad.tipo_actividad)
                    navController.navigate(route = AppScreens.ActividadEditar.route)
                })

        }
    }
}

@Composable
fun Ansiedad_user(
    informacion: MutableState<LeerAnsiedadRespuesta>,
    navController: NavHostController,
    viewModel: AppViewModel
) {

    Spacer(modifier = Modifier.padding(top = 40.dp))
    Box(modifier = Modifier.padding(start = 40.dp)) {
        Row() {
            Box() {
                Text(text = "Síntoma", style = MaterialTheme.typography.titleMedium)
            }
            Spacer(modifier = Modifier.padding(end = 100.dp))
            Box() {
                Text(text = "Hora", style = MaterialTheme.typography.titleMedium)
            }
            Spacer(modifier = Modifier.padding(end = 25.dp))
            Box() {
                Text(text = "Intensidad", style = MaterialTheme.typography.titleMedium)
            }
        }
    }
    LazyColumn {
        items(items = informacion.value) { ansiedad ->
            Card_view_Ansiedad_won(
                ansiedad = ansiedad,
                onClick = {
                    viewModel.setIdAnsiedad(ansiedad.id)
                    viewModel.setHora(formatTimeWithoutSeconds(ansiedad.hora))
                    viewModel.setNota(ansiedad.nota)
                    viewModel.setSintomas(ansiedad.sintoma_ansiedad)
                    viewModel.setIntensidadAnsiedad(ansiedad.intensidad_ansiedad)
                    navController.navigate(route = AppScreens.AnsiedadEditar.route)
                })
        }
    }
}

@Composable
fun Sueño_user(informacion: MutableState<LeerSueñoRespuesta>, navController: NavHostController, viewModel: AppViewModel) {

    Spacer(modifier = Modifier.padding(top = 40.dp))
    Box(modifier = Modifier.padding(start = 20.dp)) {
        Row() {
            Box() {
                Text(text = "Pastilla", style = MaterialTheme.typography.titleMedium)
            }
            Spacer(modifier = Modifier.padding(end = 30.dp))
            Box() {
                Text(text = "Dormir", style = MaterialTheme.typography.titleMedium)
            }
            Spacer(modifier = Modifier.padding(end = 15.dp))
            Box() {
                Text(text = "Despertar", style = MaterialTheme.typography.titleMedium)
            }
            Spacer(modifier = Modifier.padding(end = 15.dp))
            Box() {
                Text(text = "Sentir", style = MaterialTheme.typography.titleMedium)
            }
        }
    }
    LazyColumn {
        items(items=informacion.value) { sueno ->
            Card_view_Sueño_won(
                sueño = sueno,
                onClick = {

                    viewModel.setIdSueno(sueno.id)
                    viewModel.setHoras(sueno.horas)
                    viewModel.setCalidad(sueno.calidad_sueño)
                    viewModel.setPastilla(sueno.pastilla_sueño)
                    viewModel.setHoradormir(formatTimeWithoutSeconds(sueno.horaDormir))
                    viewModel.setHoradespertar(formatTimeWithoutSeconds(sueno.horaDespertar))
                    navController.navigate(route = AppScreens.SueñoEditar.route)


                })
        }
    }
}

fun formatTimeWithoutSeconds(time: String): String {
    // Parsear la cadena de tiempo en un objeto LocalTime
    val localTime = LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm:ss"))

    // Formatear el objeto LocalTime a una cadena sin segundos
    val formatter = DateTimeFormatter.ofPattern("HH:mm")
    return localTime.format(formatter)
}

@Composable
fun Pastillas_user(
    informacion: MutableState<LeerPastillasRespuesta>,
    navController: NavHostController,
    viewModel: AppViewModel
) {

    Spacer(modifier = Modifier.padding(top = 40.dp))
    Box(modifier = Modifier.padding(start = 45.dp)) {
        Row() {
            Box() {
                Text(text = "Nombre", style = MaterialTheme.typography.titleMedium)
            }
            Spacer(modifier = Modifier.padding(end = 90.dp))
            Box() {
                Text(text = "Mg", style = MaterialTheme.typography.titleMedium)
            }
            Spacer(modifier = Modifier.padding(end = 15.dp))
            Box() {
                Text(text = "Periodo", style = MaterialTheme.typography.titleMedium)
            }
            Spacer(modifier = Modifier.padding(end = 10.dp))
            Box() {
                Text(text = "Tiempo", style = MaterialTheme.typography.titleMedium)
            }
        }
    }
    LazyColumn {
        items(items = informacion.value) { pastilla ->
            Card_view_Pastillas_won(
                pastilla = pastilla,
                onClick = {
                    viewModel.setIdPastilla(pastilla.id)
                    viewModel.setNombrePastilla(pastilla.nombre)
                    viewModel.setDosisPastilla(pastilla.dosis)
                    viewModel.setPeriodoPastilla(pastilla.periodo_pastilla)
                    viewModel.setTiempoPastilla(pastilla.tiempo_pastilla)

                    navController.navigate(route = AppScreens.PastillasEditar.route)

                })
        }
    }
}


@Composable
fun Presion_user(
    informacion: MutableState<LeerPresionesRespuesta>,
    navController: NavHostController,
    viewModel: AppViewModel
) {

    Spacer(modifier = Modifier.padding(top = 40.dp))
    Box(modifier = Modifier.padding(start = 40.dp)) {
        Row() {
            Box() {
                Text(text = "Hora", style = MaterialTheme.typography.titleMedium)
            }
            Spacer(modifier = Modifier.padding(end = 30.dp))
            Box() {
                Text(text = "Sistólica", style = MaterialTheme.typography.titleMedium)
            }
            Spacer(modifier = Modifier.padding(end = 10.dp))
            Box() {
                Text(text = "Diastólica", style = MaterialTheme.typography.titleMedium)
            }
            Spacer(modifier = Modifier.padding(end = 25.dp))
            Box() {
                Text(text = "Sentir", style = MaterialTheme.typography.titleMedium)
            }
        }
    }
    LazyColumn {
        items(items=informacion.value) { presion ->
            Card_view_Presion_won(
                presion = presion,
                onClick = {

                    viewModel.setIdPresion(presion.id)
                    viewModel.setHoraPresion(formatTimeWithoutSeconds(presion.hora))
                    viewModel.setDiastolica(presion.diastolica)
                    viewModel.setSistolica(presion.sistolica)
                    viewModel.setEmocionPresion(presion.emocion_presion)

                    navController.navigate(route = AppScreens.PresionEditar.route)

                })
        }
    }
}


