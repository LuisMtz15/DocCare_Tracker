
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.VpnKey
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.doccare_tracker.model.Login.Login
import com.example.ejemplosapis.viewModel.AppViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun screen_inicio(navController: NavHostController, viewModel: AppViewModel) {
    val user = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val snackbarHostState = remember { SnackbarHostState() }
    var showyDialog = remember { mutableStateOf(false) }
    val loginResult by viewModel.loginResult.collectAsState()
    val usuario_rol = viewModel.role

    Scaffold(modifier = Modifier.fillMaxSize(),
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier.padding(16.dp)
            )
        }) {

        Box{
            BackgroundImageWithText(title = "Iniciar sesión", modifier = Modifier
                .padding(top = 156.dp, start = 32.dp)
                .align(Alignment.TopStart))
        }

        Column(modifier = Modifier.padding(20.dp)) {


            Spacer(modifier = Modifier.padding(bottom = 210.dp))

            InputField_text_wicon(

                valueState = user,
                labelId = "Correo electrónico",
                icon = Icons.Rounded.Person,
                enabled = true,
                isSingleLine = true,
                keyboardType = KeyboardType.Email,)

            Spacer(modifier = Modifier.padding(bottom = 10.dp))

            InputField_text_wicon_password(

                valueState = password,
                labelId = "Password",
                icon = Icons.Rounded.VpnKey,
                enabled = true,
                isSingleLine = true,
                keyboardType = KeyboardType.Password,
                password = true)

            Spacer(modifier = Modifier.padding(bottom = 20.dp))

            //OnCLick, falta implementar el back dependiendo del usuario
            CustomButton(color = MyColorPalette.Registro, colorText = Color.White,
                buttonText = "Iniciar Sesión", size = 4,
                onClickAction = {

                    if(user.value !="" && password.value != ""){
                        viewModel.usuariologin(Login(mail = user.value, password = password.value))
                    }else{ showyDialog.value = true }
                })

            if (showyDialog.value) {
                alterdialogs_one(showyDialog, "Debes llenar todos los campos requeridos", "Entendido")
            }

            //Para Checar si se hizo bien el login
            loginResult?.let { result ->
                LaunchedEffect(result) {
                    if (result.isSuccess) {
                        snackbarHostState.showSnackbar(
                            message ="Login Satisfactorio.",
                            duration = SnackbarDuration.Short,
                            actionLabel = "Aceptar"
                        )
                        if (usuario_rol.value) {
                            navController.navigate(route = AppScreens.iniciouser.route)
                        } else {navController.navigate(route = AppScreens.iniciodoc.route)}

                    } else {
                        snackbarHostState.showSnackbar(
                            message = "Error: Fallo al Iniciar Sesión",
                            duration = SnackbarDuration.Long,
                            actionLabel = "Reintentar"
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.padding(bottom = 10.dp))

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center)
            {
                Text(text = "o")
            }

            Spacer(modifier = Modifier.padding(bottom = 10.dp))

            //OnCLick, falta implementar
            CustomButton(color = MyColorPalette.Registro, colorText = Color.White,
                buttonText = "Crear cuenta como Usuario", size = 4,
                onClickAction = {navController.navigate(route = AppScreens.RegistroUser.route)})

            Spacer(modifier = Modifier.padding(bottom = 10.dp))

            //OnCLick, falta implementar
            CustomButton(color = MyColorPalette.RegistroDoc, colorText = Color.White,
                buttonText = "Crear cuenta como Doctor", size = 4,
                onClickAction = {navController.navigate(route = AppScreens.RegistroDoc.route)})
        }
    }
}