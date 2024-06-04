
import android.annotation.SuppressLint
import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.unit.dp


@SuppressLint("DefaultLocale")
@Composable
fun WeightGraph2(
    weights: List<Float>,
    maxWeight: Float,
    minWeight: Float,
    modifier: Modifier = Modifier,
    nombre_1: String,
    nombre_2: String,
    nombre_3: String
) {
    val data = weights.map { it - minWeight }
    val maxDataValue = maxWeight - minWeight
    val minDataValue = minWeight
    val maxDataIndex = data.indexOf(data.maxOrNull() ?: 0f)
    val minDataIndex = data.indexOf(data.minOrNull() ?: 0f)

    Canvas(modifier = modifier) {
        // Fondo blanco
        drawRect(
            color = Color.White,
            size = size
        )

        val pointSpacing = size.width / (data.size.toFloat() + 1)
        val guidelineCount = 4
        val guidelineSpacing = size.height / (guidelineCount)

        // Líneas de guía
        for (i in 0..guidelineCount) {
            val guidelineY = i * guidelineSpacing
            drawLine(
                color = Color.LightGray,
                start = Offset(0f, guidelineY),
                end = Offset(size.width, guidelineY),
                strokeWidth = 1f
            )

            // Etiquetas de las líneas de guía
            drawContext.canvas.nativeCanvas.apply {
                drawText(
                    String.format("%.0f", maxWeight - (i * maxDataValue / guidelineCount)),
                    10f,
                    guidelineY - 10f,
                    Paint().apply {
                        textSize = 30f
                        color = android.graphics.Color.GRAY
                    }
                )
            }
        }

        data.forEachIndexed { index, value ->
            val pointHeight = size.height - (value / maxDataValue) * size.height
            val x = (index + 1) * pointSpacing
            val y = pointHeight
            drawCircle(
                color = Color.Blue,
                radius = 12f,
                center = Offset(x, y)
            )
        }

        // Línea que conecta los puntos
        val path = androidx.compose.ui.graphics.Path()
        data.forEachIndexed { index, value ->
            val x = (index + 1) * pointSpacing
            val y = size.height - (value / maxDataValue) * size.height
            if (index == 0) {
                path.moveTo(x, y)
            } else {
                path.lineTo(x, y)
            }
        }
        drawPath(
            path = path,
            color = Color.Blue,
            style = Stroke(width = 4f)
        )

        // Mensajes de peso máximo y mínimo
        drawContext.canvas.nativeCanvas.apply {
            val boldPaint = Paint().apply {
                textSize = 25f
                color = android.graphics.Color.BLACK
                isFakeBoldText = true
            }
            drawText(
                "${nombre_3}: ${weights[maxDataIndex]} kg",
                size.width - 230f,
                -60f,
                boldPaint
            )
            drawText(
                "${nombre_2}: ${weights[minDataIndex]} kg",
                size.width - 210f, //
                -100f, //
                boldPaint
            )
        }

        // Peso actual
        drawContext.canvas.nativeCanvas.apply {
            drawText(
                nombre_1,
                10f,
                -110f,
                Paint().apply {
                    textSize = 30f
                    color = android.graphics.Color.BLACK
                }
            )
            val boldPaint = Paint().apply {
                textSize = 40f
                color = android.graphics.Color.BLACK
                isFakeBoldText = true
            }
            drawText(
                "${weights.last()} kg",
                10f,
                -60f,
                boldPaint
            )
        }
    }
}
@Composable
fun WeightScreen2(
    weights: List<Float>,
    nombre1: String,
    nombre2: String,
    nombre3: String,
    valueMax: Float?,
    valueMin: Float?
) {
    Box(
        modifier = Modifier.padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Surface(
            modifier = Modifier.fillMaxWidth().height(180.dp) // Ajuste de ancho y altura (en ese orden)
                .shadow(4.dp, RoundedCornerShape(8.dp)),
            shape = RoundedCornerShape(8.dp),
            color = Color.White
        ) {
            if (valueMax != null) {
                if (valueMin != null) {
                    WeightGraph2(
                        weights = weights,
                        maxWeight = valueMax+10.0f,
                        minWeight = valueMin-10.0f,
                        modifier = Modifier.padding(bottom = 16.dp, start = 16.dp, end = 16.dp, top = 60.dp),
                        nombre_1=nombre1,
                        nombre_2=nombre2,
                        nombre_3= nombre3
                    )
                }
            }
        }
    }
}




