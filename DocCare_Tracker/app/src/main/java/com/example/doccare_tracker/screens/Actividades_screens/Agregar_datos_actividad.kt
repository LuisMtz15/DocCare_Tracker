
import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.doccare_tracker.model.Actividad.AgregarActividad
import com.example.doccare_tracker.model.Graphs.DataGraph
import com.example.ejemplosapis.viewModel.AppViewModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Agregar_Actividad(navController: NavHostController, viewModel: AppViewModel) {
    //Basicos
    val snackbarHostState = remember { SnackbarHostState() }
    var showyDialog = remember { mutableStateOf(false) }
    val usuario by viewModel.usuario_id.collectAsState()

    //Selecciones
    val duracionSeleccionada = remember { mutableStateOf(0) }
    val intensidadSeleccionada = remember { mutableStateOf("") }
    val tipoSeleccionado = remember { mutableStateOf("") }
    val emocionSeleccionada = remember { mutableStateOf("") }

    //Tablas

    val emocioneslist by viewModel.emocioneslist.collectAsState()
    val intensidadlist by viewModel.intensidadeslist.collectAsState()
    val tipolist by viewModel.tipoActividadeslist.collectAsState()
    val emociones = mutableListOf<String>()
    val intensidades = mutableListOf<String>()
    val tipos = mutableListOf<String>()

    //Agregar Datos Tablas
    emocioneslist.forEach {emocion ->
        emociones.add(emocion.emocion)
    }

    intensidadlist.forEach {intensidad ->
        intensidades.add(intensidad.intensidad)
    }

    tipolist.forEach {tipo ->
        tipos.add(tipo.tipo)
    }

    //Respuesta
    val agregarrespuesta by viewModel.agregarActividadesResult.collectAsState()

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
                    text = "Agregar una Actividad",
                    color = MyColorPalette.ActividadF,
                    textcolor = Color.White,
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 17.dp),
                    horizontalArrangement = Arrangement.Start
                ) {
                    IconOnlyButton(onClick = {navController.navigate(route = AppScreens.Actividad.route)}, tint = Color.White)
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
                Text(
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(textDecoration = TextDecoration.Underline)) {
                            append("¿Dudas?")
                        }
                    },
                    color = MyColorPalette.ActividadF,
                    modifier = Modifier
                        .clickable { navController.navigate(route = AppScreens.DudasActividad.route)}

                )
                Spacer(modifier = Modifier.padding(bottom = 5.dp))

                SliderWithText(palabras = tipos, cP = MyColorPalette.ActividadF,
                    cS = MyColorPalette.ActividadC, sel = tipoSeleccionado)

                Spacer(modifier = Modifier.padding(bottom = 10.dp))

                Text(text = "¿Cuál fue la intensidad de la actividad?",
                    style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
                )

                SliderWithText(palabras = intensidades, cP = MyColorPalette.ActividadF,
                    cS = MyColorPalette.ActividadC, sel = intensidadSeleccionada)

                Spacer(modifier = Modifier.padding(bottom = 10.dp))

                Text(text = "¿Cuánto duró tu actividad?",
                    style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
                )
                SliderWithText_minutos(cP = MyColorPalette.ActividadF,
                    cS = MyColorPalette.ActividadC, sel = duracionSeleccionada)

                Spacer(modifier = Modifier.padding(bottom = 10.dp))

                Text(text = "¿Cómo te sentiste al realizar esta actividad?",
                    style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
                )

                SliderWithText(palabras = emociones, cP = MyColorPalette.ActividadF,
                    cS = MyColorPalette.ActividadC, sel = emocionSeleccionada)

                Spacer(modifier = Modifier.padding(bottom = 20.dp))

                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center) {
                    CustomButton(
                        color = MyColorPalette.ActividadC,
                        colorText = Color.White,
                        buttonText = "Enviar Datos",
                        size = 7,
                        onClickAction = {
                            if(duracionSeleccionada.value != 0 || intensidadSeleccionada.value != "" ||
                                tipoSeleccionado.value != "" || emocionSeleccionada.value != ""){


                                viewModel.agregaractividades(
                                    AgregarActividad(duracion = duracionSeleccionada.value,
                                    intensidad_actividad = intensidadSeleccionada.value, tipo_actividad = tipoSeleccionado.value,
                                    emocion_actividad = emocionSeleccionada.value, usuario_id = usuario, fecha = obtenerFechaActual())
                                )

                            }else{showyDialog.value = false}

                        }
                    )
                }

            }

            if (showyDialog.value) {
                alterdialogs_one(showyDialog, "Debes llenar todos los campos para poder agregar una Actividad", "Entendido")
            }

            agregarrespuesta?.let { result ->
                LaunchedEffect(result) {
                    if (result.isSuccess) {
                        viewModel.leertablaActividadesTipos(
                            DataGraph(
                            usuario_id = usuario, fecha = obtenerFechaActual())
                        )
                        viewModel.leertablaActividadesIntensidades(
                            DataGraph(
                            usuario_id = usuario, fecha = obtenerFechaActual())
                        )
                        snackbarHostState.showSnackbar(
                            message = "Se agregó la actividad ccorrectamente",
                            duration = SnackbarDuration.Short,
                            actionLabel = "Continuar"
                        )
                        viewModel.setNohayActividades(false) //Ya hay alimentos
                        viewModel.setAgregarActividadesResult(null)
                        navController.navigate(route = AppScreens.Actividad.route)
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
