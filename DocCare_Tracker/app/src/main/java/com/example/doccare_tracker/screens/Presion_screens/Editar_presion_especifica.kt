
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.doccare_tracker.model.Graphs.DataGraph
import com.example.doccare_tracker.model.Presion.ModificarPresion
import com.example.ejemplosapis.viewModel.AppViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "StateFlowValueCalledInComposition")
@Composable
fun Editar_Presion_especifica(navController: NavHostController, viewModel: AppViewModel) {


    //Basicas
    val showLogoutDialog = remember { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }
    val usuario_id = viewModel.usuario_id.value


    //Iniciales
    val id_presion by viewModel.idPresion.collectAsState()
    val diastolica by viewModel.diastolica.collectAsState()
    val sistolica by viewModel.sistolica.collectAsState()
    val emocion by viewModel.emocionpresion.collectAsState()
    val hora by viewModel.horapresion.collectAsState()

    //Selecciones
    val horaseleccionada = remember{mutableStateOf(hora)}
    val emocionseleccionada = remember { mutableStateOf("") }
    val pS = remember{mutableStateOf(sistolica.toString())}
    val pD = remember{mutableStateOf(diastolica.toString())}

    //Tablas

    val emocioneslist by viewModel.emocioneslistpresion.collectAsState()

    val emociones = mutableListOf<String>()


    //Agregar Datos Tablas
    emocioneslist.forEach {emociont ->
        emociones.add(emociont.emocion)
    }


    //Results
    val editarpresionResult by viewModel.editarpresionesResult.collectAsState()
    val eliminarpresionResult by viewModel.eliminarpresionesResult.collectAsState()


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
                    text = "Editar Presión arterial",
                    color = MyColorPalette.PresionF,
                    textcolor = Color.White,
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 17.dp),
                    horizontalArrangement = Arrangement.Start
                ) {
                    IconOnlyButton(onClick = {navController.navigate(route = AppScreens.PresionModificar.route)}, tint = Color.White)
                }
            } //Cierra el box

            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)){
                Spacer(modifier = Modifier.padding(bottom = 30.dp))

                Text(text = "¿A qué hora hiciste la toma?",
                    style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
                )
                Spacer(modifier = Modifier.padding(bottom = 10.dp))

                TimePicker(color_button = MyColorPalette.PresionC, selectedTime = horaseleccionada)

                Spacer(modifier = Modifier.padding(bottom = 30.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "Presión Sistólica:",
                        style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    )

                    InputField_text(
                        valueState = pS,
                        labelId = "",
                        enabled = true,
                        keyboardType = KeyboardType.Number,
                        modifier = Modifier.padding(start = 10.dp))
                }

                Spacer(modifier = Modifier.padding(bottom = 10.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "Presión Diastólica:",
                        style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    )
                    InputField_text(
                        valueState = pD,
                        labelId = "",
                        enabled = true,
                        keyboardType = KeyboardType.Number,)
                }


                Spacer(modifier = Modifier.padding(bottom = 60.dp))

                Text(text = "¿Cómo te sentiste al realizar esta actividad?",
                    style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
                )

                SliderWithText(palabras = emociones, cP = MyColorPalette.PresionF,
                    cS = MyColorPalette.PresionC, initialValue = emocion, sel = emocionseleccionada)

                Spacer(modifier = Modifier.padding(bottom = 20.dp))

                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center) {
                    CustomButton(
                        color = MyColorPalette.PresionC,
                        colorText = Color.White,
                        buttonText = "Editar Datos",
                        size = 7,
                        onClickAction = {

                            viewModel.editarpresiones(ModificarPresion(id_presion = id_presion,
                                hora = horaseleccionada.value, emocion = emocionseleccionada.value,
                                sistolica = pS.value.toInt(), diastolica = pD.value.toInt()))
                        }
                    )
                }

                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center) {
                    Button(onClick = { showLogoutDialog.value = true },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent ),) {
                        Text(text = "Eliminar Presión Arterial",
                            color = MyColorPalette.PresionC)

                    }
                }


                //Para Editar

                editarpresionResult?.let { result ->
                    LaunchedEffect(result) {
                        if (result.isSuccess) {
                            viewModel.leertablaPresionDiastolica(
                                DataGraph(
                                usuario_id = usuario_id, fecha = obtenerFechaActual())
                            )
                            viewModel.leertablaPresionSistolica(
                                DataGraph(
                                usuario_id = usuario_id, fecha = obtenerFechaActual())
                            )
                            snackbarHostState.showSnackbar(
                                message = "Se hizo el cambio con éxito",
                                duration = SnackbarDuration.Short,
                                actionLabel = "Continuar"
                            )
                            //Reiniciar Valores
                            viewModel.setIdPresion(0)
                            viewModel.setHoraPresion("")
                            viewModel.setDiastolica(0)
                            viewModel.setSistolica(0)
                            viewModel.setEmocionPresion("")

                            //Reiniciar Estado
                            viewModel.setEditarPresionesResult(null)
                            viewModel.setFiltroPresionesResult(null)

                            navController.navigate(route = AppScreens.PresionModificar.route)
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
                        title = { Text("¿Quieres elimnar esta presión arterial?", textAlign = TextAlign.Center)},
                        confirmButton = {
                            Column(modifier = Modifier.fillMaxWidth().height(35.dp),horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center) {
                                TextButton(onClick = {
                                    // Lógica para cerrar sesión
                                    showLogoutDialog.value = false
                                    viewModel.eliminarpresiones(id_presion)
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


                eliminarpresionResult?.let { result ->
                    LaunchedEffect(result) {
                        if (result.isSuccess) {
                            viewModel.leertablaPresionDiastolica(DataGraph(
                                usuario_id = usuario_id, fecha = obtenerFechaActual()))
                            viewModel.leertablaPresionSistolica(DataGraph(
                                usuario_id = usuario_id, fecha = obtenerFechaActual()))
                            snackbarHostState.showSnackbar(
                                message = "Se eliminó el registro con éxito",
                                duration = SnackbarDuration.Short,
                                actionLabel = "Continuar"
                            )
                            //Reiniciar Valores
                            viewModel.setIdPresion(0)
                            viewModel.setHoraPresion("")
                            viewModel.setDiastolica(0)
                            viewModel.setSistolica(0)
                            viewModel.setEmocionPresion("")

                            //Reiniciar Estado
                            viewModel.setEliminarPresionesResult(null)
                            viewModel.setFiltroPresionesResult(null)

                            navController.navigate(route = AppScreens.PresionModificar.route)
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