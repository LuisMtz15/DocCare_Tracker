
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.doccare_tracker.model.Actividad.LeerActividadRespuesta
import com.example.doccare_tracker.model.Alimentos.LeerAlimentosRespuesta
import com.example.doccare_tracker.model.Ansiedad.LeerAnsiedadRespuesta
import com.example.doccare_tracker.model.Pastillas.LeerPastillasRespuesta
import com.example.doccare_tracker.model.Presion.LeerPresionesRespuesta
import com.example.doccare_tracker.model.Sueño.LeerSueñoRespuesta
import com.example.ejemplosapis.viewModel.AppViewModel

// Define un enum para representar los diferentes estados de los botones
enum class BotonSeleccionado {
    Alimentos,
    Actividad,
    Ansiedad,
    Sueño,
    Pastillas,
    Presion
}
@Composable
fun PantallaPrincipal(
    Alimentosinfo: LeerAlimentosRespuesta,
    Actividadinfo: LeerActividadRespuesta,
    Ansiedadinfo: LeerAnsiedadRespuesta,
    Sueñoinfo: LeerSueñoRespuesta,
    Pastillasinfo: LeerPastillasRespuesta,
    Presioninfo: LeerPresionesRespuesta,
    viewModel: AppViewModel
) {
    var botonSeleccionado by remember { mutableStateOf<BotonSeleccionado?>(null) }
    val nSuenos=viewModel.nohaySuenos.collectAsState()
    val nPresiones=viewModel.nohayPresiones.collectAsState()
    val nPastillas= viewModel.nohayPastillas.collectAsState()
    val nAnsiedades=viewModel.nohayAnsiedades.collectAsState()
    val nActividades=viewModel.nohayActividades.collectAsState()
    val nAlimentos=viewModel.nohayAlimentoss.collectAsState()


    Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        // Botones para seleccionar cada LazyColumn
        Row{
            CustomButton(
                color = MyColorPalette.AlimentosF,
                colorText = Color.White,
                buttonText = "Alimentos",
                size = 5,
                onClickAction = {
                    botonSeleccionado = BotonSeleccionado.Alimentos
                }
            )
            Spacer(modifier = Modifier.padding(end = 10.dp))

            CustomButton(
                color = MyColorPalette.ActividadF,
                colorText = Color.White,
                buttonText = "Actividades",
                size = 5,
                onClickAction = { botonSeleccionado = BotonSeleccionado.Actividad }
            )

            Spacer(modifier = Modifier.padding(end = 10.dp))

            CustomButton(
                color = MyColorPalette.SintomasF,
                colorText = Color.White,
                buttonText = "Ansiedad",
                size = 5,
                onClickAction = { botonSeleccionado = BotonSeleccionado.Ansiedad }
            )
        }
        Spacer(modifier = Modifier.padding(bottom = 10.dp))
        Row {
            CustomButton(
                color = MyColorPalette.SueñoF,
                colorText = Color.White,
                buttonText = "Sueño",
                size = 5,
                onClickAction = { botonSeleccionado = BotonSeleccionado.Sueño }
            )
            Spacer(modifier = Modifier.padding(end = 10.dp))

            CustomButton(
                color = MyColorPalette.PastillasF,
                colorText = Color.White,
                buttonText = "Pastillas",
                size = 5,
                onClickAction = { botonSeleccionado = BotonSeleccionado.Pastillas }
            )
            Spacer(modifier = Modifier.padding(end = 10.dp))

            CustomButton(
                color = MyColorPalette.PresionF,
                colorText = Color.White,
                buttonText = "Presion",
                size = 5,
                onClickAction = { botonSeleccionado = BotonSeleccionado.Presion }
            )
        }

    }
    Column {
        botonSeleccionado?.let { boton ->
            when (boton) {
                BotonSeleccionado.Alimentos -> Alimentos(Alimentosinfo, nAlimentos.value)
                BotonSeleccionado.Actividad -> Actividad(Actividadinfo, nActividades.value)
                BotonSeleccionado.Ansiedad -> Ansiedad(Ansiedadinfo, nAnsiedades.value)
                BotonSeleccionado.Sueño -> Sueño(Sueñoinfo, nSuenos.value)
                BotonSeleccionado.Pastillas -> Pastillas(Pastillasinfo, nPastillas.value)
                BotonSeleccionado.Presion -> Presion(Presioninfo, nPresiones.value)
            }
        }
    }
}

