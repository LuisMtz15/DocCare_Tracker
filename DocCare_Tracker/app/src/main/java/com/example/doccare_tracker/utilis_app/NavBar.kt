
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.AutoGraph
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.doccare_tracker.model.Graphs.Pesos.PesosGraph
import com.example.ejemplosapis.viewModel.AppViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarraNavegacion(navController: NavHostController, viewModel: AppViewModel) {
    var showMenu by remember { mutableStateOf(false) }
    var showDropDownMenu by remember { mutableStateOf(false) }
    val showLogoutDialog = remember { mutableStateOf(false) }
    val usuario by viewModel.usuario_id.collectAsState()
    val inforespuesta by viewModel.leerUsuarioResult.collectAsState()
    var showyDialog = remember { mutableStateOf(false) }

    val pesosrespuesta by viewModel.tablaUsuarioPesos.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        TopAppBar(
            title = {},
            modifier = Modifier.padding(horizontal = 16.dp),
            actions = {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    IconButton(onClick = { showDropDownMenu = true }) {
                        Icon(Icons.AutoMirrored.Filled.List, contentDescription = "Opciones")
                    }
                    IconButton(onClick = {
                        viewModel.jalarinformacionusuarios(usuario)

                    }) {
                        Icon(Icons.Default.Person, contentDescription = "Usuario")
                    }
                    IconButton(onClick = { navController.navigate(route = AppScreens.iniciouser.route)}) { // Cambiar el estado al hacer clic en "Salida"
                        Icon(Icons.Default.DateRange, contentDescription = "Inicio")
                    }
                    IconButton(onClick = {
                        viewModel.jalarinformacionusuarios2(usuario)
                        viewModel.leertablaPesos(PesosGraph(usuario_id = usuario))

                    }) { // Cambiar el estado al hacer clic en "Salida"
                        Icon(Icons.Default.AutoGraph, contentDescription = "Datos")
                    }
                }
            }
        )
        DropdownMenu(
            expanded = showDropDownMenu,
            onDismissRequest = { showDropDownMenu = false },
            modifier = Modifier.align(Alignment.TopEnd) // Para alinear el DropdownMenu arriba y a la derecha
        ) {
            MiniMenu(onClose = { showDropDownMenu = false }, salida = showLogoutDialog, navController, viewModel)
        }

        inforespuesta?.let { result ->
            LaunchedEffect(result) {
                if (result.isSuccess) {

                    navController.navigate(route = AppScreens.InfoPersonalUser.route)
                } else {
                    showyDialog.value = true
                }
            }
        }

        pesosrespuesta?.let { result ->
            LaunchedEffect(result) {
                if (result.isSuccess) {

                    navController.navigate(route = AppScreens.GraphsData.route)
                } else {
                    showyDialog.value = true
                }
            }
        }

    }

    if (showyDialog.value) {
        alterdialogs_one(showyDialog, "Hubo un problema con tus datos", "Reintentar")
    }

    // AlertDialog para confirmar la salida
    if (showLogoutDialog.value) {
        AlertDialog(
            modifier = Modifier.fillMaxWidth(),
            onDismissRequest = { showLogoutDialog.value = false },
            title = { Text("¿Quieres cerrar sesión?", textAlign = TextAlign.Center)},
            confirmButton = {
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .height(35.dp),horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center) {
                    TextButton(onClick = {
                        // Lógica para cerrar sesión
                        showLogoutDialog.value = false
                        viewModel.cerrarSesion()
                        navController.navigate(route = AppScreens.InicioSesion.route)
                    }) {
                        Text("Cerrar Sesión")
                    }
                }


            },
            dismissButton = {
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .height(35.dp),horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center) {
                    TextButton(onClick = {
                        // Lógica para cancelar
                        showLogoutDialog.value = false
                    }) {
                        Text("Cancelar")
                    }
                }

            }
        )
    }
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun MiniMenu(
    onClose: () -> Unit,
    salida: MutableState<Boolean>,
    navController: NavHostController,
    viewModel: AppViewModel,) {
    val context = LocalContext.current
    val usuario by viewModel.usuario_id.collectAsState()
    val exitodatos by viewModel.exitodatos.collectAsState()


    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
        ) {
            TextButton(
                onClick = {

                    viewModel.leertodoslosdatos(usuario)
                    onClose()


                },
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text("Resumen")
            }
            HorizontalDivider(thickness = 1.dp, color = Color.LightGray)
            TextButton(
                onClick = {
                    onClose()
                    salida.value = true
                },
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text("Salida")
            }
        }
    }

    if (exitodatos) {
        navController.navigate(route = AppScreens.resumenUser.route)
    }
}

