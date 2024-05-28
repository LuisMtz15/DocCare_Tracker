
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
import com.example.doccare_tracker.model.Ansiedad.ModificarAnsiedad
import com.example.doccare_tracker.model.Graphs.DataGraph
import com.example.ejemplosapis.viewModel.AppViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "StateFlowValueCalledInComposition")
@Composable
fun Editar_sintoma_especifico(navController: NavHostController, viewModel: AppViewModel) {

    //Basicas
    val showLogoutDialog = remember { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }
    val usuario_id = viewModel.usuario_id.value


    //Iniciales
    val id_Ansiedad by viewModel.idAnsiedad.collectAsState()
    val hora by viewModel.hora.collectAsState()
    val intensidad by viewModel.intensidadAnsiedad.collectAsState()
    val nota_inicial by viewModel.nota.collectAsState()
    val sintoma by viewModel.sintomas.collectAsState()


    //Selecciones
    val sintomaseleccionado = remember { mutableStateOf("") }
    val horaseleccionada = remember{mutableStateOf(hora)}
    val intensidadeleccionado = remember { mutableStateOf("") }
    val nota = remember{mutableStateOf(nota_inicial)}

    //Tablas

    val sintomaslist by viewModel.sintomaslist.collectAsState()
    val intensidadlist by viewModel.intensidadlist.collectAsState()

    val sintomas = mutableListOf<String>()
    val intensidades = mutableListOf<String>()

    //Agregar Datos Tablas
    sintomaslist.forEach {sint ->
        sintomas.add(sint.sintoma)
    }

    intensidadlist.forEach {intensidad ->
        intensidades.add(intensidad.intensidad)
    }


    //Results
    val editaransiedadResult by viewModel.editarAnsiedadResult.collectAsState()
    val eliminaransiedadResult by viewModel.eliminarAnsiedadResult.collectAsState()

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
                    text = "Editar Síntoma",
                    color = MyColorPalette.SintomasF,
                    textcolor = Color.White,
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 17.dp),
                    horizontalArrangement = Arrangement.Start
                ) {
                    IconOnlyButton(onClick = {navController.navigate(route = AppScreens.AnsiedadModificar.route)}, tint = Color.White)
                }
            } //Cierra el box

            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)){
                Spacer(modifier = Modifier.padding(bottom = 20.dp))

                Text(text = "¿Qué síntoma lograste percibir?",
                    style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
                )
                Spacer(modifier = Modifier.padding(bottom = 5.dp))

                SliderWithText(palabras = sintomas, cP = MyColorPalette.SintomasF,
                    cS = MyColorPalette.SintomasC, initialValue = sintoma, sel =sintomaseleccionado)

                Spacer(modifier = Modifier.padding(bottom = 5.dp))

                Text(text = "¿Aproximadamente qué hora empezó el síntoma?",
                    style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
                )
                Spacer(modifier = Modifier.padding(bottom = 5.dp))

                TimePicker(color_button = MyColorPalette.SintomasC, selectedTime = horaseleccionada)
                Spacer(modifier = Modifier.padding(bottom = 5.dp))

                Text(text = "¿Cuál fue la intensidad del síntoma?",
                    style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
                )
                Spacer(modifier = Modifier.padding(bottom = 5.dp))

                SliderWithText(palabras = intensidades, cP = MyColorPalette.SintomasF,
                    cS = MyColorPalette.SintomasC, initialValue = intensidad, sel = intensidadeleccionado)

                Spacer(modifier = Modifier.padding(bottom = 20.dp))

                Text(text = "¿Quieres dejar alguna nota?",
                    style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
                )

                InputField_text(valueState=nota, labelId="",
                    enabled = true, keyboardType= KeyboardType.Text,
                    modifier = Modifier.height(120.dp), isSingleLine = false)



                Spacer(modifier = Modifier.padding(bottom = 20.dp))

                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center) {
                    CustomButton(
                        color = MyColorPalette.SintomasC,
                        colorText = Color.White,
                        buttonText = "Editar Datos",
                        size = 7,
                        onClickAction = {
                            viewModel.editaransiedades(
                                ModificarAnsiedad(hora = horaseleccionada.value, id_ansiedad = id_Ansiedad,
                                    intensidad = intensidadeleccionado.value, sintoma = sintomaseleccionado.value,
                                    nota = nota.value ))
                        }
                    )
                }
                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center) {
                    Button(onClick = { showLogoutDialog.value = true },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent ),) {
                        Text(text = "Eliminar Síntoma",
                            color = MyColorPalette.SintomasC)

                    }
                }


                //Para Editar

                editaransiedadResult?.let { result ->
                    LaunchedEffect(result) {
                        if (result.isSuccess) {
                            viewModel.leertablaAnsiedadSintomas(
                                DataGraph(
                                usuario_id = usuario_id, fecha = obtenerFechaActual())
                            )
                            viewModel.leertablaAnsiedadIntensidades(
                                DataGraph(
                                usuario_id = usuario_id, fecha = obtenerFechaActual())
                            )
                            snackbarHostState.showSnackbar(
                                message = "Se hizo el cambio con éxito",
                                duration = SnackbarDuration.Short,
                                actionLabel = "Continuar"
                            )
                            //Reiniciar Valores
                            viewModel.setIdAnsiedad(0)
                            viewModel.setHora("")
                            viewModel.setNota("")
                            viewModel.setSintomas("")
                            viewModel.setIntensidadAnsiedad("")

                            //Reiniciar Estado
                            viewModel.setEditarAnsiedadResult(null)
                            viewModel.setEditarAnsiedadResult(null)

                            navController.navigate(route = AppScreens.AnsiedadModificar.route)
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
                        title = { Text("¿Quieres elimnar este síntoma?", textAlign = TextAlign.Center)},
                        confirmButton = {
                            Column(modifier = Modifier.fillMaxWidth().height(35.dp),horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center) {
                                TextButton(onClick = {
                                    // Lógica para cerrar sesión
                                    showLogoutDialog.value = false
                                    viewModel.eliminaransiedades(id_Ansiedad)
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


                eliminaransiedadResult?.let { result ->
                    LaunchedEffect(result) {
                        if (result.isSuccess) {
                            viewModel.leertablaAnsiedadSintomas(DataGraph(
                                usuario_id = usuario_id, fecha = obtenerFechaActual()))
                            viewModel.leertablaAnsiedadIntensidades(DataGraph(
                                usuario_id = usuario_id, fecha = obtenerFechaActual()))
                            snackbarHostState.showSnackbar(
                                message = "Se eliminó el síntoma con éxito",
                                duration = SnackbarDuration.Short,
                                actionLabel = "Continuar"
                            )
                            //Reiniciar Valores
                            viewModel.setIdAnsiedad(0)
                            viewModel.setHora("")
                            viewModel.setNota("")
                            viewModel.setSintomas("")
                            viewModel.setIntensidadAnsiedad("")

                            //Reiniciar Estado
                            viewModel.setEliminarAnsiedadResult(null)
                            viewModel.setEditarAnsiedadResult(null)

                            navController.navigate(route = AppScreens.AnsiedadModificar.route)
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