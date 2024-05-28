
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
import com.example.doccare_tracker.model.Ansiedad.AgregarAnsiedad
import com.example.doccare_tracker.model.Graphs.DataGraph
import com.example.ejemplosapis.viewModel.AppViewModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Agregar_Ansiedad(navController: NavHostController, viewModel: AppViewModel) {

    //Basicas
    val snackbarHostState = remember { SnackbarHostState() }
    var showyDialog = remember { mutableStateOf(false) }
    val usuario by viewModel.usuario_id.collectAsState()



    //Selecciones
    val sintomaseleccionado = remember { mutableStateOf("") }
    val horaseleccionada = remember{mutableStateOf("")}
    val intensidadeleccionado = remember { mutableStateOf("") }
    val nota = remember{mutableStateOf("")}

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
    val agregarAnsiedadResult by viewModel.agregarAnsiedadResult.collectAsState()

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
                    text = "Agregar Síntoma",
                    color = MyColorPalette.SintomasF,
                    textcolor = Color.White,
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 17.dp),
                    horizontalArrangement = Arrangement.Start
                ) {
                    IconOnlyButton(onClick = {navController.navigate(route = AppScreens.Ansiedad.route)}, tint = Color.White)
                }
            } //Cierra el box

            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)){
                Spacer(modifier = Modifier.padding(bottom = 30.dp))

                Text(text = "¿Qué síntoma lograste percibir?",
                    style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
                )
                Spacer(modifier = Modifier.padding(bottom = 10.dp))

                SliderWithText(palabras = sintomas, cP = MyColorPalette.SintomasF,
                    cS = MyColorPalette.SintomasC, sel = sintomaseleccionado)

                Spacer(modifier = Modifier.padding(bottom = 20.dp))

                Text(text = "¿Aproximadamente qué hora empezó el síntoma?",
                    style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
                )
                Spacer(modifier = Modifier.padding(bottom = 10.dp))

                TimePicker(color_button = MyColorPalette.SintomasC, selectedTime = horaseleccionada)
                Spacer(modifier = Modifier.padding(bottom = 20.dp))

                Text(text = "¿Cuál fue la intensidad del síntoma?",
                    style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
                )
                Spacer(modifier = Modifier.padding(bottom = 10.dp))

                SliderWithText(palabras = intensidades, cP = MyColorPalette.SintomasF,
                    cS = MyColorPalette.SintomasC, sel = intensidadeleccionado)

                Spacer(modifier = Modifier.padding(bottom = 20.dp))

                Text(text = "¿Quieres dejar alguna nota?",
                    style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
                )

                InputField_text(valueState=nota, labelId="Nota opcional",
                    enabled = true, keyboardType= KeyboardType.Text,
                    modifier = Modifier.height(120.dp), isSingleLine = false)



                Spacer(modifier = Modifier.padding(bottom = 20.dp))

                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center) {
                    CustomButton(
                        color = MyColorPalette.SintomasC,
                        colorText = Color.White,
                        buttonText = "Enviar Datos",
                        size = 7,
                        onClickAction = {

                            if(sintomaseleccionado.value != "" || horaseleccionada.value != "" ||
                                intensidadeleccionado.value != ""){

                                viewModel.agregaransiedades(AgregarAnsiedad(fecha = obtenerFechaActual(),hora= horaseleccionada.value,
                                    sintoma = sintomaseleccionado.value, intensidad = intensidadeleccionado.value,
                                    usuario_id = usuario, nota = nota.value))
                            }else{showyDialog.value = true}

                        }
                    )
                }

                if (showyDialog.value) {
                    alterdialogs_one(showyDialog, "Debes llenar todos los campos para poder agregar una Síntoma", "Entendido")
                }

                agregarAnsiedadResult?.let { result ->
                    LaunchedEffect(result) {
                        if (result.isSuccess) {
                            viewModel.leertablaAnsiedadSintomas(
                                DataGraph(
                                usuario_id = usuario, fecha = obtenerFechaActual())
                            )
                            viewModel.leertablaAnsiedadIntensidades(
                                DataGraph(
                                usuario_id = usuario, fecha = obtenerFechaActual())
                            )
                            snackbarHostState.showSnackbar(
                                message = "Se agregó el síntoma ccorrectamente",
                                duration = SnackbarDuration.Short,
                                actionLabel = "Continuar"
                            )
                            viewModel.setNohayAnsiedades(false) //Ya hay alimentos
                            viewModel.setAgregarAnsiedadResult(null)
                            navController.navigate(route = AppScreens.Ansiedad.route)
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