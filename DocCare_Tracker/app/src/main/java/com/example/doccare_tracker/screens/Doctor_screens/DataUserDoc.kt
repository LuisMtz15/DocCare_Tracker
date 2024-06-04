
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
import androidx.compose.material.icons.filled.Person2
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
fun Data_user_doc(navController: NavHostController, viewModel: AppViewModel) {
    val listaAlimentos by viewModel.alimentosResumenlist.collectAsState()
    val listaActividades by viewModel.actividadesResumenlist.collectAsState()
    val listaPresion by viewModel.presionesResumenlist.collectAsState()
    val listaAnsiedad by viewModel.ansiedadResumenlist.collectAsState()
    val listaSueno by viewModel.suenoResumenlist.collectAsState()
    val listaPastillas by viewModel.pastillasResumenlist.collectAsState()
    val pesos by viewModel.tablaUsuarioPesoslist.collectAsState()
    val nombre by viewModel.user_nombre.collectAsState()


    Surface(modifier = Modifier.fillMaxSize()) {

        Box{
            BackgroundImageWithText(title = "", modifier = Modifier
                .padding(top = 156.dp, start = 32.dp)
                .align(Alignment.TopStart))
        }

        Column(modifier = Modifier.padding(20.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
                IconOnlyButton(onClick = {
                    navController.navigate(route = AppScreens.InfoUsuaroDocSeleccion.route)
                })
            }
            Spacer(modifier = Modifier.padding(bottom = 30.dp))
            Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally){
                Image(imageVector = if (viewModel.user_sexo.collectAsState().value == "Masculino"){
                    Icons.Default.Person
                }else{
                    Icons.Default.Person2
                },

                    contentDescription ="Icono de Usuario",
                    modifier = Modifier.size(width = 80.dp, height = 80.dp))
                Text(
                    text = nombre,
                    style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
                )
            }
            Spacer(modifier = Modifier.padding(bottom = 30.dp))

            Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
                PantallaPrincipal(
                    Alimentosinfo = listaAlimentos, Actividadinfo = listaActividades,
                    Ansiedadinfo = listaAnsiedad, Presioninfo = listaPresion, Sueñoinfo = listaSueno,
                    Pastillasinfo = listaPastillas, viewModel=viewModel)

            }


            Spacer(modifier = Modifier.padding(bottom = 30.dp))



        }
    }
}