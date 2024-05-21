
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.ejemplosapis.viewModel.AppViewModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "MutableCollectionMutableState")
@Composable
fun Modificar_Sueño(navController: NavHostController, viewModel: AppViewModel) {

    val snackbarHostState = remember { SnackbarHostState() }
    val text = remember { mutableStateOf("No hay registros de sueño") }
    val fecha = remember { mutableStateOf("") }
    val sueñoslist by viewModel.suenolist.collectAsState()
    val sueños_user = remember { mutableStateOf(sueñoslist) }
    val filtrossueños by viewModel.filtrosuenoResult.collectAsState()
    val const = remember {mutableStateOf(true)}


    Scaffold(modifier = Modifier.fillMaxSize(),
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier.padding(16.dp)
            )
        }) {

        Box {
            BackgroundImageWithText(
                title = "Sueños Registrados", modifier = Modifier
                    .padding(top = 96.dp, start = 20.dp)
                    .align(Alignment.TopStart)
            )
        }

        Column(modifier = Modifier.fillMaxSize()){
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp), horizontalArrangement = Arrangement.Start) {
                IconOnlyButton(onClick = {
                    viewModel.setFiltroSueñoResult(null)
                    navController.navigate(route = AppScreens.Sueño.route)})
            }
            Spacer(modifier = Modifier.padding(bottom = 120.dp))
            Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Text(
                    text = "¿De qué día requieres la información?",
                    fontSize = 17.sp, fontWeight = FontWeight.Bold
                )
            }
            Date_Picker(color_button = MyColorPalette.SueñoC, selectedDateText = fecha, viewModel=viewModel, opcion = "Sueño")
            Spacer(modifier = Modifier.padding(bottom = 4.dp))
            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                CustomButton(color = MyColorPalette.SueñoC, colorText = Color.White,
                    buttonText = "Restablecer Valores", size = 8, onClickAction = {viewModel.setFiltroSueñoResult(null)
                        fecha.value = ""
                        text.value = "No hay registros de sueño"
                        const.value = true
                        sueños_user.value = sueñoslist})

            }

            Spacer(modifier = Modifier.padding(bottom = 14.dp))
            Text(text = "*Selecciona una opción a modificar",
                modifier = Modifier.padding(start = 25.dp),)

            if(!viewModel.nohaySuenos.collectAsState().value && const.value){
                Sueño_user(
                    navController = navController, viewModel = viewModel,
                    informacion = sueños_user
                )
            }else{
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically) {
                    Spacer(modifier = Modifier.padding(bottom = 400.dp))
                    Column(horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = text.value,
                            fontSize = 27.sp, fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }

            filtrossueños?.let { result ->
                LaunchedEffect(result) {
                    if (result.isSuccess) {
                        sueños_user.value = viewModel.filtrosuenolist.value
                    } else {
                        snackbarHostState.showSnackbar(
                            message = "Error: ${result.exceptionOrNull()?.message}",
                            duration = SnackbarDuration.Long,
                            actionLabel = "Intentar de nuevo"
                        )
                        text.value = "No hay Registros para esta fecha"
                        const.value = false
                    }
                }
            }


        }
    }
}