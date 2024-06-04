
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.doccare_tracker.model.Graphs.Pesos.PesosGraph
import com.example.ejemplosapis.viewModel.AppViewModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Inicio_doc(navController: NavHostController, viewModel: AppViewModel) {

    //Basicas
    val snackbarHostState = remember { SnackbarHostState() }

    //Usuarios
    val personas by viewModel.usuariosDoclist.collectAsState()
    val exitousuarios by viewModel.exitodatos.collectAsState()
    var cont =0

    fun autoincrement(): Int {
        cont++
        return cont
    }



    Scaffold(modifier = Modifier.fillMaxSize(),
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier.padding(16.dp)
            )
        }
        ){

        Surface(modifier = Modifier.fillMaxSize()) {

            Box {
                BackgroundImageWithText(
                    title = "DocCare Tracker", modifier = Modifier
                        .padding(top = 106.dp, start = 20.dp)
                        .align(Alignment.TopStart)
                )
            }

            Column {

                Spacer(modifier = Modifier.padding(bottom = 180.dp))



                if (personas.isEmpty()) {
                    Spacer(modifier = Modifier.padding(bottom = 100.dp))
                    Row(modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center) {
                    Text(text = "No hay usuarios registrados",
                        fontWeight = FontWeight.Bold, fontSize = 25.sp)}
                }else {
                    Row(modifier = Modifier.padding(start = 22.dp)) {
                        Text(
                            text = "Pacientes Registrados",
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                    Spacer(modifier = Modifier.padding(bottom = 20.dp))
                    Text(text = "*Selecciona el icono para mostrar más datos",
                        modifier = Modifier.padding(start = 20.dp),)
                    Spacer(modifier = Modifier.padding(bottom = 50.dp))
                    Box(modifier = Modifier.padding(start = 40.dp)) {
                        Row() {
                            Box() {
                                Text(text = "Id", style = MaterialTheme.typography.titleMedium)
                            }
                            Spacer(modifier = Modifier.padding(end = 45.dp))
                            Box() {
                                Text(text = "Nombre", style = MaterialTheme.typography.titleMedium)
                            }
                            Spacer(modifier = Modifier.padding(end = 45.dp))
                            Box() {
                                Text(text = "Edad", style = MaterialTheme.typography.titleMedium)
                            }
                            Spacer(modifier = Modifier.padding(end = 20.dp))
                            Box() {
                                Text(text = "Sexo", style = MaterialTheme.typography.titleMedium)
                            }
                        }
                    }
                    //Falta, back por User
                    LazyColumn {
                        items(items = personas) { persona ->
                            Card_view(
                                persona = persona,
                                autoincrementcont = autoincrement(),
                                onClick = {
                                    viewModel.setUser_id(persona.id)
                                    viewModel.setUserNombre(persona.nombre_completo)
                                    viewModel.setUserEdad(persona.edad)
                                    viewModel.setUserSexo(persona.sexo)
                                    viewModel.leertablaPesos(PesosGraph(persona.id))
                                    viewModel.leertodoslosdatos(persona.id)
                                    viewModel.jalarinformacionusuarios2(persona.id)

                                    navController.navigate(route = AppScreens.InfoUsuaroDocSeleccion.route)
                                })
                        }
                        item { Spacer(modifier = Modifier.padding(bottom = 60.dp)) }
                    }
                }

                if (exitousuarios) {
                    navController.navigate(route = AppScreens.InfoUsuaroDocSeleccion.route)
                }
            }
            BarraNavegacionDoc(navController, viewModel)
        }
    }
}