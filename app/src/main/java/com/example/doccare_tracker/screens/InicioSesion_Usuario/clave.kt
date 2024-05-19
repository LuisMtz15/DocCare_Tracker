
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.ejemplosapis.viewModel.AppViewModel

@Composable
fun clave(navController: NavHostController, viewModel: AppViewModel) {

    Surface(modifier = Modifier.fillMaxSize()) {
        val clave = remember { mutableStateOf("")}


        Box{
            BackgroundImageWithText(title = "¿Cuentas con una clave?", modifier = Modifier
                .padding(top = 156.dp, start = 20.dp)
                .align(Alignment.TopStart))
        }
        Column(modifier = Modifier.padding(20.dp)) {


            Spacer(modifier = Modifier.padding(bottom = 210.dp))

            InputField_text(

                valueState = clave,
                labelId = "Clave Única de Su Doctor",
                enabled = true,
                isSingleLine = true,
                keyboardType = KeyboardType.Number,)

            Spacer(modifier = Modifier.padding(bottom = 30.dp))

            //OnCLick, falta implementar
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){
                CustomButton(color = MyColorPalette.registros, colorText = Color.White,
                    buttonText = "Finalizar", size = 3,
                    onClickAction = {navController.navigate(route = AppScreens.iniciouser.route)})
            }
            Spacer(modifier = Modifier.padding(bottom = 10.dp))
            //OnCLick, falta implementar
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){
                CustomButton(color = MyColorPalette.registros, colorText = Color.White,
                    buttonText = "No Tengo clave", size = 3,
                    onClickAction = {navController.navigate(route = AppScreens.iniciouser.route)})
            }

        }
    }
}