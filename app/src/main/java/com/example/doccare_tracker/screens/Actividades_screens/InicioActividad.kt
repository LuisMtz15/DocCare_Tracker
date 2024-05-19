
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.ejemplosapis.viewModel.AppViewModel

@Composable
fun Inicio_Actividad(navController: NavHostController, viewModel: AppViewModel) {
    val actividadeslist by viewModel.actividadeslist.collectAsState() // Para las gráficas


    Surface(modifier = Modifier.fillMaxSize()) {


        Box{
            BackgroundImageWithText(title = "Actividades", modifier = Modifier
                .padding(top = 126.dp, start = 32.dp)
                .align(Alignment.TopStart))
        }

        Column(modifier = Modifier.padding(20.dp)) {

            Box {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
                    IconOnlyButton(onClick = {
                        viewModel.setLeerActividadesResult(null)
                        navController.navigate(route = AppScreens.iniciouser.route)
                    })
                }
            }

            Spacer(modifier = Modifier.padding(bottom = 155.dp))

            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically) {
                Column(modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center) {
                    Text(text = "Espacio para las gráficas",
                        modifier = Modifier.align(Alignment.CenterHorizontally))
                    Text(text = "Espacio para las gráficas",
                        modifier = Modifier.align(Alignment.CenterHorizontally))
                }
                Column(modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center) {
                    Text(text = "Espacio para las gráficas",
                        modifier = Modifier.align(Alignment.CenterHorizontally))
                    Text(text = "Espacio para las gráficas",
                        modifier = Modifier.align(Alignment.CenterHorizontally))
                }
            }

            Spacer(modifier = Modifier.padding(bottom = 95.dp))

            Column(modifier = Modifier.fillMaxWidth(),verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
                CustomButton(color = MyColorPalette.ActividadC, colorText = Color.White,
                    buttonText = "Modificar Datos"
                    ,size = 6, onClickAction = {navController.navigate(route = AppScreens.ActividadModificar.route)} )

                Spacer(modifier = Modifier.padding(bottom = 10.dp))

                CustomButton(color = MyColorPalette.ActividadC, colorText = Color.White,
                    buttonText = "Agregar Datos"
                    ,size = 6, onClickAction = {navController.navigate(route = AppScreens.ActividadAgregar.route)})
            }


        }
    }
}