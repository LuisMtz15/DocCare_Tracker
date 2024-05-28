
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
fun Inicio_Sueño(navController: NavHostController, viewModel: AppViewModel) {
    val suenoHoras = viewModel.tablaSuenohoraslist.collectAsState()
    val suenoPastilla = viewModel.tablaSuenopastillaslist.collectAsState()
    val colores: List<Color> = listOf(
        Color(0xFF40A0BB), // SueñoF
        Color(0xFF54BCBD), // SueñoC
        Color(0xFF5FB0C1), // Agrega tres colores más de la misma gama
        Color(0xFF6AC6C3),
        Color(0xFF75DBC5)
    )


    Surface(modifier = Modifier.fillMaxSize()) {


        Box{
            BackgroundImageWithText(title = "Sueño", modifier = Modifier
                .padding(top = 126.dp, start = 32.dp)
                .align(Alignment.TopStart))
        }

        Column(modifier = Modifier.padding(20.dp)) {

            Box {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
                    IconOnlyButton(onClick = {
                        viewModel.setLeerSueñoResult(null)
                        navController.navigate(route = AppScreens.iniciouser.route)})
                }
            }

            Spacer(modifier = Modifier.padding(bottom = 190.dp))

            if (suenoHoras.value != null || suenoPastilla.value != null) {
                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically) {
                    Column(modifier = Modifier
                        .weight(1f)
                        .height(100.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center) {
                        DonutChartValues(suenoHoras.value,colores, "Horas en descanso con más frecuencia")


                    }
                    Spacer(modifier = Modifier.padding(horizontal = 10.dp))
                    Column(modifier = Modifier
                        .weight(1f)
                        .height(100.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Bottom) {
                        BarChartValues(suenoPastilla.value, colores, "Uso de pastillas para dormir")
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
                CustomButton(color = MyColorPalette.SueñoC, colorText = Color.White,
                    buttonText = "Modificar Datos"
                    ,size = 6,
                    onClickAction = {navController.navigate(route = AppScreens.SueñoModificar.route)})

                Spacer(modifier = Modifier.padding(bottom = 10.dp))

                CustomButton(color = MyColorPalette.SueñoC, colorText = Color.White,
                    buttonText = "Agregar Datos"
                    ,size = 6,
                    onClickAction = {navController.navigate(route = AppScreens.SueñoAgregar.route)})
            }


        }
    }
}