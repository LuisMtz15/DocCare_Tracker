
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
import com.example.doccare_tracker.model.Actividad.ModificarActividad
import com.example.ejemplosapis.viewModel.AppViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Editar_Actividad_especifica(navController: NavHostController, viewModel: AppViewModel) {
    //Basicas
    val showLogoutDialog = remember { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }

    //Selecciones
    val duracionSeleccionada = remember { mutableStateOf(0) }
    val intensidadSeleccionada = remember { mutableStateOf("") }
    val tipoSeleccionado = remember { mutableStateOf("") }
    val emocionSeleccionada = remember { mutableStateOf("") }



    //Iniciales
    val id_Actividad by viewModel.idActividad.collectAsState()
    val duracion by viewModel.duracionActividad.collectAsState()
    val intensidad by viewModel.intensidadActividad.collectAsState()
    val tipo by viewModel.tipoActividad.collectAsState()
    val emocion by viewModel.emocionActividad.collectAsState()

    //Tablas

    val emocioneslist by viewModel.emocioneslist.collectAsState()
    val intensidadlist by viewModel.intensidadeslist.collectAsState()
    val tipolist by viewModel.tipoActividadeslist.collectAsState()
    val emociones = mutableListOf<String>()
    val intensidades = mutableListOf<String>()
    val tipos = mutableListOf<String>()

    //Agregar Datos Tablas
    emocioneslist.forEach {emociont ->
        emociones.add(emociont.emocion)
    }

    intensidadlist.forEach {intensidadt ->
        intensidades.add(intensidadt.intensidad)
    }

    tipolist.forEach {tipot ->
        tipos.add(tipot.tipo)
    }

    //Results
    val editaractividadResult by viewModel.editarActividadesResult.collectAsState()
    val eliminaractividadResult by viewModel.eliminarActividadesResult.collectAsState()

    Scaffold(modifier = Modifier.fillMaxSize(),
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier.padding(16.dp)
            )
        }){
        Column(modifier = Modifier.fillMaxSize()) {
            Box {
                TitleText(
                    text = "Editar una Actividad",
                    color = MyColorPalette.ActividadF,
                    textcolor = Color.White,
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 17.dp),
                    horizontalArrangement = Arrangement.Start
                ) {
                    IconOnlyButton(onClick = {navController.navigate(route = AppScreens.ActividadModificar.route)}, tint = Color.White)
                }
            } //Cierra el box

            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)){
                Spacer(modifier = Modifier.padding(bottom = 30.dp))

                Text(text = "¿Qué tipo de actividad física realizaste?",
                    style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
                )
                Spacer(modifier = Modifier.padding(bottom = 10.dp))

                SliderWithText(palabras = tipos, cP = MyColorPalette.ActividadF,
                    cS = MyColorPalette.ActividadC, initialValue = tipo, sel = tipoSeleccionado)

                Spacer(modifier = Modifier.padding(bottom = 10.dp))

                Text(text = "¿Cuál fue la intensidad de la actividad?",
                    style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
                )

                SliderWithText(palabras = intensidades, cP = MyColorPalette.ActividadF,
                    cS = MyColorPalette.ActividadC,initialValue = intensidad, sel = intensidadSeleccionada)

                Spacer(modifier = Modifier.padding(bottom = 10.dp))

                Text(text = "¿Cuánto duró tu actividad?",
                    style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
                )
                SliderWithText_minutos(cP = MyColorPalette.ActividadF,
                    cS = MyColorPalette.ActividadC, initialValue=duracion, sel = duracionSeleccionada)

                Spacer(modifier = Modifier.padding(bottom = 10.dp))

                Text(text = "¿Cómo te sentiste al realizar esta actividad?",
                    style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
                )

                SliderWithText(palabras = emociones, cP = MyColorPalette.ActividadF,
                    cS = MyColorPalette.ActividadC,initialValue = emocion, sel = emocionSeleccionada)

                Spacer(modifier = Modifier.padding(bottom = 20.dp))

                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center) {
                    CustomButton(
                        color = MyColorPalette.ActividadC,
                        colorText = Color.White,
                        buttonText = "Editar Datos",
                        size = 7,
                        onClickAction = {
                            viewModel.editaractividades(ModificarActividad(duracion = duracionSeleccionada.value,
                                intensidad_actividad = intensidadSeleccionada.value, tipo_actividad = tipoSeleccionado.value,
                                emocion_actividad = emocionSeleccionada.value, id_actividad = id_Actividad))
                        }
                    )
                }

                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center) {
                    Button(onClick = { showLogoutDialog.value = true },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent ),) {
                        Text(text = "Eliminar Actividad",
                            color = MyColorPalette.ActividadC)

                    }
                }



                //Para Editar

                editaractividadResult?.let { result ->
                    LaunchedEffect(result) {
                        if (result.isSuccess) {
                            snackbarHostState.showSnackbar(
                                message = "Se hizo el cambio con éxito",
                                duration = SnackbarDuration.Short,
                                actionLabel = "Continuar"
                            )
                            //Reiniciar Valores
                            viewModel.setDuracionActividad(0)
                            viewModel.setIntensidadActividad("")
                            viewModel.setTipoActividad("")
                            viewModel.setEmocionActividad("")
                            viewModel.setIdActividad(0)

                            //Reiniciar Estado
                            viewModel.setEditarActividadesResult(null)
                            viewModel.setFiltroActividadesResult(null)


                            navController.navigate(route = AppScreens.ActividadModificar.route)
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
                        title = { Text("¿Quieres elimnar esta actividad?", textAlign = TextAlign.Center)},
                        confirmButton = {
                            Column(modifier = Modifier
                                .fillMaxWidth()
                                .height(35.dp),horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center) {
                                TextButton(onClick = {
                                    // Lógica para cerrar sesión
                                    showLogoutDialog.value = false
                                    viewModel.eliminaractividades(id_Actividad)

                                }) {
                                    Text("Eliminar")
                                }
                            }


                        },
                        dismissButton = {
                            Column(modifier = Modifier
                                .fillMaxWidth()
                                .height(35.dp),horizontalAlignment = Alignment.CenterHorizontally,
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

                eliminaractividadResult?.let { result ->
                    LaunchedEffect(result) {
                        if (result.isSuccess) {
                            snackbarHostState.showSnackbar(
                                message = "Se eliminó la actividad con éxito",
                                duration = SnackbarDuration.Short,
                                actionLabel = "Continuar"
                            )
                            //Reiniciar Valores
                            viewModel.setDuracionActividad(0)
                            viewModel.setIntensidadActividad("")
                            viewModel.setTipoActividad("")
                            viewModel.setEmocionActividad("")
                            viewModel.setIdActividad(0)

                            //Reiniciar Estado

                            viewModel.setEliminarActividadesResult(null)
                            viewModel.setFiltroActividadesResult(null)

                            navController.navigate(route = AppScreens.ActividadModificar.route)
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