@Composable
fun Alimentos(informacion: LeerAlimentosRespuesta, value: Boolean) {
    Spacer(modifier = Modifier.padding(top = 40.dp))

    if (value) {
        Column(horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "No hay datos disponibles",
                fontSize = 20.sp, fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }else {
        Box(modifier = Modifier.padding(start = 40.dp)) {
            Row() {
                Box() {
                    Text(text = "Alimento", style = MaterialTheme.typography.titleMedium)
                }
                Spacer(modifier = Modifier.padding(end = 50.dp))
                Box() {
                    Text(text = "Tamaño", style = MaterialTheme.typography.titleMedium)
                }
                Spacer(modifier = Modifier.padding(end = 30.dp))
                Box() {
                    Text(text = "Fecha", style = MaterialTheme.typography.titleMedium)
                }
            }
        }

        LazyColumn {
            items(items = informacion) { alimento ->
                Card_view_Alimentos(alimento = alimento, onClick = {})
            }
        }
    }
}

@Composable
fun Actividad(informacion: LeerActividadRespuesta, value: Boolean) {

    Spacer(modifier = Modifier.padding(top = 40.dp))
    if (value) {
        Column(horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "No hay datos disponibles",
                fontSize = 20.sp, fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }else {
        Box(modifier = Modifier.padding(start = 40.dp)) {
            Row() {
                Box() {
                    Text(text = "Actividad", style = MaterialTheme.typography.titleMedium)
                }
                Spacer(modifier = Modifier.padding(end = 50.dp))
                Box() {
                    Text(text = "Intensidad", style = MaterialTheme.typography.titleMedium)
                }
                Spacer(modifier = Modifier.padding(end = 25.dp))
                Box() {
                    Text(text = "Duracion(min)", style = MaterialTheme.typography.titleMedium)
                }
            }
        }

        LazyColumn {
            items(items = informacion) { actividad ->
                Card_view_Actividad(actividad = actividad, onClick = {})
            }
        }
    }
}

@Composable
fun Ansiedad(informacion: LeerAnsiedadRespuesta, value: Boolean) {

    Spacer(modifier = Modifier.padding(top = 40.dp))
    if (value) {
        Column(horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "No hay datos disponibles",
                fontSize = 20.sp, fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }else {
        Box(modifier = Modifier.padding(start = 40.dp)) {
            Row() {
                Box() {
                    Text(text = "Síntoma", style = MaterialTheme.typography.titleMedium)
                }
                Spacer(modifier = Modifier.padding(end = 80.dp))
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
            items(items = informacion) { ansiedad ->
                Card_view_Ansiedad(ansiedad = ansiedad, onClick = {})
            }
        }
    }
}

@Composable
fun Sueño(informacion: LeerSueñoRespuesta, value: Boolean) {

    Spacer(modifier = Modifier.padding(top = 40.dp))
    if (value) {
        Column(horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "No hay datos disponibles",
                fontSize = 20.sp, fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }else {
        Box(modifier = Modifier.padding(start = 20.dp)) {
            Row() {
                Box() {
                    Text(text = "Pastilla", style = MaterialTheme.typography.titleMedium)
                }
                Spacer(modifier = Modifier.padding(end = 25.dp))
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
            items(items = informacion) { sueno ->
                Card_view_Sueño(sueño = sueno, onClick = {})
            }
        }
    }
}


@Composable
fun Pastillas(informacion: LeerPastillasRespuesta, value: Boolean) {

    Spacer(modifier = Modifier.padding(top = 40.dp))
    if (value) {
        Column(horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "No hay datos disponibles",
                fontSize = 20.sp, fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }else {
        Box(modifier = Modifier.padding(start = 45.dp)) {
            Row() {
                Box() {
                    Text(text = "Nombre", style = MaterialTheme.typography.titleMedium)
                }
                Spacer(modifier = Modifier.padding(end = 70.dp))
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
            items(items = informacion) { pastilla ->
                Card_view_Pastillas(pastilla = pastilla, onClick = {})
            }
        }
    }
}


@Composable
fun Presion(informacion: LeerPresionesRespuesta, value: Boolean) {

    Spacer(modifier = Modifier.padding(top = 40.dp))
    if (value) {
        Column(horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "No hay datos disponibles",
                fontSize = 20.sp, fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }else {
        Box(modifier = Modifier.padding(start = 40.dp)) {
            Row() {
                Box() {
                    Text(text = "Hora", style = MaterialTheme.typography.titleMedium)
                }
                Spacer(modifier = Modifier.padding(end = 20.dp))
                Box() {
                    Text(text = "Sistólica", style = MaterialTheme.typography.titleMedium)
                }
                Spacer(modifier = Modifier.padding(end = 10.dp))
                Box() {
                    Text(text = "Diastólica", style = MaterialTheme.typography.titleMedium)
                }
                Spacer(modifier = Modifier.padding(end = 15.dp))
                Box() {
                    Text(text = "Sentir", style = MaterialTheme.typography.titleMedium)
                }
            }
        }
        LazyColumn {
            items(items = informacion) { presion ->
                Card_view_Presion(presion = presion, onClick = {})
            }
        }
    }
}


