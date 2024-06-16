
import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.unit.dp
import kotlin.math.cos
import kotlin.math.sin


@Composable
fun DonutChartValues(valuelist: List<String>?, valueColors: List<Color>, text:String, context:String) {
    val portionSizes = valuelist?.distinct()
    val portionLabels = portionSizes
    val portionCounts = portionSizes?.map { size ->
        valuelist.count { it == size }.toFloat()
    }

    val data = portionCounts
    val colors = valueColors
    val labels = portionLabels

    if (valuelist == null) {
        DonutChart(
            data = emptyList(),
            colors = emptyList(),
            labels = emptyList(),
            modifier = Modifier.size(100.dp),
            title = "",
            context = context
        )
    } else {
        Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
            if (data != null && labels != null) {
                DonutChart(data = data, colors = colors, labels = labels, modifier = Modifier.size(100.dp),
                    title = text, context=context)
            }
        }
    }
}




@Composable
fun DonutChart(
    data: List<Float>,
    colors: List<Color>,
    labels: List<String>,
    modifier: Modifier = Modifier,
    title: String,
    context: String
) {
    val total = data.sum()
    var startAngle = 0f
    val labelPadding = 0f
    Canvas(modifier = modifier) {
        val innerRadius = size.minDimension / 9 // Ajuste de tamaño del centro
        val outerRadius = size.minDimension / 1.0f // Ajuste del ancho de la gráfica
        val radius = (innerRadius + outerRadius) / 2
        val labelRadius = outerRadius / 1.9f // Distancia del centro para las etiquetas

        data.forEachIndexed { index, value ->
            val sweepAngle = 360 * (value / total)
            val angle = startAngle + sweepAngle / 2
            var x = center.x + labelRadius * cos(Math.toRadians(angle.toDouble())).toFloat()
            var y = center.y + labelRadius * sin(Math.toRadians(angle.toDouble())).toFloat()

            // Ajusta las coordenadas para agregar padding
            if (x < center.x) x -= labelPadding else x += labelPadding
            if (y < center.y) y -= labelPadding else y += labelPadding

            drawArc(
                color = colors[index],
                startAngle = startAngle,
                sweepAngle = sweepAngle,
                useCenter = false,
                style = Stroke(width = outerRadius - innerRadius)
            )
            startAngle += sweepAngle

            drawContext.canvas.nativeCanvas.drawText(
                labels[index],
                x,
                y,
                Paint().apply {
                    if (context!="Sintomas"){
                        color = android.graphics.Color.WHITE
                        textAlign = Paint.Align.CENTER
                        textSize = 30f
                    }else{
                        color = android.graphics.Color.BLACK
                        textAlign = Paint.Align.CENTER
                        textSize = 20f
                    }
                }
            )
        }

        drawCircle(
            color = Color.LightGray,
            radius = innerRadius,
            center = center
        )

        drawContext.canvas.nativeCanvas.drawText(
            title,
            center.x,
            center.y + outerRadius + 40f,
            Paint().apply {
                color = android.graphics.Color.BLACK
                textAlign = Paint.Align.CENTER
                textSize = 24f
                isFakeBoldText = true
            }
        )
    }
}

