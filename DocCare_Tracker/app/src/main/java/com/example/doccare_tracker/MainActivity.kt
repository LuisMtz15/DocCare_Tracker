package com.example.doccare_tracker

import AppNavigation
import AppScreens
import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.doccare_tracker.model.Token.ChecarToken
import com.example.doccare_tracker.ui.theme.DocCare_TrackerTheme
import com.example.ejemplosapis.service.UserServiceApi
import com.example.ejemplosapis.viewModel.AppViewModel


class MainActivity : ComponentActivity() {

    @SuppressLint("StateFlowValueCalledInComposition", "ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LOCKED
        setContent {
            DocCare_TrackerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background

                ) {
                    val viewModel = AppViewModel(UserServiceApi.instance, application)
                    val token = viewModel.jwtToken.value
                    viewModel.checarToken(ChecarToken(token))
                    val imageView: ImageView = findViewById(R.drawable.logo_doccare)
                    imageView.setImageResource(R.drawable.logo_doccare)
                    MainScreen(viewModel)
                }
            }
        }
    }
}

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun MainScreen(viewModel: AppViewModel) {
    val tokenResult by viewModel.tokenResult.collectAsState()
    val leerusuariosResult by viewModel.leerUsuariosDocResult.collectAsState()
    val state = remember { mutableStateOf(false) }
    LoadingScreen()
    tokenResult?.let { result ->
        if (result.isSuccess) {
            if (viewModel.isTokenValid.value) {
                val rol = viewModel.role.value
                if (rol) {
                    AppNavigation(viewModel, AppScreens.iniciouser.route)
                } else {
                    viewModel.clave.value?.let { viewModel.leerUsuariosDoc(it) }
                    if (viewModel.leerUsuariosDocResult.value?.isSuccess == true) {
                        AppNavigation(viewModel, AppScreens.iniciodoc.route)
                    }
                    else {AppNavigation(viewModel, AppScreens.iniciodoc.route)}
                }
            }
        } else {
            AppNavigation(viewModel)
        }
    }
}


@Composable
fun LoadingScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Indicador de progreso lineal (animación de carga)
        Image(painter = painterResource(id = R.drawable.logo_doccare),
            contentDescription ="Logo de DocCare_Tracker" )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "DocCare Tracker", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(36.dp))
        LinearProgressIndicator(
            modifier = Modifier.width(150.dp) // Ancho del indicador
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Cargando...")
    }
}

