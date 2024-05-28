
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.doccare_tracker.model.Graphs.DataGraph
import com.example.doccare_tracker.model.Presion.AgregarPresion
import com.example.ejemplosapis.viewModel.AppViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Agregar_Presion(navController: NavHostController, viewModel: AppViewModel) {
    //Basicas
    val snackbarHostState = remember { SnackbarHostState() }
    var showyDialog = remember { mutableStateOf(false) }
    val usuario by viewModel.usuario_id.collectAsState()

    //Selecciones
    val horaseleccionada = remember{mutableStateOf("hora")}
    val emocionseleccionada = remember { mutableStateOf("") }
    val pS = remember{mutableStateOf("")}
    val pD = remember{mutableStateOf("")}

    //Tablas

    val emocioneslist by viewModel.emocioneslistpresion.collectAsState()

    val emociones = mutableListOf<String>()


    //Agregar Datos Tablas
    emocioneslist.forEach {emociont ->
        emociones.add(emociont.emocion)
    }


    //Results
    val agregarpresionesResult by viewModel.agregarpresionesResult.collectAsState()

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
                    text = "Agregar Presión arterial",
                    color = MyColorPalette.PresionF,
                    textcolor = Color.White,
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 17.dp),
                    horizontalArrangement = Arrangement.Start
                ) {
                    IconOnlyButton(onClick = {navController.navigate(route = AppScreens.Presion.route)}, tint = Color.White)
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
                    cS = MyColorPalette.PresionC, sel = emocionseleccionada)

                Spacer(modifier = Modifier.padding(bottom = 20.dp))

                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center) {
                    CustomButton(
                        color = MyColorPalette.PresionC,
                        colorText = Color.White,
                        buttonText = "Enviar Datos",
                        size = 7,
                        onClickAction = {
                            if(horaseleccionada.value != "" && pS.value != "" && pD.value != ""){
                                viewModel.agregarpresiones(AgregarPresion(diastolica = pD.value.toInt(),
                                    sistolica = pS.value.toInt(), emocion = emocionseleccionada.value,
                                    hora = horaseleccionada.value, usuario_id =usuario, fecha = obtenerFechaActual()))
                            }else{showyDialog.value = true}

                        }
                    )
                }

                if (showyDialog.value) {
                    alterdialogs_one(showyDialog, "Debes llenar todos los campos para poder agregar un Registro", "Entendido")
                }

                agregarpresionesResult?.let { result ->
                    LaunchedEffect(result) {
                        if (result.isSuccess) {
                            viewModel.leertablaPresionDiastolica(
                                DataGraph(
                                usuario_id = usuario, fecha = obtenerFechaActual())
                            )
                            viewModel.leertablaPresionSistolica(
                                DataGraph(
                                usuario_id = usuario, fecha = obtenerFechaActual())
                            )
                            snackbarHostState.showSnackbar(
                                message = "Se agregó el registro ccorrectamente",
                                duration = SnackbarDuration.Short,
                                actionLabel = "Continuar"
                            )
                            viewModel.setNohayPresiones(false) //Ya hay alimentos
                            viewModel.setAgregarPresionesResult(null)
                            navController.navigate(route = AppScreens.Presion.route)
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