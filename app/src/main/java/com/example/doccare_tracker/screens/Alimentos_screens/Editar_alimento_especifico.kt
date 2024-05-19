
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
import com.example.doccare_tracker.model.Alimentos.EditarAlimento
import com.example.ejemplosapis.viewModel.AppViewModel


@SuppressLint("StateFlowValueCalledInComposition", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Editar_Alimentos_Especifico(navController: NavHostController, viewModel: AppViewModel) {
    val showLogoutDialog = remember { mutableStateOf(false) }

    val nombrecomida by viewModel.nombreAlimento.collectAsState()
    val comida = remember { mutableStateOf(nombrecomida) }

    val porcion by viewModel.porcionAlimento.collectAsState()
    val porcionseleccionada = remember { mutableStateOf("") }

    val tamaños = mutableListOf<String>()
    val porciones by viewModel.porcioneslist.collectAsState()


    val editaralimento by viewModel.editarAlimentosResult.collectAsState()
    val eliminaralimento by viewModel.eliminarAlimentosResult.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    porciones.forEach {porcion ->
        tamaños.add(porcion.tipo)
    }
    val id by viewModel.idAlimento.collectAsState()

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
                    text = "Editar un Alimento",
                    color = MyColorPalette.AlimentosF,
                    textcolor = Color.White,
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 17.dp),
                    horizontalArrangement = Arrangement.Start
                ) {
                    IconOnlyButton(onClick = {navController.navigate(route = AppScreens.AlimentosModificar.route)}, tint = Color.White)
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

                InputField_text(valueState=comida, labelId="",
                    enabled = true, keyboardType= KeyboardType.Text)

                Spacer(modifier = Modifier.padding(bottom = 10.dp))

                Text(text = "¿De qué tamaño fue tu porción?",
                    style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
                )
                SliderWithText(palabras = tamaños, cP = MyColorPalette.AlimentosF,
                    cS = MyColorPalette.AlimentosC, initialValue = porcion, sel = porcionseleccionada)

                Spacer(modifier = Modifier.padding(bottom = 20.dp))

                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center) {
                    CustomButton(
                        color = MyColorPalette.AlimentosC,
                        colorText = Color.White,
                        buttonText = "Editar Dato",
                        size = 7,
                        //Borrar user id y datos de alimentos
                        onClickAction = {
                            viewModel.editaralimentos(EditarAlimento(id_alimento = id,
                                nombre = comida.value, tipo_porcion = porcionseleccionada.value))
                        }
                    )
                }

                //Para Editar

                editaralimento?.let { result ->
                    LaunchedEffect(result) {
                        if (result.isSuccess) {
                            snackbarHostState.showSnackbar(
                                message = "Se hizo el cambio con éxito",
                                duration = SnackbarDuration.Short,
                                actionLabel = "Continuar"
                            )
                            viewModel.setIdAlimento(0)
                            viewModel.setNombreAlimento("")
                            viewModel.setPorcionAlimento("")
                            viewModel.setEditarAlimentosResult(null)
                            viewModel.setFiltroAlimentosResult(null)
                            navController.navigate(route = AppScreens.AlimentosModificar.route)
                        } else {
                            snackbarHostState.showSnackbar(
                                message = "Error: ${result.exceptionOrNull()?.message}",
                                duration = SnackbarDuration.Long,
                                actionLabel = "Reintentar"
                            )
                        }
                    }
                }

                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center) {
                    Button(onClick = { showLogoutDialog.value = true },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent ),) {
                        Text(text = "Eliminar Alimento",
                            color = MyColorPalette.AlimentosC)

                    }
                }

                // AlertDialog para confirmar la salida
                if (showLogoutDialog.value) {
                    AlertDialog(
                        onDismissRequest = { showLogoutDialog.value = false },
                        title = { Text("¿Quieres elimnar este alimento?", textAlign = TextAlign.Center)},
                        confirmButton = {
                            Column(modifier = Modifier
                                .fillMaxWidth()
                                .height(35.dp),horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center) {
                                TextButton(onClick = {
                                    // Lógica para cerrar sesión
                                    showLogoutDialog.value = false
                                    viewModel.eliminaralimentos(id)
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

                eliminaralimento?.let { result ->
                    LaunchedEffect(result) {
                        if (result.isSuccess) {
                            snackbarHostState.showSnackbar(
                                message = "Se eliminó el alimento con éxito",
                                duration = SnackbarDuration.Short,
                                actionLabel = "Continuar"
                            )
                            viewModel.setIdAlimento(0)
                            viewModel.setNombreAlimento("")
                            viewModel.setPorcionAlimento("")
                            viewModel.setFiltroAlimentosResult(null)
                            viewModel.setEliminarAlimentosResult(null)
                            navController.navigate(route = AppScreens.AlimentosModificar.route)
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