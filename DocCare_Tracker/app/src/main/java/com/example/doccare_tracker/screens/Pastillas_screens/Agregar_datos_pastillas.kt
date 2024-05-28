
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.doccare_tracker.model.Graphs.DataGraph
import com.example.doccare_tracker.model.Pastillas.AgregarPastillas
import com.example.ejemplosapis.viewModel.AppViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Agregar_Pastillas(navController: NavHostController, viewModel: AppViewModel) {

    //Basicas
    val snackbarHostState = remember { SnackbarHostState() }
    var showyDialog = remember { mutableStateOf(false) }
    val usuario by viewModel.usuario_id.collectAsState()


    //Selecciones
    val pastilla = remember { mutableStateOf("") }
    val mgs = remember { mutableStateOf("") }
    val peridoseleccion = remember { mutableStateOf("")}
    val tiemposeleccion = remember { mutableStateOf("")}

    //Tablas

    val periodolist by viewModel.periodoslist.collectAsState()

    val periodopastilla = mutableListOf<String>()

    //Agregar Datos Tablas
    periodolist.forEach {periodo ->
        periodopastilla.add(periodo.periodo.toString())
    }

    //Results
    val agregarpastillasResult by viewModel.agregarpastillasResult.collectAsState()

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
                    text = "Agregar una Pastilla",
                    color = MyColorPalette.PastillasF,
                    textcolor = Color.White,
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 17.dp),
                    horizontalArrangement = Arrangement.Start
                ) {
                    IconOnlyButton(onClick = {navController.navigate(route = AppScreens.Pastillas.route)}, tint = Color.White)
                }
            } //Cierra el box

            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)){
                Spacer(modifier = Modifier.padding(bottom = 30.dp))

                Text(text = "¿Qué medicamento tomas?",
                    style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
                )
                Spacer(modifier = Modifier.padding(bottom = 10.dp))

                InputField_text(valueState=pastilla, labelId="Pastilla",
                    enabled = true, keyboardType= KeyboardType.Text)

                Spacer(modifier = Modifier.padding(bottom = 10.dp))

                Text(text = "¿De cuántos mg es el medicamento?",
                    style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
                )
                Spacer(modifier = Modifier.padding(bottom = 10.dp))

                InputField_text(valueState=mgs, labelId="Miligramos",
                    enabled = true, keyboardType= KeyboardType.Number)

                Spacer(modifier = Modifier.padding(bottom = 10.dp))

                Text(text = "¿Cada cuánto debes tomar tu medicamento?",
                    style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
                )
                SliderWithTextTiempo(palabras = periodopastilla, cP = MyColorPalette.PastillasF,
                    cS = MyColorPalette.PastillasC, sel = peridoseleccion)

                Spacer(modifier = Modifier.padding(bottom = 10.dp))

                PeriodoSelection(selected = tiemposeleccion,)


                Spacer(modifier = Modifier.padding(bottom = 20.dp))

                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center) {
                    CustomButton(
                        color = MyColorPalette.PastillasC,
                        colorText = Color.White,
                        buttonText = "Enviar Datos",
                        size = 7,
                        onClickAction = {

                            if(pastilla.value !="" && mgs.value !="" && peridoseleccion.value !="" && tiemposeleccion.value !=""){

                                viewModel.agregarpastillas(AgregarPastillas(
                                    usuario_id = usuario,
                                    nombre = pastilla.value,
                                    dosis = mgs.value,
                                    periodo = peridoseleccion.value.toInt(),
                                    tiempo = tiemposeleccion.value,
                                    fecha = obtenerFechaActual()
                                ))
                            }else{showyDialog.value = true}

                        }
                    )
                }


                if (showyDialog.value) {
                    alterdialogs_one(showyDialog, "Debes llenar todos los campos para poder agregar una Pastilla", "Entendido")
                }

                agregarpastillasResult?.let { result ->
                    LaunchedEffect(result) {
                        if (result.isSuccess) {
                            viewModel.leertablaPastillasTiempo(
                                DataGraph(
                                usuario_id = usuario, fecha = obtenerFechaActual())
                            )
                            viewModel.leertablaPastillasMedicamento(
                                DataGraph(
                                usuario_id = usuario, fecha = obtenerFechaActual())
                            )
                            snackbarHostState.showSnackbar(
                                message = "Se agregó la pastilla ccorrectamente",
                                duration = SnackbarDuration.Short,
                                actionLabel = "Continuar"
                            )
                            viewModel.setNohayPastillas(false) //Ya hay alimentos
                            viewModel.setAgregarPastillasResult(null)
                            navController.navigate(route = AppScreens.Pastillas.route)
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