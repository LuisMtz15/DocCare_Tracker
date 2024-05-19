
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
import com.example.doccare_tracker.model.Pastillas.ModificarPastillas
import com.example.ejemplosapis.viewModel.AppViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Editar_pastilla_especifica(navController: NavHostController, viewModel: AppViewModel) {

    //Basicas
    val showLogoutDialog = remember { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }


    //Iniciales

    val idPastillas by viewModel.idPastilla.collectAsState()
    val nombre by viewModel.nombrepastilla.collectAsState()
    val dosis by viewModel.dosispastilla.collectAsState()
    val tiempo by viewModel.tiempopastilla.collectAsState()
    val periodo by viewModel.periodopastilla.collectAsState()

    //Selecciones
    val pastilla = remember { mutableStateOf(nombre) }
    val mgs = remember { mutableStateOf(dosis) }
    val peridoseleccion = remember { mutableStateOf("")}
    val tiemposeleccion = remember { mutableStateOf(tiempo)}

    //Tablas

    val periodolist by viewModel.periodoslist.collectAsState()

    val periodopastilla = mutableListOf<String>()

    //Agregar Datos Tablas
    periodolist.forEach {periodo ->
        periodopastilla.add(periodo.periodo.toString())
    }

    //Results
    val editarpastillaResult by viewModel.editarpastillasResult.collectAsState()
    val eliminarpastillaResult by viewModel.eliminarpastillasResult.collectAsState()

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
                    text = "Editar una Pastilla",
                    color = MyColorPalette.PastillasF,
                    textcolor = Color.White,
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 17.dp),
                    horizontalArrangement = Arrangement.Start
                ) {
                    IconOnlyButton(onClick = {navController.navigate(route = AppScreens.PastillasModificar.route)}, tint = Color.White)
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

                InputField_text(valueState=pastilla, labelId="",
                    enabled = true, keyboardType= KeyboardType.Text)

                Spacer(modifier = Modifier.padding(bottom = 10.dp))

                Text(text = "¿De cuántos mg es el medicamento?",
                    style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
                )
                Spacer(modifier = Modifier.padding(bottom = 10.dp))

                InputField_text(valueState=mgs, labelId="",
                    enabled = true, keyboardType= KeyboardType.Number)

                Spacer(modifier = Modifier.padding(bottom = 10.dp))

                Text(text = "¿Cada cuánto debes tomar tu medicamento?",
                    style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
                )
                SliderWithTextTiempo(palabras = periodopastilla, cP = MyColorPalette.PastillasF,
                    cS = MyColorPalette.PastillasC, initialValue = periodo.toString(), sel = peridoseleccion) //Este es periodo

                Spacer(modifier = Modifier.padding(bottom = 10.dp))

                PeriodoSelection(selected = tiemposeleccion,) //Este es Tiempo


                Spacer(modifier = Modifier.padding(bottom = 20.dp))

                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center) {
                    CustomButton(
                        color = MyColorPalette.PastillasC,
                        colorText = Color.White,
                        buttonText = "Editar Datos",
                        size = 7,
                        onClickAction = {

                            viewModel.editarpastillas(ModificarPastillas(dosis=mgs.value, id_pastilla = idPastillas,
                                nombre=pastilla.value, periodo=peridoseleccion.value.toInt(), tiempo=tiemposeleccion.value))

                        }
                    )
                }

                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center) {
                    Button(onClick = { showLogoutDialog.value = true },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent ),) {
                        Text(text = "Eliminar Pastilla",
                            color = MyColorPalette.PastillasC)

                    }
                }



                editarpastillaResult?.let { result ->
                    LaunchedEffect(result) {
                        if (result.isSuccess) {
                            snackbarHostState.showSnackbar(
                                message = "Se hizo el cambio con éxito",
                                duration = SnackbarDuration.Short,
                                actionLabel = "Continuar"
                            )
                            //Reiniciar Valores
                            viewModel.setIdPastilla(0)
                            viewModel.setNombrePastilla("")
                            viewModel.setDosisPastilla("")
                            viewModel.setPeriodoPastilla(0)
                            viewModel.setTiempoPastilla("")

                            //Reiniciar Estado
                            viewModel.setEditarPastillasResult(null)
                            viewModel.setFiltroPastillasResult(null)

                            navController.navigate(route = AppScreens.PastillasModificar.route)
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
                        title = { Text("¿Quieres elimnar esta pastilla?", textAlign = TextAlign.Center)},
                        confirmButton = {
                            Column(modifier = Modifier.fillMaxWidth().height(35.dp),horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center) {
                                TextButton(onClick = {
                                    // Lógica para cerrar sesión
                                    viewModel.eliminarpastillas(idPastillas)
                                    showLogoutDialog.value = false

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


                eliminarpastillaResult?.let { result ->
                    LaunchedEffect(result) {
                        if (result.isSuccess) {
                            snackbarHostState.showSnackbar(
                                message = "Se eliminó la pastilla con éxito",
                                duration = SnackbarDuration.Short,
                                actionLabel = "Continuar"
                            )
                            //Reiniciar Valores
                            viewModel.setIdPastilla(0)
                            viewModel.setNombrePastilla("")
                            viewModel.setDosisPastilla("")
                            viewModel.setPeriodoPastilla(0)
                            viewModel.setTiempoPastilla("")

                            //Reiniciar Estado

                            viewModel.setEliminarPastillasResult(null)
                            viewModel.setFiltroPastillasResult(null)

                            navController.navigate(route = AppScreens.PastillasModificar.route)
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