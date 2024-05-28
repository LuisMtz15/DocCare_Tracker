
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.doccare_tracker.model.Graphs.DataGraph
import com.example.doccare_tracker.model.Sueño.ModificarSueño
import com.example.ejemplosapis.viewModel.AppViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "StateFlowValueCalledInComposition")
@Composable
fun Editar_sueño_especifico(navController: NavHostController, viewModel: AppViewModel) {


    //Basicas
    val showLogoutDialog = remember { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }
    val usuario_id = viewModel.usuario_id.value

    //Iniciales
    val id_sueno by viewModel.idSueno.collectAsState()
    val calidad by viewModel.calidad.collectAsState()
    val pastilla by viewModel.pastilla.collectAsState()
    val horaDormir by viewModel.horadormir.collectAsState()
    val horaDespertar by viewModel.horadespertar.collectAsState()

    //Selecciones
    val horaseleccionadaDormir = remember{mutableStateOf(horaDormir)}
    val horaseleccionadaDespertar = remember{mutableStateOf(horaDespertar)}
    val selected = remember { mutableStateOf(pastilla) }
    val calidadseleccionada = remember{mutableStateOf("")}


    //Tablas

    val calidadeslist by viewModel.calidadeslist.collectAsState()

    val calidades = mutableListOf<String>()

    //Agregar Datos Tablas
    calidadeslist.forEach {calidadt ->
        calidades.add(calidadt.emocion)
    }

    //Results
    val editarsuenoResult by viewModel.editarsuenoResult.collectAsState()
    val eliminarsuenoResult by viewModel.eliminarsuenoResult.collectAsState()

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
                    text = "Editar Sueño",
                    color = MyColorPalette.SueñoF,
                    textcolor = Color.White,
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 17.dp),
                    horizontalArrangement = Arrangement.Start
                ) {
                    IconOnlyButton(onClick = {navController.navigate(route = AppScreens.SueñoModificar.route)}, tint = Color.White)
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

                Spacer(modifier = Modifier.padding(bottom = 10.dp))

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
                    cS = MyColorPalette.SueñoC, initialValue = calidad, sel = calidadseleccionada)

                Spacer(modifier = Modifier.padding(bottom = 10.dp))

                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center) {
                    CustomButton(
                        color = MyColorPalette.SueñoC,
                        colorText = Color.White,
                        buttonText = "Editar Datos",
                        size = 7,
                        onClickAction = {
                            val duracionSueno = calcularDuracionSueño(horaseleccionadaDormir.value,
                                horaseleccionadaDespertar.value)

                            viewModel.editarsueno(ModificarSueño(calidad_sueño = calidadseleccionada.value,
                                horas = duracionSueno, id_sueño = id_sueno, pastilla = selected.value,
                                horaDormir=horaseleccionadaDormir.value, horaDespertar=horaseleccionadaDespertar.value))
                        }
                    )
                }

                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center) {
                    Button(onClick = { showLogoutDialog.value = true },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent ),) {
                        Text(text = "Eliminar Sueño",
                            color = MyColorPalette.SueñoC)

                    }
                }

                editarsuenoResult?.let { result ->
                    LaunchedEffect(result) {
                        if (result.isSuccess) {
                            viewModel.leertablaSuenohoras(DataGraph(
                                usuario_id = usuario_id, fecha = obtenerFechaActual()))
                            viewModel.leertablaSuenopastillas(DataGraph(
                                usuario_id = usuario_id, fecha = obtenerFechaActual()))
                            snackbarHostState.showSnackbar(
                                message = "Se hizo el cambio con éxito",
                                duration = SnackbarDuration.Short,
                                actionLabel = "Continuar"
                            )
                            //Reiniciar Valores
                            viewModel.setIdSueno(0)
                            viewModel.setHoras(0)
                            viewModel.setCalidad("")
                            viewModel.setPastilla("")
                            viewModel.setHoradormir("")
                            viewModel.setHoradespertar("")

                            //Reiniciar Estado
                            viewModel.setEditarSueñoResult(null)
                            viewModel.setFiltroSueñoResult(null)


                            navController.navigate(route = AppScreens.SueñoModificar.route)
                        } else {
                            snackbarHostState.showSnackbar(
                                message = "Error: ${result.exceptionOrNull()?.message}",
                                duration = SnackbarDuration.Long,
                                actionLabel = "Reintentar"
                            )
                        }
                    }
                }



                // AlertDialog para confirmar la salida
                if (showLogoutDialog.value) {
                    AlertDialog(
                        onDismissRequest = { showLogoutDialog.value = false },
                        title = { Text("¿Quieres elimnar este sueño?", textAlign = TextAlign.Center)},
                        confirmButton = {
                            Column(modifier = Modifier.fillMaxWidth().height(35.dp),horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center) {
                                TextButton(onClick = {
                                    // Lógica para cerrar sesión
                                    showLogoutDialog.value = false
                                    viewModel.eliminarsueno(id_sueno)
                                }) {
                                    Text("Eliminar")
                                }
                            }


                        },
                        dismissButton = {
                            Column(modifier = Modifier.fillMaxWidth().height(35.dp),horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center) {
                                TextButton(onClick = {
                                    // Lógica para cancelar
                                    showLogoutDialog.value = false
                                }) {
                                    Text("Cancelar")
                                }
                            }

                        }
                    )
                }

                eliminarsuenoResult?.let { result ->
                    LaunchedEffect(result) {
                        if (result.isSuccess) {
                            viewModel.leertablaSuenohoras(
                                DataGraph(
                                usuario_id = usuario_id, fecha = obtenerFechaActual())
                            )
                            viewModel.leertablaSuenopastillas(
                                DataGraph(
                                usuario_id = usuario_id, fecha = obtenerFechaActual())
                            )
                            snackbarHostState.showSnackbar(
                                message = "Se eliminó el registro con éxito",
                                duration = SnackbarDuration.Short,
                                actionLabel = "Continuar"
                            )
                            //Reiniciar Valores
                            viewModel.setIdSueno(0)
                            viewModel.setHoras(0)
                            viewModel.setCalidad("")
                            viewModel.setPastilla("")
                            viewModel.setHoradormir("")
                            viewModel.setHoradespertar("")

                            //Reiniciar Estado
                            viewModel.setEliminarSueñoResult(null)
                            viewModel.setFiltroSueñoResult(null)


                            navController.navigate(route = AppScreens.SueñoModificar.route)
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


fun calcularDuracionSueño(horaDormir: String, horaDespertar: String): Int {
    val (horasDormir, minutosDormir) = horaDormir.split(":").map { it.toInt() }
    val (horasDespertar, minutosDespertar) = horaDespertar.split(":").map { it.toInt() }

    val totalMinutosDormir = horasDormir * 60 + minutosDormir
    val totalMinutosDespertar = horasDespertar * 60 + minutosDespertar

    val duracionSueño = if (totalMinutosDespertar >= totalMinutosDormir) {
        totalMinutosDespertar - totalMinutosDormir
    } else {
        (24 * 60 - totalMinutosDormir) + totalMinutosDespertar
    }

    val horasRedondeadas = if (duracionSueño % 60 >= 42) {
        duracionSueño / 60 + 1
    } else {
        duracionSueño / 60
    }

    return horasRedondeadas
}

