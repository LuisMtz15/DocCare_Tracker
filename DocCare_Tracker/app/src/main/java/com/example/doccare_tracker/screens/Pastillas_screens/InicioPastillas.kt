
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.ejemplosapis.viewModel.AppViewModel

@Composable
fun Inicio_Pastillas(navController: NavHostController, viewModel: AppViewModel) {
    val pastillasMedicamento = viewModel.tablaPastillasMedicamentolist.collectAsState()
    val pastillasTiempo = viewModel.tablaPastillasTiempolist.collectAsState()

    val colores: List<Color> = listOf(
        Color(0xFF035D90), // PastillasF
        Color(0xFF4CB2F0), // PastillasC
        Color(0xFF2174B7), // Agrega tres colores más de la misma gama
        Color(0xFF73C4F5),
        Color(0xFFA9DBFA)
    )


    Surface(modifier = Modifier.fillMaxSize()) {


        Box{
            BackgroundImageWithText(title = "Pastillas", modifier = Modifier
                .padding(top = 126.dp, start = 32.dp)
                .align(Alignment.TopStart))
        }

        Column(modifier = Modifier.padding(20.dp)) {

            Box {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
                    IconOnlyButton(onClick = {
                        viewModel.setLeerPastillasResult(null)
                        navController.navigate(route = AppScreens.iniciouser.route)})
                }
            }

            Spacer(modifier = Modifier.padding(bottom = 190.dp))

            if (pastillasTiempo.value != null || pastillasMedicamento.value != null) {
                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically) {
                    Column(modifier = Modifier
                        .weight(1f)
                        .height(100.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center) {
                        DonutChartValues(pastillasMedicamento.value,colores, "Medicamentos", "Pastillas")


                    }
                    Spacer(modifier = Modifier.padding(horizontal = 10.dp))
                    Column(modifier = Modifier
                        .weight(1f)
                        .height(100.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Bottom) {
                        BarChartValues(pastillasTiempo.value, colores, "Número de pastillas por tiempo")
                    }
                }
            } else{
                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically) {
                    Card (colors = CardDefaults.cardColors(Color.Transparent),){
                        Text(text = "No hay datos para las gráficas",
                            fontSize = 20.sp,fontWeight = FontWeight.Bold)
                    }
                }
            }

            Spacer(modifier = Modifier.padding(bottom = 95.dp))

            Column(modifier = Modifier.fillMaxWidth(),verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
                CustomButton(color = MyColorPalette.PastillasC, colorText = Color.White,
                    buttonText = "Modificar Datos"
                    ,size = 6,
                    onClickAction = {navController.navigate(route = AppScreens.PastillasModificar.route)})

                Spacer(modifier = Modifier.padding(bottom = 10.dp))

                CustomButton(color = MyColorPalette.PastillasC, colorText = Color.White,
                    buttonText = "Agregar Datos"
                    ,size = 6,
                    onClickAction = {navController.navigate(route = AppScreens.PastillasAgregar.route)})
            }


        }
    }
}