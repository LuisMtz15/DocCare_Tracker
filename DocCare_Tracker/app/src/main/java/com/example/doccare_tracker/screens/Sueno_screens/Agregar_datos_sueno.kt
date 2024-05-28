
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.doccare_tracker.model.Graphs.DataGraph
import com.example.doccare_tracker.model.Sueño.AgregarSueño
import com.example.ejemplosapis.viewModel.AppViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Agregar_Sueño(navController: NavHostController, viewModel: AppViewModel) {

    //Basicas
    val snackbarHostState = remember { SnackbarHostState() }
    var showyDialog = remember { mutableStateOf(false) }
    val usuario by viewModel.usuario_id.collectAsState()

    //Selecciones
    val horaseleccionadaDormir = remember{mutableStateOf("")}
    val horaseleccionadaDespertar = remember{mutableStateOf("")}
    val selected = remember { mutableStateOf("") }
    val calidadseleccionada = remember{mutableStateOf("")}


    //Tablas

    val calidadeslist by viewModel.calidadeslist.collectAsState()

    val calidades = mutableListOf<String>()

    //Agregar Datos Tablas
    calidadeslist.forEach {calidadt ->
        calidades.add(calidadt.emocion)
    }

    //Results
    val agregarsuenoResult by viewModel.agregarsuenoResult.collectAsState()

    Scaffold(modifier = Modifier.fillMaxSize(),
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier.padding(16.dp)
            )
        }) {


        Column(modifier = Modifier.fillMaxSize()) {
            Box {
                TitleText(
                    text = "Agregar Sueño",
                    color = MyColorPalette.SueñoF,
                    textcolor = Color.White,
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 17.dp),
                    horizontalArrangement = Arrangement.Start
                ) {
                    IconOnlyButton(onClick = {navController.navigate(route = AppScreens.Sueño.route)}, tint = Color.White)
                }
            } //Cierra el box

            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)){
                Spacer(modifier = Modifier.padding(bottom = 30.dp))

                PastillaSelection(selected =selected)

                Spacer(modifier = Modifier.padding(bottom = 20.dp))

                Text(text = "¿Aproximadamente, a qué hora te fuiste a la cama?",
                    style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
                )
                Spacer(modifier = Modifier.padding(bottom = 10.dp))

                TimePicker(color_button = MyColorPalette.SueñoC, selectedTime = horaseleccionadaDormir)
                Spacer(modifier = Modifier.padding(bottom = 20.dp))

                Spacer(modifier = Modifier.padding(bottom = 20.dp))

                Text(text = "¿Aproximadamente, a qué hora te despertaste?",
                    style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
                )
                Spacer(modifier = Modifier.padding(bottom = 10.dp))

                TimePicker(color_button = MyColorPalette.SueñoC, selectedTime = horaseleccionadaDespertar)
                Spacer(modifier = Modifier.padding(bottom = 20.dp))



                Text(text = "¿Cómo sentiste el tiempo de sueño?",
                    style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
                )
                Spacer(modifier = Modifier.padding(bottom = 10.dp))

                SliderWithText(palabras = calidades, cP = MyColorPalette.SueñoF,
                    cS = MyColorPalette.SueñoC, sel = calidadseleccionada)

                Spacer(modifier = Modifier.padding(bottom = 20.dp))

                Spacer(modifier = Modifier.padding(bottom = 20.dp))

                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center) {
                    CustomButton(
                        color = MyColorPalette.SueñoC,
                        colorText = Color.White,
                        buttonText = "Enviar Datos",
                        size = 7,
                        onClickAction = {

                            if(horaseleccionadaDormir.value !="" && horaseleccionadaDespertar.value !=""
                                && calidadseleccionada.value !="" && selected.value !=""){

                                val duracionSueno = calcularDuracionSueño(horaseleccionadaDormir.value,
                                    horaseleccionadaDespertar.value)
                                viewModel.setHoradormir(horaseleccionadaDormir.value)
                                viewModel.setHoradespertar(horaseleccionadaDespertar.value)
                                viewModel.agregarsueno(AgregarSueño(calidad_sueño = calidadseleccionada.value,
                                    fecha = obtenerFechaActual(), horas = duracionSueno, pastilla = selected.value,
                                    usuario_id = usuario, horaDormir = horaseleccionadaDormir.value,
                                    horaDespertar = horaseleccionadaDespertar.value))
                            }else{showyDialog.value = true}

                        }
                    )
                }


                if (showyDialog.value) {
                    alterdialogs_one(showyDialog, "Debes llenar todos los campos para poder agregar un Registro", "Entendido")
                }

                agregarsuenoResult?.let { result ->
                    LaunchedEffect(result) {
                        if (result.isSuccess) {
                            viewModel.leertablaSuenohoras(
                                DataGraph(
                                usuario_id = usuario, fecha = obtenerFechaActual())
                            )
                            viewModel.leertablaSuenopastillas(
                                DataGraph(
                                usuario_id = usuario, fecha = obtenerFechaActual())
                            )
                            snackbarHostState.showSnackbar(
                                message = "Se agregó el registro ccorrectamente",
                                duration = SnackbarDuration.Short,
                                actionLabel = "Continuar"
                            )
                            viewModel.setNohaySuenos(false) //Ya hay alimentos
                            viewModel.setAgregarSueñoResult(null)
                            navController.navigate(route = AppScreens.Sueño.route)
                        } else {
                            snackbarHostState.showSnackbar(
                                message = "Error: ${result.exceptionOrNull()?.message}",
                                duration = SnackbarDuration.Long,
                                actionLabel = "Reintentar"
                            )
                        }
                    }
                }
            }

        }
    }
}


