
import android.annotation.SuppressLint
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.ejemplosapis.viewModel.AppViewModel


@SuppressLint("StateFlowValueCalledInComposition", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Data_Graphs(navController: NavHostController, viewModel: AppViewModel) {
    val usuario_id = viewModel.usuario_id.value
    val pesos by viewModel.tablaUsuarioPesoslist.collectAsState()
    val dataPeso by viewModel.peso.collectAsState()
    val dataAltura by viewModel.altura.collectAsState()
    val dataCircunferencia by viewModel.circunferencia.collectAsState()

    val pesoDouble = dataPeso?.toFloatOrNull() ?: 0f
    val alturaDouble = dataAltura?.toFloatOrNull() ?: 1f
    val circunferenciaDouble = dataCircunferencia?.toFloatOrNull() ?: 0f

    val IMC = if (alturaDouble != 0f) pesoDouble / (alturaDouble/100 * alturaDouble/100) else 0f
    val RCA = if (alturaDouble != 0f) circunferenciaDouble / (alturaDouble) else 0f


    Surface(modifier = Modifier.fillMaxSize()) {

            Box {
                BackgroundImageWithText(
                    title = "Resumen Datos", modifier = Modifier
                        .padding(top = 86.dp, start = 20.dp)
                        .align(Alignment.TopStart)
                )
                Box {
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp), horizontalArrangement = Arrangement.Start) {
                        IconOnlyButton(onClick = {
                            viewModel.setTablaUsuarioPesos(null)
                            navController.navigate(route = AppScreens.iniciouser.route)
                        }, tint = Color.Black)
                    }
                }

            }

            Column {
                Spacer(modifier = Modifier.padding(bottom = 160.dp))
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "Peso", fontSize = 25.sp, fontWeight = FontWeight.Bold)
                    CustomButton(
                        color = MyColorPalette.Registro,
                        colorText = Color.White,
                        buttonText = "Registro",
                        size = 3,
                        onClickAction = {
                            navController.navigate(route = AppScreens.ModificarPeso.route)
                        }
                    )
                }
                Spacer(modifier = Modifier.padding(bottom = 10.dp))
                pesos?.let {
                    WeightScreen(weights= it, nombre1 = "Actual",
                        nombre2 = "Peso Min",
                        nombre3 = "Peso Max",
                        valueMax= it.maxOrNull(),
                        valueMin= it.minOrNull(),)
                }
                Column(modifier = Modifier.padding(20.dp)) {
                    Text(text = "IMC: ${IMC}", fontSize = 25.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.padding(bottom = 20.dp))
                    Text(text = "RCA: ${RCA}", fontSize = 25.sp, fontWeight = FontWeight.Bold)
                }

            }
        }
}

