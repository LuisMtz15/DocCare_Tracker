
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.ejemplosapis.viewModel.AppViewModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Dudas(navController: NavHostController, viewModel: AppViewModel) {

    Scaffold(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            Box {
                TitleText(
                    text = "Tipo de Actividad",
                    color = MyColorPalette.ActividadF,
                    textcolor = Color.White,
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 17.dp),
                    horizontalArrangement = Arrangement.Start
                ) {
                    IconOnlyButton(
                        onClick = { navController.navigate(route = AppScreens.ActividadAgregar.route) },
                        tint = Color.White
                    )
                }
            } //Cierra el box

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Spacer(modifier = Modifier.padding(bottom = 10.dp))
                Text(text = "Actividad Anaeróbicas",
                    color = MyColorPalette.ActividadF, fontWeight = FontWeight.Bold, fontSize = 18.sp)

                Spacer(modifier = Modifier.padding(bottom = 30.dp))
                Text(text = " Las actividades anaeróbicas son ejercicios intensos y " +
                        "de corta duración que no requieren oxígeno para producir energía. " +
                        "Ejemplos incluyen el levantamiento de pesas y los sprints. " +
                        "Estos ejercicios ayudan a desarrollar fuerza y potencia muscular.")
                Spacer(modifier = Modifier.padding(bottom = 30.dp))
                Text(text = "Actividad Aeróbicas",
                    color = MyColorPalette.ActividadF, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Spacer(modifier = Modifier.padding(bottom = 30.dp))
                Text(text = "Las actividades aeróbicas son ejercicios de intensidad moderada " +
                        "que se realizan durante períodos prolongados, utilizando oxígeno para " +
                        "generar energía. Correr largas distancias y nadar son ejemplos comunes. " +
                        "Estas actividades mejoran la resistencia cardiovascular y la salud general.")
                Spacer(modifier = Modifier.padding(bottom = 30.dp))
                Text(text = "Equlibrado",
                    color = MyColorPalette.ActividadF, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Spacer(modifier = Modifier.padding(bottom = 30.dp))
                Text(text = "Las actividades equilibradas combinan ejercicios anaeróbicos y aeróbicos. " +
                        "Un ejemplo es el entrenamiento en circuito, que incluye estaciones de fuerza y " +
                        "cardio. Otro ejemplo es el crossfit, que mezcla levantamiento de pesas y " +
                        "carreras cortas. Estas actividades proporcionan un acondicionamiento físico " +
                        "completo.")
            }
        }
    }
}
