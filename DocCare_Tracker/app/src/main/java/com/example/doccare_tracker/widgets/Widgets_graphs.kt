
import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.unit.dp

@Composable
fun BarChartValues(dates: List<String>?, colorPalette: List<Color>, text: String) {
    if (dates != null) {
        BarChart(
            dates = dates,
            colorPalette = colorPalette,
            modifier = Modifier
                .size(100.dp, 50.dp),
            title = text
        )
    } else {
        BarChart(
            dates = emptyList(),
            colorPalette = emptyList(),
            modifier = Modifier
                .size(100.dp, 50.dp),
            title = " "
        )
    }
}

@Composable
fun BarChart(dates: List<String>, colorPalette: List<Color>, modifier: Modifier = Modifier, title: String) {
    val uniqueDates = dates.distinct()

    Canvas(modifier = modifier) {
        val barWidth = size.width / (uniqueDates.size * 1.5f) //Ancho Barras

        uniqueDates.forEachIndexed { index, date ->
            val count = dates.count { it == date }
            val x = index * barWidth * 1.5f + barWidth / 2
            val y = size.height * (1 - (count / dates.size.toFloat()))

            drawRect(
                color = colorPalette[index % colorPalette.size],
                topLeft = Offset(x - barWidth / 2, y),
                size = Size(barWidth, size.height - y)
            )

            drawContext.canvas.nativeCanvas.drawText(
                date,
                x,
                size.height + 24f, // Posición vertical de la etiqueta de la fecha
                Paint().apply {
                    color = android.graphics.Color.BLACK
                    textAlign = Paint.Align.CENTER
                    textSize = 20f
                }
            )

            drawContext.canvas.nativeCanvas.drawText(
                count.toString(),
                x,
                y - 8f, // Posición vertical de la etiqueta del número de ocurrencias
                Paint().apply {
                    color = android.graphics.Color.BLACK
                    textAlign = Paint.Align.CENTER
                    textSize = 20f
                }
            )
        }


        drawContext.canvas.nativeCanvas.drawText(
            title,
            center.x,
            size.height + 80f,
            Paint().apply {
                color = android.graphics.Color.BLACK
                textAlign = Paint.Align.CENTER
                textSize = 24f
                isFakeBoldText = true
            }
        )
    }
}
