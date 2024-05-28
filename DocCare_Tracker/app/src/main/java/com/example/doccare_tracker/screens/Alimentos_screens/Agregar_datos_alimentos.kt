
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
import com.example.doccare_tracker.model.Alimentos.AgregarAlimentos
import com.example.doccare_tracker.model.Graphs.DataGraph
import com.example.ejemplosapis.viewModel.AppViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

@SuppressLint("StateFlowValueCalledInComposition", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Agregar_Alimentos(navController: NavHostController, viewModel: AppViewModel) {

    val comida = remember { mutableStateOf("") }
    val tamaños = mutableListOf<String>()
    val porcionseleccionada = remember { mutableStateOf("") }
    val porciones by viewModel.porcioneslist.collectAsState()
    val agregarrespuesta by viewModel.agregarAlimentosResult.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    val usuario by viewModel.usuario_id.collectAsState()
    var showyDialog = remember { mutableStateOf(false) }

    porciones.forEach {porcion ->
        tamaños.add(porcion.tipo)
    }
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
                    text = "Agregar un Alimento",
                    color = MyColorPalette.AlimentosF,
                    textcolor = Color.White,
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 17.dp),
                    horizontalArrangement = Arrangement.Start
                ) {
                    IconOnlyButton(onClick = {navController.navigate(route = AppScreens.Alimentos.route)}, tint = Color.White)
                }
            } //Cierra el box

            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)){
                Spacer(modifier = Modifier.padding(bottom = 30.dp))

                Text(text = "¿Qué fue lo que consumiste?",
                    style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
                )
                Spacer(modifier = Modifier.padding(bottom = 10.dp))

                InputField_text(valueState=comida, labelId="Comida consumida",
                    enabled = true, keyboardType= KeyboardType.Text)

                Spacer(modifier = Modifier.padding(bottom = 10.dp))

                Text(text = "¿De qué tamaño fue tu porción?",
                    style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
                )
                SliderWithText(palabras = tamaños, cP = MyColorPalette.AlimentosF,
                    cS = MyColorPalette.AlimentosC, sel=porcionseleccionada)

                Spacer(modifier = Modifier.padding(bottom = 20.dp))

                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center) {
                    CustomButton(
                        color = MyColorPalette.AlimentosC,
                        colorText = Color.White,
                        buttonText = "Enviar Datos",
                        size = 7,
                        onClickAction = {
                            if (comida.value != "" && porcionseleccionada.value != "") {
                                viewModel.agregaralimentos(
                                    AgregarAlimentos(
                                        fecha = obtenerFechaActual(),
                                        nombre = comida.value,
                                        tipo_porcion = porcionseleccionada.value,
                                        usuario_id = usuario
                                    )
                                )
                            }else{showyDialog.value = true}
                        }
                    )
                }

            }
            if (showyDialog.value) {
                alterdialogs_one(showyDialog, "Debes llenar todos los campos para poder agregar un alimento", "Entendido")
            }

            agregarrespuesta?.let { result ->
                LaunchedEffect(result) {
                    if (result.isSuccess) {
                        viewModel.leertablaAlimentosfechas(
                            DataGraph(
                            usuario_id = usuario, fecha = obtenerFechaActual())
                        )
                        viewModel.leertablaAlimentosPorciones(
                            DataGraph(
                            usuario_id = usuario, fecha = obtenerFechaActual())
                        )
                        snackbarHostState.showSnackbar(
                            message = "Se agregó el alimento ccorrectamente",
                            duration = SnackbarDuration.Short,
                            actionLabel = "Continuar"
                        )
                        viewModel.setNohayAlimentos(false) //Ya hay alimentos
                        viewModel.setAgregarAlimentosResult(null)
                        navController.navigate(route = AppScreens.Alimentos.route)
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


fun obtenerFechaActual(): String {
    val dateFormat = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())
    dateFormat.timeZone = TimeZone.getTimeZone("UTC")
    val date = Date()
    return dateFormat.format(date)
}