
import android.annotation.SuppressLint
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.doccare_tracker.model.Informacion_Personal.Usuario.ModificarDatosUsuario
import com.example.ejemplosapis.viewModel.AppViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Modi_personal_usuario(navController: NavHostController, viewModel: AppViewModel) {
    val dataNombre by viewModel.nombre.collectAsState()
    val dataApellidoPaterno by viewModel.apellidopaterno.collectAsState()
    val dataApellidoMaterno by viewModel.apellidomaterno.collectAsState()
    val dataCorreo by viewModel.email.collectAsState()
    val dataPeso by viewModel.peso.collectAsState()
    val dataAltura by viewModel.altura.collectAsState()
    val dataCircunferencia by viewModel.circunferencia.collectAsState()

    //Basicas
    val modificarRespuesta by viewModel.editarUsuarioResult.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    val usuario by viewModel.usuario_id.collectAsState()

    val clave_unica by viewModel.clave.collectAsState()
    val clave = remember { mutableStateOf(clave_unica ?: "") }


    val nombre = remember { mutableStateOf(dataNombre?:"") }
    val correo = remember { mutableStateOf(dataCorreo ?: "") }
    val apellidom = remember { mutableStateOf(dataApellidoMaterno?:"") }
    val apellidop= remember { mutableStateOf(dataApellidoPaterno?:"") }

    val peso = remember { mutableStateOf(dataPeso?: "") }
    val altura = remember { mutableStateOf(dataAltura?: "") }
    val circunferencia = remember { mutableStateOf(dataCircunferencia?:"") }

    Scaffold(modifier = Modifier.fillMaxSize(),
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier.padding(16.dp)
            )
        }) {


        Box{
            BackgroundImageWithText(title = "", modifier = Modifier
                .padding(top = 156.dp, start = 32.dp)
                .align(Alignment.TopStart))
        }

        Column(modifier = Modifier.padding(20.dp)) {

            Box {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
                    IconOnlyButton(onClick = {navController.popBackStack()})
                }
            }

            Spacer(modifier = Modifier.padding(bottom = 5.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically){
                Image(imageVector = Icons.Default.Person,
                    contentDescription ="Icono de Usuario",
                    modifier = Modifier.size(width = 150.dp, height = 150.dp))
            }

            LazyColumn {
                item {
                    Spacer(modifier = Modifier.padding(bottom = 30.dp))
                    Text(text = "Nombre",
                        style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    )
                    InputField_text(
                        valueState = nombre,
                        labelId = "",
                        enabled = true,
                        isSingleLine = true,
                        keyboardType = KeyboardType.Text)}

                item {

                    Spacer(modifier = Modifier.padding(bottom = 10.dp))
                    Text(text = "Apellido Paterno",
                        style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    )
                    InputField_text(
                        valueState = apellidop,
                        labelId = "",
                        enabled = true,
                        isSingleLine = true,
                        keyboardType = KeyboardType.Text,)}


                item {

                    Spacer(modifier = Modifier.padding(bottom = 10.dp))
                    Text(text = "Apellido Materno",
                        style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    )
                    InputField_text(
                        valueState = apellidom,
                        labelId = "",
                        enabled = true,
                        isSingleLine = true,
                        keyboardType = KeyboardType.Text)}

                item {

                    Spacer(modifier = Modifier.padding(bottom = 10.dp))

                    Text(text = "Correo",
                        style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    )
                    InputField_text(
                        valueState = correo,
                        labelId = "",
                        enabled = true,
                        isSingleLine = true,
                        keyboardType = KeyboardType.Email)}

                item {

                    Spacer(modifier = Modifier.padding(bottom = 10.dp))

                    Text(text = "Clave",
                        style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    )
                    InputField_text(
                        valueState = clave,
                        labelId = "",
                        enabled = true,
                        isSingleLine = true,
                        keyboardType = KeyboardType.Number,)}

                item {

                    Spacer(modifier = Modifier.padding(bottom = 10.dp))

                    Text(text = "Peso",
                        style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    )
                    InputField_text(
                        valueState = peso,
                        labelId = "",
                        enabled = true,
                        isSingleLine = true,
                        keyboardType = KeyboardType.Decimal,)}

                item {

                    Spacer(modifier = Modifier.padding(bottom = 10.dp))

                    Text(text = "Altura",
                        style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    )
                    InputField_text(
                        valueState = altura,
                        labelId = "",
                        enabled = true,
                        isSingleLine = true,
                        keyboardType = KeyboardType.Decimal,)}

                item {

                    Spacer(modifier = Modifier.padding(bottom = 10.dp))

                    Text(text = "Circunferecnia Abdominal",
                        style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    )
                    InputField_text(
                        valueState = circunferencia,
                        labelId = "",
                        enabled = true,
                        isSingleLine = true,
                        keyboardType = KeyboardType.Decimal,)}

                item {

                    Spacer(modifier = Modifier.padding(bottom = 20.dp))
                    //OnCLick, falta implementar
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){
                        CustomButton(color = MyColorPalette.modificar, colorText = Color.White,
                            buttonText = "Aceptar", size = 3,
                            onClickAction = {

                                viewModel.editarDatosUsuario(ModificarDatosUsuario(
                                    altura =altura.value.toFloat(),
                                    apellidom =apellidom.value, apellidop =apellidop.value,
                                    cintura =circunferencia.value.toFloat(), correo =correo.value, clave =clave.value,
                                    nombre =nombre.value, peso =peso.value.toFloat(), usuario_id = usuario))

                            })
                    }
                }

            }

            modificarRespuesta?.let { result ->
                LaunchedEffect(result) {
                    if (result.isSuccess) {
                        snackbarHostState.showSnackbar(
                            message = "Se modificó la información ccorrectamente",
                            duration = SnackbarDuration.Short,
                            actionLabel = "Continuar"
                        )
                        viewModel.setClave(clave.value)
                        viewModel.setAltura(altura.value)
                        viewModel.setApellidoMaterno(apellidom.value)
                        viewModel.setApellidoPaterno(apellidop.value)
                        viewModel.setCircunferencia(circunferencia.value)
                        viewModel.setPeso(peso.value)
                        viewModel.setEmail(correo.value)
                        viewModel.setNombre(nombre.value)
                        viewModel.setEditarUsuarioResult(null)
                        navController.navigate(route = AppScreens.InfoPersonalUser.route)
                    } else {
                        snackbarHostState.showSnackbar(
                            message = "Error: ${result.exceptionOrNull()?.message}",
                            duration = SnackbarDuration.Long,
                            actionLabel = "Reintentar"
                        )
                    }
                }
            }


        }
    }
}