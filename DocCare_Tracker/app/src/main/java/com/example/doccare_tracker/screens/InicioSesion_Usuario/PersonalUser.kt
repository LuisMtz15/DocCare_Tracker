
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.doccare_tracker.model.Registros.Usuario.RegistrarDetalleUsuario
import com.example.ejemplosapis.viewModel.AppViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun screen_personalUser(navController: NavHostController, viewModel: AppViewModel) {

    val altura = remember { mutableStateOf("") }
    val peso = remember { mutableStateOf("") }
    val circunferencias = remember { mutableStateOf("") }
    val selectedGender = remember { mutableStateOf("Masculino") }
    val signupResult by viewModel.registrarUsuarioResult.collectAsState()
    val detalleUsuarioResult by viewModel.registrarDetalleUsuarioResult.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    val clave = remember { mutableStateOf("")}
    val usuario_id = viewModel.usuario_id
    var showyDialog = remember { mutableStateOf(false) }
    var showDialog_cons = remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier.padding(16.dp)
            )
        }
    ) {

        Box{
            BackgroundImageWithText(title = "Información Personal", modifier = Modifier
                .padding(top = 106.dp, start = 32.dp)
                .align(Alignment.TopStart))
        }

        Column(modifier = Modifier.padding(20.dp)) {
            Spacer(modifier = Modifier.padding(bottom = 180.dp))

            LazyColumn {

                item{
                    //La Altura esta en string ¡cuidado!
                    InputField_text(

                        valueState = altura,
                        labelId = "Altura",
                        enabled = true,
                        isSingleLine = true,
                        keyboardType = KeyboardType.Number,)

                    Spacer(modifier = Modifier.padding(bottom = 10.dp))
                }

                item { //La peso esta en string ¡cuidado!
                    InputField_text(

                        valueState = peso,
                        labelId = "Peso",
                        enabled = true,
                        isSingleLine = true,
                        keyboardType = KeyboardType.Number,)

                    Spacer(modifier = Modifier.padding(bottom = 10.dp)) }

                item{ //La circunferencia esta en string ¡cuidado!
                    InputField_text(

                        valueState = circunferencias,
                        labelId = "Circunferencia Abdominal",
                        enabled = true,
                        isSingleLine = true,
                        keyboardType = KeyboardType.Number,)

                    Spacer(modifier = Modifier.padding(bottom = 10.dp))}

                item{
                    GenderSelection(selectedGender=selectedGender,)
                    Spacer(modifier = Modifier.padding(bottom = 10.dp))
                }

                item{
                    Text(text = "Clave Única de Su Doctor", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    Text(text = "Lo podrás registrar más adelante", fontSize = 10.sp, fontWeight = FontWeight.Thin)
                    Spacer(modifier = Modifier.padding(bottom = 10.dp))
                    InputField_text(

                        valueState = clave,
                        labelId = "Clave Única de Su Doctor",
                        enabled = true,
                        isSingleLine = true,
                        keyboardType = KeyboardType.Number,)

                    Spacer(modifier = Modifier.padding(bottom = 30.dp))
                }

                item{ //OnCLick, falta implementar
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){
                        CustomButton(color = MyColorPalette.registros, colorText = Color.White,
                            buttonText = "Continuar", size = 3,
                            onClickAction = {
                                if (altura.value != "" && peso.value != "" && circunferencias.value != "") {
                                    viewModel.registrardetalleUsuario(RegistrarDetalleUsuario(
                                        altura = altura.value.toInt(), circunferencia_abdominal = circunferencias.value.toInt(), sexo = selectedGender.value,
                                        peso = peso.value.toInt(), clave_unica = clave.value, usuario_id = usuario_id.value))
                                }
                                else{showyDialog.value = true}

                            }) }

                }
            }
            if (showyDialog.value){
                alterdialogs_one(showyDialog, "Debes llenar todos los campos requeridos", "Entendido")
            }
            detalleUsuarioResult?.let { result ->
                LaunchedEffect(result) {
                    if (result.isSuccess) {
                        snackbarHostState.showSnackbar(
                            message ="Registro exitoso. Inicia sesión",
                            duration = SnackbarDuration.Short,
                            actionLabel = "Iniciar Sesión"
                        )

                        viewModel.setRegistrarUsuarioResult(result=null)
                        viewModel.setRegistrarDetalleUsuarioResult(result=null)
                        navController.navigate(route = AppScreens.InicioSesion.route)
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