
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
import com.example.doccare_tracker.model.Graphs.Pesos.ModifcarPeso
import com.example.doccare_tracker.model.Graphs.Pesos.PesosGraph
import com.example.ejemplosapis.viewModel.AppViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ModificarPeso(navController: NavHostController, viewModel: AppViewModel) {
    val nuevopeso = remember { mutableStateOf("") }
    val snackbarHostState = remember { SnackbarHostState() }
    var showyDialog = remember { mutableStateOf(false) }
    val usuario by viewModel.usuario_id.collectAsState()
    val modificarResult by viewModel.editarPesoResult.collectAsState()

    Scaffold(modifier = Modifier.fillMaxSize(),
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier.padding(16.dp)
            )
        }) {
        Box{
            BackgroundImageWithText(title = "Agregar Peso", modifier = Modifier
                .padding(top = 100.dp)
                .align(Alignment.TopCenter))
        }

        Column(modifier = Modifier.padding(20.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
                IconOnlyButton(onClick = {
                    navController.navigate(route = AppScreens.GraphsData.route)
                })
            }

            Spacer(modifier = Modifier.padding(bottom = 150.dp))
            Text(text = "Peso:",
                style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold))
            InputField_text(
                valueState = nuevopeso,
                labelId = "Nuevo Peso",
                enabled = true,
                isSingleLine = true,
                keyboardType = KeyboardType.Decimal,)
            Spacer(modifier = Modifier.padding(bottom = 50.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){
                CustomButton(color = MyColorPalette.modificar, colorText = Color.White,
                    buttonText = "Registrar", size = 3,
                    onClickAction = {

                        if(nuevopeso.value != "" ){
                            viewModel.editarPeso(ModifcarPeso(peso = nuevopeso.value.toFloat(), usuario_id = usuario))

                        }else{showyDialog.value = true}

                    })
            }

            if (showyDialog.value) {
                alterdialogs_one(showyDialog, "Para registrar un peso debe ingresar un valor", "Entendido")
            }

            modificarResult?.let { result ->
                LaunchedEffect(result) {
                    if (result.isSuccess) {
                        snackbarHostState.showSnackbar(
                            message = "Se agrego el registro",
                            duration = SnackbarDuration.Short,
                            actionLabel = "Continuar"
                        )
                        viewModel.setEditarPesoResult(null)
                        viewModel.leertablaPesos(PesosGraph(usuario_id = usuario))
                        viewModel.jalarinformacionusuarios2(user = usuario)
                        navController.navigate(route = AppScreens.GraphsData.route)
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