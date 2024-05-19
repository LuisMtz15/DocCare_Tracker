
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.ejemplosapis.viewModel.AppViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarraNavegacionDoc(navController: NavHostController, viewModel: AppViewModel) {
    val showLogoutDialog = remember { mutableStateOf(false) }
    val usuario by viewModel.usuario_id.collectAsState()
    val inforespuesta by viewModel.leerUsuarioResult.collectAsState()
    var showyDialog = remember { mutableStateOf(false) }

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
                    IconButton(onClick = { navController.navigate(route = AppScreens.iniciodoc.route) }) { // Cambiar el estado al hacer clic en "Salida"
                        Icon(Icons.Default.DateRange, contentDescription = "Inicio")
                    }
                    IconButton(onClick = {

                        viewModel.jalarinformacionusuarios(usuario)

                    }) {
                        Icon(Icons.Default.Person, contentDescription = "Usuario")
                    }
                    IconButton(onClick = { showLogoutDialog.value = true }) {
                        Icon(Icons.AutoMirrored.Filled.ExitToApp, contentDescription = "Salir")
                    }

                }
            }
        )
        inforespuesta?.let { result ->
            LaunchedEffect(result) {
                if (result.isSuccess) {
                    navController.navigate(route = AppScreens.InfoPersonalDoc.route)
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
                Column(modifier = Modifier.fillMaxWidth().height(35.dp),horizontalAlignment = Alignment.CenterHorizontally,
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
                Column(modifier = Modifier.fillMaxWidth().height(35.dp),horizontalAlignment = Alignment.CenterHorizontally,
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

