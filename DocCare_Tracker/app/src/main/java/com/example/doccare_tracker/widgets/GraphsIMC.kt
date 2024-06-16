
import android.annotation.SuppressLint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

// Graficas de IMC e ICA

data class ColorRange(
    val start: Float,
    val end: Float,
    val color: Color,
    val label: String
)

@Composable
fun BarraProgreso(
    progress: Float,
    maxValue: Float = 40f,
    ranges: List<ColorRange> = listOf(
        // AREA PARA MODIFICAR VALORES.LIMITES Y COLORES DE GRAFICA BASE
        ColorRange(0f, 18.5f, Color.Green, "Bajo Peso"),
        ColorRange(18.5f, 25f, Color.Blue, "Normal"),
        ColorRange(25f, 30f, Color.Yellow, "Sobrepeso"),
        ColorRange(30f, 35f, Color.Magenta, "Obesidad"),
        ColorRange(35f, maxValue, Color.Red, "Obesidad Severa")
    ),
    backgroundColor: Color = Color.LightGray,
    indicatorColor: Color = Color.Black,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier
) {
    BoxWithConstraints(
        modifier = modifier
            .height(50.dp)
            .background(backgroundColor)
            .padding(2.dp)
            .clip(RoundedCornerShape(12.dp))
    ) {
        val totalWidth = constraints.maxWidth.toFloat()
        val progressRatio = progress / maxValue
        val indicatorPosition = totalWidth * progressRatio

        Column {
            Row(
                modifier = Modifier
                    .height(20.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
            ) {
                ranges.forEach { range ->
                    val rangeRatio = (range.end - range.start) / maxValue
                    Box(
                        modifier = Modifier
                            .weight(rangeRatio)
                            .fillMaxHeight()
                            .background(range.color)
                            .clip(RoundedCornerShape(10.dp))
                    )
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Canvas(
                    modifier = Modifier
                        .offset(x = with(LocalDensity.current) { indicatorPosition.toDp() } - 5.dp) // Ajuste para centrar el indicador
                        .size(10.dp)
                ) {
                    drawCircle(color = indicatorColor)
                }
            }
        }
    }
}


@Composable
fun IMC_BarraProgreso(imc: Float) {
    val imcState = IMC_Estados(imc)
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text("IMC: $imc", fontWeight = FontWeight.Bold)
        Text("Estado: $imcState", fontWeight = FontWeight.Bold)
        BarraProgreso(
            progress = imc,
            maxValue = 40f,
            ranges = listOf(
                // AREA PARA MODIFICAR VALORES Y LIMITES DE GRAFICA DE ICA, ADEMAS DE COLORES EN HEXADECIMAL
                ColorRange(0f, 18.5f, Color(0xFF5FB7ED), "Bajo Peso"),
                ColorRange(18.5f, 25f, Color(0xFFA8B844), "Normal"),
                ColorRange(25f, 30f, Color(0xFFF7CD46), "Sobrepeso"),
                ColorRange(30f, 35f, Color(0xFFE08538), "Obesidad"),
                ColorRange(35f, 40f, Color(0xFF851915), "Obesidad Severa")
            ),
            backgroundColor = Color.Transparent,
            indicatorColor = Color.Black,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
    }
}

@Composable
fun ICA_BarraProgreso(ica: Float) {
    val icaState = ICA_Estados(ica)
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text("ICA: $ica", fontWeight = FontWeight.Bold)
        Text("Estado: $icaState", fontWeight = FontWeight.Bold)
        BarraProgreso(
            progress = ica,
            maxValue = 1f,
            ranges = listOf(
                // AREA PARA MODIFICAR VALORES Y LIMITES DE GRAFICA DE ICA, ADEMAS DE COLORES EN HEXADECIMAL
                ColorRange(0f, 0.34f, Color(0xFF5FB7ED), "Delgadez severa"),
                ColorRange(0.35f, 0.41f, Color(0xFFA8B844), "Delgadez leve"),
                ColorRange(0.42f, 0.48f, Color(0xFFF7CD46), "Peso normal"),
                ColorRange(0.49f, 0.53f, Color(0xFFE08538), "Sobrepeso"),
                ColorRange(0.54f, 0.57f, Color(0xFFD93734), "Sobrepeso elevado"),
                ColorRange(0.58f, 1f, Color(0xFF851915), "Obesidad mórbida")
            ),
            backgroundColor = Color.Transparent,
            indicatorColor = Color.Black,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
    }
}

// Valores de IMC
fun IMC_Estados(imc: Float): String {
    return when {
        imc < 18.5f -> "Bajo Peso"
        imc < 25f -> "Normal"
        imc < 30f -> "Sobrepeso"
        imc < 35f -> "Obesidad"
        else -> "Obesidad Severa"
    }
}

// Valores de ICA
fun ICA_Estados(ica: Float): String {
    return when {
        ica < 0.34f -> "Delgadez severa"
        ica < 0.42f -> "Delgadez leve"
        ica < 0.49f -> "Peso normal"
        ica < 0.54f -> "Sobrepeso"
        ica < 0.58f -> "Sobrepeso elevado"
        else -> "Obesidad mórbida"
    }
}

@Preview(showBackground = true)
@Composable
fun TestBarra_IMCeICA() {
    Column {
        Text("IMC Ejemplo")
        IMC_BarraProgreso(imc = 21.0f)

        Spacer(modifier = Modifier.height(16.dp))

        Text("ICA Ejemplo")
        ICA_BarraProgreso(ica = 0.62f)
    }
}