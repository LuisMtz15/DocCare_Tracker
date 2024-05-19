
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.doccare_tracker.utils_app.Actividad_ini
import com.example.doccare_tracker.utils_app.Alimentos_ini
import com.example.doccare_tracker.utils_app.ansiedad_ini
import com.example.doccare_tracker.utils_app.pastillas_ini
import com.example.doccare_tracker.utils_app.presion_ini
import com.example.doccare_tracker.utils_app.sueno_ini
import com.example.ejemplosapis.viewModel.AppViewModel


@SuppressLint("StateFlowValueCalledInComposition", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Inicio_usuario(navController: NavHostController, viewModel: AppViewModel) {
    val usuario_id = viewModel.usuario_id.value
    val snackbarHostState = remember { SnackbarHostState() }

    val alimentosResult by viewModel.leerAlimentosResult.collectAsState()
    val actividadResult by viewModel.leerActividadesResult.collectAsState()
    val presionResult by viewModel.leerpresionesResult.collectAsState()
    val ansiedadResult by viewModel.leerAnsiedadResult.collectAsState()
    val sueñoResult by viewModel.leersuenoResult.collectAsState()
    val pastillasResult by viewModel.leerpastillasResult.collectAsState()

    Scaffold(modifier = Modifier.fillMaxSize(),
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier.padding(16.dp)
            )
        })

    {
        Surface(modifier = Modifier.fillMaxSize()) {

            Box {
                BackgroundImageWithText(
                    title = "DocCare Tracker", modifier = Modifier
                        .padding(top = 46.dp, start = 20.dp)
                        .align(Alignment.TopStart)
                )
            }

            Column {
                Spacer(modifier = Modifier.padding(bottom = 90.dp))
                LazyColumn() {
                    item {

                        Alimentos_ini(click = {
                            viewModel.leeralimentos(usuario_id = usuario_id)
                        }
                        )
                        Actividad_ini(click = {
                            viewModel.leeractividades(user = usuario_id)
                        }
                        )
                        ansiedad_ini(click = {
                            viewModel.leeransiedades(user = usuario_id)
                        }
                        )
                        presion_ini(click = {
                            viewModel.leerpresiones(user = usuario_id)
                        }
                        )
                        sueno_ini(click = {
                            viewModel.leersueno(user = usuario_id)
                        }
                        )
                        pastillas_ini(click = {
                            viewModel.leerpastillas(user = usuario_id)
                        }
                        )
                    }

                    item { Spacer(modifier = Modifier.padding(bottom = 60.dp)) }
                }

                //Alimentos
                alimentosResult?.let { result ->
                    LaunchedEffect(result) {
                        if (result.isSuccess) {
                            viewModel.setLeerAlimentosResult(null)
                            navController.navigate(route = AppScreens.Alimentos.route)

                        } else {
                            snackbarHostState.showSnackbar(
                                message = "No hay datos disponibles",
                                duration = SnackbarDuration.Short
                            )
                            viewModel.setNohayAlimentos(true)
                            navController.navigate(route = AppScreens.Alimentos.route)
                        }
                    }
                }
                //Actividad
                actividadResult?.let { result ->
                    LaunchedEffect(result) {
                        if (result.isSuccess) {
                            viewModel.setLeerActividadesResult(null)
                            navController.navigate(route = AppScreens.Actividad.route)

                        } else {
                            snackbarHostState.showSnackbar(
                                message = "No hay datos disponibles",
                                duration = SnackbarDuration.Short
                            )
                            viewModel.setNohayActividades(true)
                            navController.navigate(route = AppScreens.Actividad.route)
                        }
                    }
                }
                //Ansiedad
                ansiedadResult?.let { result ->
                    LaunchedEffect(result) {
                        if (result.isSuccess) {
                            viewModel.setLeerAnsiedadResult(null)
                            navController.navigate(route = AppScreens.Ansiedad.route)

                        } else {
                            snackbarHostState.showSnackbar(
                                message = "No hay datos disponibles",
                                duration = SnackbarDuration.Short
                            )
                            viewModel.setNohayAnsiedades(true)
                            navController.navigate(route = AppScreens.Ansiedad.route)
                        }
                    }
                }
                //Presion
                presionResult?.let { result ->
                    LaunchedEffect(result) {
                        if (result.isSuccess) {
                            viewModel.setLeerPresionesResult(null)
                            navController.navigate(route = AppScreens.Presion.route)

                        } else {
                            snackbarHostState.showSnackbar(
                                message = "No hay datos disponibles",
                                duration = SnackbarDuration.Short
                            )
                            viewModel.setNohayPresiones(true)
                            navController.navigate(route = AppScreens.Presion.route)
                        }
                    }
                }
                //Sueño
                sueñoResult?.let { result ->
                    LaunchedEffect(result) {
                        if (result.isSuccess) {
                            viewModel.setLeerSueñoResult(null)
                            navController.navigate(route = AppScreens.Sueño.route)

                        } else {
                            snackbarHostState.showSnackbar(
                                message = "No hay datos disponibles",
                                duration = SnackbarDuration.Short
                            )
                            viewModel.setNohaySuenos(true)
                            navController.navigate(route = AppScreens.Sueño.route)
                        }
                    }
                }
                //Pastillas
                pastillasResult?.let { result ->
                    LaunchedEffect(result) {
                        if (result.isSuccess) {
                            viewModel.setLeerPastillasResult(null)
                            navController.navigate(route = AppScreens.Pastillas.route)

                        } else {
                            snackbarHostState.showSnackbar(
                                message = "No hay datos disponibles",
                                duration = SnackbarDuration.Short
                            )
                            viewModel.setNohayPastillas(true)
                            navController.navigate(route = AppScreens.Pastillas.route)
                        }
                    }
                }
            }
            BarraNavegacion(navController, viewModel)
        }
    }
}
