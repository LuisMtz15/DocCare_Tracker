
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
import com.example.doccare_tracker.model.Informacion_Personal.Contraseñas.ModificarContraseña
import com.example.ejemplosapis.viewModel.AppViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ContraseñaDoc(navController: NavHostController, viewModel: AppViewModel) {
    val contraseñaanterior = remember { mutableStateOf("") }
    val confirmarsenha = remember { mutableStateOf("") }
    val nuevacontraseña = remember { mutableStateOf("") }
    val snackbarHostState = remember { SnackbarHostState() }
    var showyDialog = remember { mutableStateOf(false) }
    var showyDialogy = remember { mutableStateOf(false) }
    val usuario by viewModel.usuario_id.collectAsState()

    val modificarResult by viewModel.editarPasswordResult.collectAsState()

    Scaffold(modifier = Modifier.fillMaxSize(),
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier.padding(16.dp)
            )
        }) {
        Box{
            BackgroundImageWithText(title = "Modificar Contraseña", modifier = Modifier
                .padding(top = 100.dp)
                .align(Alignment.TopCenter))
        }

        Column(modifier = Modifier.padding(20.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
                IconOnlyButton(onClick = {
                    navController.navigate(route = AppScreens.InfoPersonalUser.route)
                })
            }

            Spacer(modifier = Modifier.padding(bottom = 150.dp))
            Text(text = "Contraseña Anterior",
                style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold))
            InputField_text_password(
                valueState = contraseñaanterior,
                labelId = "",
                enabled = true,
                isSingleLine = true,
                keyboardType = KeyboardType.Password,)

            Spacer(modifier = Modifier.padding(bottom = 10.dp))
            Text(text = "Nueva Contraseña",
                style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold))
            InputField_text_password(
                valueState = nuevacontraseña,
                labelId = "",
                enabled = true,
                isSingleLine = true,
                keyboardType = KeyboardType.Password,)

            Spacer(modifier = Modifier.padding(bottom = 10.dp))
            Text(text = "Confirmar Contraseña",
                style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold))
            InputField_text_password(
                valueState = confirmarsenha,
                labelId = "",
                enabled = true,
                isSingleLine = true,
                keyboardType = KeyboardType.Password,)

            Spacer(modifier = Modifier.padding(bottom = 20.dp))


            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){
                CustomButton(color = MyColorPalette.modificar, colorText = Color.White,
                    buttonText = "Cambiar", size = 3,
                    onClickAction = {

                        if(contraseñaanterior.value != "" && nuevacontraseña.value !=""
                            && confirmarsenha.value != ""){
                            if (nuevacontraseña.value == confirmarsenha.value){
                                viewModel.editarPassword(
                                    ModificarContraseña(
                                        Oldpassword = contraseñaanterior.value,
                                        password = nuevacontraseña.value, usuario_id = usuario )
                                )
                            } else{showyDialogy.value = true}
                        }else{showyDialog.value = true}

                    })
            }

            if (showyDialog.value) {
                alterdialogs_one(showyDialog, "Debes llenar todos los campos para modificar la contraseña", "Entendido")
            }
            if (showyDialogy.value) {
                alterdialogs_one(showyDialogy, "Tus constraseñas no coinciden", "Entendido")
            }

            modificarResult?.let { result ->
                LaunchedEffect(result) {
                    if (result.isSuccess) {
                        snackbarHostState.showSnackbar(
                            message = "Se modificó la contraseña",
                            duration = SnackbarDuration.Short,
                            actionLabel = "Continuar"
                        )
                        viewModel.setEditarPasswordResult(null)
                        navController.navigate(route = AppScreens.iniciodoc.route)
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