
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.ejemplosapis.viewModel.AppViewModel

@Composable
fun Resumen_usuario(navController: NavHostController, viewModel: AppViewModel) {

    Surface(modifier = Modifier.fillMaxSize()) {

        val listaAlimentos by viewModel.alimentosResumenlist.collectAsState()
        val listaActividades by viewModel.actividadesResumenlist.collectAsState()
        val listaPresion by viewModel.presionesResumenlist.collectAsState()
        val listaAnsiedad by viewModel.ansiedadResumenlist.collectAsState()
        val listaSueno by viewModel.suenoResumenlist.collectAsState()
        val listaPastillas by viewModel.pastillasResumenlist.collectAsState()

        val nombreuser by viewModel.nombreUsuario.collectAsState()
        val apellidouser by viewModel.apellidoUsuario.collectAsState()

        Box{
            BackgroundImageWithText(title = "", modifier = Modifier
                .padding(top = 156.dp, start = 32.dp)
                .align(Alignment.TopStart))
        }

        Column(modifier = Modifier.padding(20.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
                IconOnlyButton(
                    onClick = {
                        viewModel.setExitodatos(false)
                        navController.navigate(route = AppScreens.iniciouser.route)
                    }
                )
            }

            Spacer(modifier = Modifier.padding(bottom = 5.dp))
            Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally){
                Image(imageVector = Icons.Default.Person,
                    contentDescription ="Icono de Usuario",
                    modifier = Modifier.size(width = 150.dp, height = 150.dp))
                Text(
                    text = "$nombreuser $apellidouser",
                    style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
                )
            }
            Spacer(modifier = Modifier.padding(bottom = 30.dp))

            Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
                PantallaPrincipal(
                    Alimentosinfo = listaAlimentos,
                    Actividadinfo = listaActividades,
                    Ansiedadinfo = listaAnsiedad,
                    Sueñoinfo = listaSueno,
                    Pastillasinfo = listaPastillas,
                    Presioninfo = listaPresion,
                    viewModel = viewModel
                )

            }


            Spacer(modifier = Modifier.padding(bottom = 30.dp))



        }
    }
}