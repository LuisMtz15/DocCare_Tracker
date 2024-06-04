
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.ejemplosapis.viewModel.AppViewModel

@Composable
fun Info_usuario_doc(navController: NavHostController, viewModel: AppViewModel) {
    val pesos by viewModel.tablaUsuarioPesoslist.collectAsState()
    val nombre by viewModel.user_nombre.collectAsState()

    val dataPeso by viewModel.peso.collectAsState()
    val dataAltura by viewModel.altura.collectAsState()
    val dataCircunferencia by viewModel.circunferencia.collectAsState()

    val pesoDouble = dataPeso?.toFloatOrNull() ?: 0f
    val alturaDouble = dataAltura?.toFloatOrNull() ?: 1f
    val circunferenciaDouble = dataCircunferencia?.toFloatOrNull() ?: 0f

    val IMC = if (alturaDouble != 0f) pesoDouble / (alturaDouble/100 * alturaDouble/100) else 0f
    val RCA = if (alturaDouble != 0f) circunferenciaDouble / (alturaDouble) else 0f


    Surface(modifier = Modifier.fillMaxSize()) {

        Box{
            BackgroundImageWithText(title = "", modifier = Modifier
                .padding(top = 156.dp, start = 32.dp)
                .align(Alignment.TopStart))
        }

        Column(modifier = Modifier.padding(20.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
                IconOnlyButton(onClick = {
                    viewModel.setExitodatos(false)
                    navController.navigate(route = AppScreens.iniciodoc.route)
                })
            }
            Spacer(modifier = Modifier.padding(bottom = 30.dp))
            Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally){
                Text(
                    text = nombre,
                    style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
                )
            }
            Spacer(modifier = Modifier.padding(bottom = 25.dp))
            Text(text = "Peso", fontSize = 25.sp, fontWeight = FontWeight.Bold)
            pesos?.let {
                WeightScreen2(weights= it, nombre1 = "Actual",
                    nombre2 = "Peso Min",
                    nombre3 = "Peso Max",
                    valueMax= it.maxOrNull(),
                    valueMin= it.minOrNull(),)
            }
            Column(modifier = Modifier.padding(20.dp)) {
                Text(text = "Índice de Masa Corporal:", fontSize = 25.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.padding(bottom = 10.dp))
                IMC_BarraProgreso(imc = IMC)
                Text(text = "Índice de Cintura-Altura:", fontSize = 25.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.padding(bottom = 10.dp))
                ICA_BarraProgreso(ica = RCA)
            }
            Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally){
                CustomButton(
                    color = MyColorPalette.Registro,
                    colorText = Color.White,
                    buttonText = "Ver datos Registrados",
                    size = 4,
                    onClickAction = {
                        navController.navigate(route = AppScreens.DataUserDoc.route)
                    })
            }
            Spacer(modifier = Modifier.padding(bottom = 30.dp))



        }
    }
}