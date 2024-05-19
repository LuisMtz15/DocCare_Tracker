
import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.doccare_tracker.Constants.Constants
import com.example.doccare_tracker.model.Registros.RegistrarUsuario
import com.example.ejemplosapis.viewModel.AppViewModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Registro_prin(navController: NavHostController, viewModel: AppViewModel) {
    var agreeToPrivacyPolicy by remember { mutableStateOf(false) }
    val correo = remember { mutableStateOf("") }
    val contraseña = remember { mutableStateOf("") }
    val confirmarContraseña = remember { mutableStateOf("") }
    val confirmarcorreo = remember { mutableStateOf("") }
    var showPrivacyPolicyDialog by remember { mutableStateOf(false) }
    val nombre = remember { mutableStateOf("") }
    val apellidop = remember { mutableStateOf("") }
    val apellidom = remember { mutableStateOf("") }
    val fecha = remember { mutableStateOf("") }
    var showyDialog = remember { mutableStateOf(false) }
    var showDialog_cons = remember { mutableStateOf(false) }
    var showDialog_perf = remember { mutableStateOf(false) }
    val isPasswordokey = remember { mutableStateOf(false) }
    var showdialogpass = remember { mutableStateOf(false) }


    val signupResult by viewModel.registrarUsuarioResult.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
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
            BackgroundImageWithText(title = "Registro", modifier = Modifier
                .padding(top = 76.dp, start = 32.dp)
                .align(Alignment.TopStart))
        }
        Column(modifier = Modifier.padding(20.dp)) {


            Spacer(modifier = Modifier.padding(bottom = 130.dp))

            LazyColumn {
                item {
                    InputField_text(

                        valueState = nombre,
                        labelId = "Nombre",
                        enabled = true,
                        isSingleLine = true,
                        keyboardType = KeyboardType.Text)

                    Spacer(modifier = Modifier.padding(bottom = 10.dp))
                }

                item {
                    InputField_text(

                        valueState = apellidop,
                        labelId = "Apellido Paterno",
                        enabled = true,
                        isSingleLine = true,
                        keyboardType = KeyboardType.Text,)
                    Spacer(modifier = Modifier.padding(bottom = 10.dp))
                }

                item{
                    InputField_text(

                        valueState = apellidom,
                        labelId = "Apellido Materno",
                        enabled = true,
                        isSingleLine = true,
                        keyboardType = KeyboardType.Text)

                    Spacer(modifier = Modifier.padding(bottom = 10.dp))
                }

                item{
                    Date_PickerRegister(color_button = MyColorPalette.registros, selectedDateText = fecha,)

                    Spacer(modifier = Modifier.padding(bottom = 10.dp))
                }

                item {
                    InputField_text(
                        valueState = correo,
                        labelId = "Correo Electronico",
                        enabled = true,
                        isSingleLine = true,
                        keyboardType = KeyboardType.Email)

                    Spacer(modifier = Modifier.padding(bottom = 10.dp))
                }
                item {
                    InputField_text(
                        valueState = confirmarcorreo,
                        labelId = "Confirmar Correo Electronico",
                        enabled = true,
                        isSingleLine = true,
                        keyboardType = KeyboardType.Email,)

                    Spacer(modifier = Modifier.padding(bottom = 10.dp))
                }

                item {
                    //La contraseña esta en string ¡cuidado!
                    InputField_text_registro(
                        valueState = contraseña,
                        labelId = "Contraseña",
                        enabled = true,
                        isSingleLine = true,
                        keyboardType = KeyboardType.Password,
                        password = true,
                        isPasswordokey = isPasswordokey)

                    Spacer(modifier = Modifier.padding(bottom = 10.dp))
                }

                item {
                    //La contraseña esta en string ¡cuidado!
                    InputField_text_password(
                        valueState = confirmarContraseña,
                        labelId = "Confirmar Contraseña",
                        enabled = true,
                        isSingleLine = true,
                        keyboardType = KeyboardType.Password,
                        password = true,)

                    Spacer(modifier = Modifier.padding(bottom = 20.dp))
                }

                item {

                    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally){
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Checkbox(
                                checked = agreeToPrivacyPolicy,
                                onCheckedChange = { agreeToPrivacyPolicy = it }
                            )
                            Text("Acepto Aviso de Privacidad")
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = buildAnnotatedString {
                                withStyle(style = SpanStyle(textDecoration = TextDecoration.Underline)) {
                                    append("Ver Aviso de privacidad")
                                }
                            },
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier
                                .clickable { showPrivacyPolicyDialog = true }
                                .padding(start = 15.dp)
                        )
                    }

                }

                item {
                    Spacer(modifier = Modifier.padding(bottom = 30.dp))
                    //OnCLick, falta implementar
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){
                        CustomButton(color = MyColorPalette.registros, colorText = Color.White,
                            buttonText = "Continuar", size = 3,
                            onClickAction = {
                                if (isPasswordokey.value){
                                    if (nombre.value != "" && apellidop.value != "" &&
                                        apellidom.value != "" && fecha.value != "" && correo.value != ""
                                        && confirmarcorreo.value != "" && contraseña.value != "" && confirmarContraseña.value != ""
                                    ) {
                                        if (agreeToPrivacyPolicy) {
                                            if (correo.value == confirmarcorreo.value && contraseña.value == confirmarContraseña.value) {
                                                viewModel.registrarUsuario(
                                                    RegistrarUsuario(
                                                        apellido_materno = apellidom.value,
                                                        apellido_paterno = apellidop.value,
                                                        email = correo.value,
                                                        nombre = nombre.value,
                                                        fecha_nacimiento = fecha.value,
                                                        password = contraseña.value,
                                                        rol = "Usuario"
                                                    )
                                                )
                                            } else {
                                                showDialog_cons.value = true
                                            }// En caso de que las contraseñas o correo no sean iguales
                                        } else {
                                            showyDialog.value = true
                                        } //En caso de no aceptar aviso de privacidad
                                    } else {
                                        showDialog_perf.value = true
                                    }
                            }else{showdialogpass.value = true}


                            })
                    }
                }
            }

            if (showyDialog.value){
                alterdialogs_one(showyDialog, "Debes aceptar el aviso de privacidad", "Entendido")
            }
            if (showdialogpass.value){
                alterdialogs_one(showdialogpass, "Por tu seguridad y la de tus datos, debes cumplir con los requerimintos de la contraseña", "Entendido")
            }
            if (showDialog_cons.value){
                alterdialogs_one(showDialog_cons, "El correo o la contraseña no coinciden", "Entendido")
            }
            if (showDialog_perf.value){
                alterdialogs_one(showDialog_perf, "Debes llenar todos los campos", "Entendido")
            }

            signupResult?.let { result ->
                LaunchedEffect(result) {
                    if (result.isSuccess) {
                        snackbarHostState.showSnackbar(
                            message ="Registro Inicial Completo.",
                            duration = SnackbarDuration.Short
                        )
                        navController.navigate(route = AppScreens.PersonalUser.route)
                    } else {
                        snackbarHostState.showSnackbar(
                            message = "Error: ${result.exceptionOrNull()?.message}",
                            duration = SnackbarDuration.Long,
                            actionLabel = "Reintentar"
                        )
                    }
                }
            }

            if (showPrivacyPolicyDialog) {
                AlertDialog(
                    onDismissRequest = { showPrivacyPolicyDialog = false },
                    title = { Text("Aviso de Privacidad") },
                    text = {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp)
                        ) {
                            item {
                                Text(
                                    text = Constants.avisoDePrivacidad,
                                    style = MaterialTheme.typography.bodySmall
                                )
                            }
                        }
                    },
                    confirmButton = {
                        Button(onClick = { showPrivacyPolicyDialog = false }) {
                            Text("Cerrar")
                        }
                    }
                )
            }

        }
    }
}