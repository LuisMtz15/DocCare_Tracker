
import android.app.TimePickerDialog
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Person2
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.rounded.AttachMoney
import androidx.compose.material.icons.rounded.CalendarMonth
import androidx.compose.material.icons.rounded.ChevronLeft
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderColors
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.doccare_tracker.R
import com.example.doccare_tracker.model.Actividad.FiltroActividad
import com.example.doccare_tracker.model.Actividad.LeerActividadRespuestaItem
import com.example.doccare_tracker.model.Alimentos.FiltroAlimentos
import com.example.doccare_tracker.model.Alimentos.LeerAlimentosResultItem
import com.example.doccare_tracker.model.Ansiedad.FiltroAnsiedad
import com.example.doccare_tracker.model.Ansiedad.LeerAnsiedadItem
import com.example.doccare_tracker.model.Informacion_Personal.JalarInfo.JalarUsuariosDocRespuestaItem
import com.example.doccare_tracker.model.Pastillas.FiltroPastillas
import com.example.doccare_tracker.model.Pastillas.LeerPastillasRespuestaItem
import com.example.doccare_tracker.model.Presion.FiltroPresion
import com.example.doccare_tracker.model.Presion.LeerPresionesRespuestaItem
import com.example.doccare_tracker.model.Sueño.FiltroSueño
import com.example.doccare_tracker.model.Sueño.LeerSueñoRespuestaItem
import com.example.ejemplosapis.viewModel.AppViewModel
import java.time.Instant
import java.time.ZoneId
import java.util.Locale
import kotlin.math.roundToInt


@Composable
fun InputField(
    modifier: Modifier = Modifier,
    valueState: MutableState<String>,
    labelId: String = "test",
    icon: ImageVector = Icons.Rounded.AttachMoney,
    enabled: Boolean = true,
    isSingleLine: Boolean = true,
    keyboardType: KeyboardType = KeyboardType.Number,
    imeAction: ImeAction = ImeAction.Next,
    onAction: KeyboardActions = KeyboardActions.Default
) {

    OutlinedTextField(
        value = valueState.value,
        onValueChange = { valueState.value = it },
        label = { Text(text = labelId) },
        leadingIcon = {
            Icon(imageVector = icon, contentDescription = null)
        },
        singleLine = isSingleLine,
        enabled = enabled,
        textStyle = TextStyle(fontSize = 18.sp, color = LocalContentColor.current),
        modifier = modifier
            .padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
            .fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
        keyboardActions = onAction
    )
}


@Composable
fun InputField_text(
    modifier: Modifier = Modifier,
    valueState: MutableState<String>,
    labelId: String = "test",
    enabled: Boolean = true,
    isSingleLine: Boolean = true,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    onAction: KeyboardActions = KeyboardActions.Default,
    password: Boolean = false,
) {

    OutlinedTextField(
        value = valueState.value,
        onValueChange = { valueState.value = it },
        label = { Text(text = labelId) },
        singleLine = isSingleLine,
        enabled = enabled,
        textStyle = TextStyle(fontSize = 18.sp, color = LocalContentColor.current),
        modifier = modifier
            .padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
            .size(width = 330.dp, height = 60.dp),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
        keyboardActions = onAction,
        visualTransformation = if (password) PasswordVisualTransformation() else VisualTransformation.None,
        shape = RoundedCornerShape(16.dp)
    )
}


@Composable
fun InputField_text_wicon(
    modifier: Modifier = Modifier,
    valueState: MutableState<String>,
    labelId: String = "test",
    icon: ImageVector = Icons.Rounded.AttachMoney,
    enabled: Boolean = true,
    isSingleLine: Boolean = true,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    onAction: KeyboardActions = KeyboardActions.Default,
    password: Boolean = false,
) {

    OutlinedTextField(
        value = valueState.value,
        onValueChange = { valueState.value = it },
        label = { Text(text = labelId) },
        singleLine = isSingleLine,
        enabled = enabled,
        leadingIcon = {
            Icon(imageVector = icon, contentDescription = null)
        },
        textStyle = TextStyle(fontSize = 18.sp, color = LocalContentColor.current),
        modifier = modifier
            .padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
            .size(width = 330.dp, height = 60.dp),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
        keyboardActions = onAction,
        visualTransformation = if (password) PasswordVisualTransformation() else VisualTransformation.None,
        shape = RoundedCornerShape(16.dp)
    )
}



@Composable
fun InputField_text_wicon_password(
    modifier: Modifier = Modifier,
    valueState: MutableState<String>,
    labelId: String = "test",
    icon: ImageVector? = Icons.Rounded.AttachMoney,
    enabled: Boolean = true,
    isSingleLine: Boolean = true,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    onAction: KeyboardActions = KeyboardActions.Default,
    password: Boolean = true,
) {
    var passwordVisible by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = valueState.value,
        onValueChange = { valueState.value = it },
        label = { Text(text = labelId) },
        singleLine = isSingleLine,
        enabled = enabled,
        leadingIcon = {
            if (icon != null) {
                Icon(imageVector = icon, contentDescription = null)
            }
        },
        trailingIcon = {
            if (password) {
                val image = if (passwordVisible) {
                    Icons.Filled.Visibility
                } else {
                    Icons.Filled.VisibilityOff
                }

                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(imageVector = image, contentDescription = null)
                }
            }
        },
        textStyle = TextStyle(fontSize = 18.sp, color = LocalContentColor.current),
        modifier = modifier
            .padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
            .size(width = 330.dp, height = 60.dp),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
        keyboardActions = onAction,
        visualTransformation = if (password && !passwordVisible) PasswordVisualTransformation() else VisualTransformation.None,
        shape = RoundedCornerShape(16.dp)
    )
}

@Composable
fun InputField_text_password(
    modifier: Modifier = Modifier,
    valueState: MutableState<String>,
    labelId: String = "test",
    enabled: Boolean = true,
    isSingleLine: Boolean = true,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    onAction: KeyboardActions = KeyboardActions.Default,
    password: Boolean = true,
) {
    var passwordVisible by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = valueState.value,
        onValueChange = { valueState.value = it },
        label = { Text(text = labelId) },
        singleLine = isSingleLine,
        enabled = enabled,
        trailingIcon = {
            if (password) {
                val image = if (passwordVisible) {
                    Icons.Filled.Visibility
                } else {
                    Icons.Filled.VisibilityOff
                }

                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(imageVector = image, contentDescription = null)
                }
            }
        },
        textStyle = TextStyle(fontSize = 18.sp, color = LocalContentColor.current),
        modifier = modifier
            .padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
            .size(width = 330.dp, height = 60.dp),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
        keyboardActions = onAction,
        visualTransformation = if (password && !passwordVisible) PasswordVisualTransformation() else VisualTransformation.None,
        shape = RoundedCornerShape(16.dp)
    )
}


@Composable
fun InputField_text_registro(
    modifier: Modifier = Modifier,
    valueState: MutableState<String>,
    labelId: String = "test",
    enabled: Boolean = true,
    isSingleLine: Boolean = true,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    onAction: KeyboardActions = KeyboardActions.Default,
    password: Boolean = false,
    isPasswordokey: MutableState<Boolean>,
) {
    var passwordVisible by remember { mutableStateOf(false) }
    var isPasswordValid by remember { mutableStateOf(true) }
    val errorMessage = "La contraseña debe tener más de 8 caracteres, al menos una letra mayúscula y un número."

    fun validatePassword(password: String): Boolean {
        return password.length > 7 && password.any { it.isUpperCase() } && password.any { it.isDigit() }
    }

    OutlinedTextField(
        value = valueState.value,
        onValueChange = {
            valueState.value = it
            isPasswordValid = validatePassword(it)
        },
        label = { Text(text = labelId) },
        singleLine = isSingleLine,
        enabled = enabled,
        trailingIcon = {
            if (password) {
                val image = if (passwordVisible) {
                    Icons.Filled.Visibility
                } else {
                    Icons.Filled.VisibilityOff
                }

                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(imageVector = image, contentDescription = null)
                }
            }
        },
        textStyle = TextStyle(fontSize = 18.sp, color = LocalContentColor.current),
        modifier = modifier
            .padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
            .size(width = 330.dp, height = 60.dp),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
        keyboardActions = onAction,
        visualTransformation = if (password && !passwordVisible) PasswordVisualTransformation() else VisualTransformation.None,
        isError = !isPasswordValid,
        shape = RoundedCornerShape(16.dp)
    )

    if (!isPasswordValid) {
        Text(
            text = errorMessage,
            color = MaterialTheme.colorScheme.error,
            style = TextStyle(fontSize = 12.sp),
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 4.dp)
        )
        isPasswordokey.value = false
    }else{
        isPasswordokey.value = true}
}


//Horas
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePicker(
    color_button: Color = MyColorPalette.Registro,
    selectedTime: MutableState<String>,

) {
    val context = LocalContext.current
    val timePickerState = rememberTimePickerState(is24Hour = false)
    val showTimePicker = remember { mutableStateOf(false) }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            OutlinedTextField(
                value = selectedTime.value,
                onValueChange = { },
                label = { Text(text ="Hora seleccionada",
                    style = TextStyle(fontSize = 12.sp,
                        fontWeight = FontWeight.Bold))},
                enabled = false,
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 10.dp)
            )
            Button(
                onClick = { showTimePicker.value = true },
                modifier = Modifier.padding(end = 16.dp),
                colors = ButtonDefaults.buttonColors(containerColor =color_button )
            ) {
                Text("Seleccionar Hora")
            }


        }

        if (showTimePicker.value) {
            TimePickerDialog(
                context,
                { _, hourOfDay, minute ->
                    val time = String.format(Locale.getDefault(), "%02d:%02d", hourOfDay, minute)
                    selectedTime.value = time
                    showTimePicker.value = false
                },
                timePickerState.hour,
                timePickerState.minute,
                false
            ).show()
        }
    }
}

//Title Text

@Composable
fun TitleText(text: String, color: Color, textcolor: Color) {

    Surface(color= color, modifier = Modifier
        .fillMaxWidth()
        .height(80.dp)) {
        Row (modifier = Modifier.fillMaxSize(), Arrangement.Center, Alignment.CenterVertically) {
            Text(
                text = text,
                color = textcolor,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }

}

//Text
@Composable
fun TextForm(text: String, color: Color) {

    Text(
        text = text,
        color = color,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold
    )

}


//Botones

@Composable
fun CustomButton(
    color: Color,
    colorText: Color,
    buttonText: String,
    size: Int,
    onClickAction: () -> Unit = {}
) {
    Column{
        Button(
            onClick = onClickAction,
            modifier = Modifier.size(width = getButtonWith(size).dp, height = getButtonH(size).dp),
            colors = ButtonDefaults.buttonColors(containerColor = color ),
            contentPadding = PaddingValues(bottom = 0.3.dp)
        ) {
            Text(text = buttonText,
                fontSize = getFontSize(size).sp,
                color = colorText,)
        }
    }
}

private fun getButtonWith(size: Int): Int {
    return when (size) {
        1 -> 70.dp
        2 -> 134.dp
        3 -> 156.dp
        4 -> 358.dp
        5 -> 100.dp
        6 -> 250.dp
        7 -> 250.dp
        8-> 240.dp
        else -> 130.dp
    }.value.toInt()
}

private fun getButtonH(size: Int): Int {
    return when (size) {
        1 -> 30.dp
        2 -> 45.dp
        3 -> 45.dp
        4 -> 45.dp
        5 -> 45.dp
        6 -> 75.dp //Inicio de Procesos
        7 -> 55.dp //Agregar procesos
        8-> 45.dp
        else -> 50.dp
    }.value.toInt()
}

private fun getFontSize(size: Int): Int {
    return when (size) {
        1 -> 12.sp
        2 -> 16.sp
        3 -> 20.sp
        4 -> 20.sp
        6 -> 20.sp
        7 -> 20.sp
        8-> 15.sp
        else -> 16.sp
    }.value.toInt()
}




//Slider
@Composable

fun SliderWithText(
    sel: MutableState<String>,
    palabras: List<String>,
    initialValue: String? =null, // Valor inicial opcional
    cP: Color, // Color Fuerte
    cS: Color, // Color claro
) {
    // Asigna el valor inicial de sel basado en initialValue
    LaunchedEffect(initialValue) {
        sel.value = initialValue ?: palabras[0]
    }
    var sliderValue by remember {
        mutableStateOf(initialValue?.let { palabras.indexOf(it).toFloat() } ?: 0f)
    }

    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.padding(bottom = 15.dp))
        Text(palabras[sliderValue.toInt()], style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 18.sp))
        Slider(
            value = sliderValue,
            onValueChange = { newValue ->
                sliderValue = newValue
                sel.value = palabras[sliderValue.toInt()]
            },
            valueRange = 0f..(palabras.size - 1).toFloat(),
            steps = palabras.size - 2,
            modifier = Modifier.fillMaxWidth(),
            colors = SliderColors(
                thumbColor = cP, //Bolita
                activeTickColor = cS, //Puntitos activos
                activeTrackColor = cS, //Linea Activa
                inactiveTickColor = Color.LightGray, //Puntitos inactivos
                inactiveTrackColor = Color.LightGray, //Linea Inactiva
                disabledActiveTickColor = Color.Transparent,
                disabledActiveTrackColor = Color.Transparent,
                disabledInactiveTickColor = Color.Transparent,
                disabledInactiveTrackColor = Color.Transparent,
                disabledThumbColor = Color.Transparent
            ),
        )
    }
}


@Composable
fun SliderWithTextTiempo(
    sel: MutableState<String>,
    palabras: List<String>,
    initialValue: String? =null, // Valor inicial opcional
    cP: Color, // Color Fuerte
    cS: Color, // Color claro
) {
    LaunchedEffect(initialValue) {
        sel.value = initialValue ?: palabras[0]
    }
    var sliderValue by remember {
        mutableStateOf(initialValue?.let { palabras.indexOf(it).toFloat() } ?: 0f)
    }

    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.padding(bottom = 15.dp))
        Text(" ${palabras[sliderValue.toInt()]} horas", style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 18.sp))
        Slider(
            value = sliderValue,
            onValueChange = { newValue ->
                sliderValue = newValue
                sel.value = palabras[sliderValue.toInt()]
            },
            valueRange = 0f..(palabras.size - 1).toFloat(),
            steps = 80,
            modifier = Modifier.fillMaxWidth(),
            colors = SliderColors(
                thumbColor = cP, //Bolita
                activeTickColor = cS, //Puntitos activos
                activeTrackColor = cS, //Linea Activa
                inactiveTickColor = Color.LightGray, //Puntitos inactivos
                inactiveTrackColor = Color.LightGray, //Linea Inactiva
                disabledActiveTickColor = Color.Transparent,
                disabledActiveTrackColor = Color.Transparent,
                disabledInactiveTickColor = Color.Transparent,
                disabledInactiveTrackColor = Color.Transparent,
                disabledThumbColor = Color.Transparent
            ),
        )
    }
}



//Slider minutos

@Composable
fun SliderWithText_minutos(
    initialValue: Int? = null, // Valor inicial opcional
    sel: MutableState<Int>,
    cP: Color, // Color Fuerte
    cS: Color // Color claro
) {

    val minValue = 20
    val maxValue = 120
    val step = 2

    // Asigna el valor inicial de sel basado en initialValue
    LaunchedEffect(initialValue) {
        sel.value = initialValue ?: minValue
    }

    var sliderValue by remember {
        mutableStateOf(initialValue?.toFloat() ?: minValue.toFloat())
    }

    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.padding(bottom = 15.dp))
        Text("${sliderValue.toInt()} minutos", style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 18.sp))
        Slider(
            value = sliderValue,
            onValueChange = { newValue ->
                val steps = ((maxValue - minValue) / step)
                val newValueInt = (newValue * steps).roundToInt() / steps.toFloat()
                sliderValue = newValueInt.coerceIn(minValue.toFloat(), maxValue.toFloat())
                sel.value = sliderValue.toInt()
            },
            valueRange = minValue.toFloat()..maxValue.toFloat(),
            steps = ((maxValue - minValue) / step).toFloat().toInt(),
            modifier = Modifier.fillMaxWidth(),
            colors = SliderColors(
                thumbColor = cP, //Bolita
                activeTickColor = cS, //Puntitos activos
                activeTrackColor = cS, //Linea Activa
                inactiveTickColor = Color.LightGray, //Puntitos inactivos
                inactiveTrackColor = Color.LightGray, //Linea Inactiva
                disabledActiveTickColor = Color.Transparent,
                disabledActiveTrackColor = Color.Transparent,
                disabledInactiveTickColor = Color.Transparent,
                disabledInactiveTrackColor = Color.Transparent,
                disabledThumbColor = Color.Transparent
            ),
        )
    }
}







//Imagen con titulo

@Composable
fun BackgroundImageWithText(title: String, modifier: Modifier = Modifier) {
    var backgroundImageRes = R.drawable.header_prin
    Box(modifier = Modifier.fillMaxSize()) {
        // Fondo de pantalla
        Image(
            bitmap = ImageBitmap.imageResource(backgroundImageRes),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            alignment = Alignment.TopCenter
        )

        // Título encima de la imagen
        Text(
            text = title,
            color = Color.Black,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            modifier = modifier
        )
    }
}



//Seleccion de Cuadritos

@Composable
fun ThreeOptionToggleButton(
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit,
    modifier: Modifier = Modifier,
    selectedOptionColor: Color = Color.Green,
    unselectedOptionColor: Color = Color.Transparent,
    containerBackgroundColor: Color = Color.White
) {
    Row(
        modifier = modifier
            .background(color = containerBackgroundColor, shape = RoundedCornerShape(8.dp))
            .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
            .padding(4.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        options.forEach { option ->
            Text(
                text = option,
                modifier = Modifier
                    .weight(1f)
                    .padding(12.dp)
                    .clickable {
                        onOptionSelected(option)
                    }
                    .background(
                        color = if (option == selectedOption) selectedOptionColor.copy(alpha = 0.3f) else unselectedOptionColor,
                        shape = RoundedCornerShape(4.dp)
                    )
                    .padding(8.dp),
                textAlign = TextAlign.Center,
                color = Color.Black // Mantiene el color del texto en negro cuando se selecciona una opcion
            )
        }
    }
}


//DatePicker

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Date_Picker(
    color_button: Color = MyColorPalette.Registro,
    color_tint: Color = Color.Gray,
    selectedDateText: MutableState<String>,
    viewModel: AppViewModel,
    opcion: String
){
    val state = rememberDatePickerState()
    var showDatePicker by remember { mutableStateOf(false) }

    Column(modifier = Modifier
        .padding(16.dp)
        .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center ) {
            OutlinedTextField(
                value = selectedDateText.value,
                onValueChange = { /* No haces nada aquí, ya que la fecha se actualiza automáticamente */ },
                label = { Text("Fecha Seleccionada")},
                modifier = Modifier.weight(1f),
                readOnly = true,
                enabled = false,
                shape = RoundedCornerShape(16.dp),
                textStyle = TextStyle(),
                trailingIcon = {
                    Icon(imageVector = Icons.Rounded.CalendarMonth, contentDescription = null, tint = color_tint)
                },


            )
            Spacer(modifier = Modifier.size(20.dp))

            Button(onClick = { showDatePicker = true }, colors = ButtonDefaults.buttonColors(containerColor = color_button)) {

                if (state.selectedDateMillis != null) {
                    Text(text = "Modificar Fecha")
                }
                else {
                    Text(text = "Seleccionar Fecha")
                }
            }
            if (showDatePicker) {
                DatePickerDialog(onDismissRequest = { showDatePicker = false },
                    confirmButton = {
                        Button(onClick = {
                            when (opcion) {
                                "Alimento" -> {
                                    viewModel.filtraralimentos(FiltroAlimentos(fecha = selectedDateText.value, usuario_id = viewModel.usuario_id.value) )
                                }
                                "Actividad" -> {
                                    viewModel.filtroactividades(FiltroActividad(fecha = selectedDateText.value, usuario_id = viewModel.usuario_id.value) )
                                }
                                "Ansiedad" -> {
                                    viewModel.filtroansiedades(FiltroAnsiedad(fecha = selectedDateText.value, usuario_id = viewModel.usuario_id.value) )
                                }
                                "Pastillas" -> {
                                    viewModel.filtropastillas(FiltroPastillas(fecha = selectedDateText.value, usuario_id = viewModel.usuario_id.value) )
                                }
                                "Sueño" -> {
                                    viewModel.filtrosueno(FiltroSueño(fecha = selectedDateText.value, usuario_id = viewModel.usuario_id.value) )
                                }
                                "Presion" -> {
                                    viewModel.filtropresiones(FiltroPresion(fecha = selectedDateText.value, usuario_id = viewModel.usuario_id.value) )
                                }
                                else -> {
                                    println("Opción no reconocida")
                                }
                            }

                            showDatePicker = false

                        }) {
                            Text(text = "Aceptar")
                        }
                    }) {
                    DatePicker(state = state)
                }
            }

            state.selectedDateMillis?.let {
                val dateFormatter = Instant.ofEpochMilli(it).atZone(ZoneId.of("UTC")).toLocalDate()
                val formattedDate = dateFormatter.toString()
                selectedDateText.value = formattedDate
            }

        }
    }
}




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Date_PickerRegister(
    color_button: Color = MyColorPalette.Registro,
    color_tint: Color = Color.Gray,
    selectedDateText: MutableState<String>

){
    val state = rememberDatePickerState()
    var showDatePicker by remember { mutableStateOf(false) }

    Column(modifier = Modifier
        .padding(16.dp)
        .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center ) {
            OutlinedTextField(
                value = selectedDateText.value,
                onValueChange = { /* No haces nada aquí, ya que la fecha se actualiza automáticamente */ },
                label = { Text("Fecha de Nacimiento")},
                modifier = Modifier.size(width = 345.dp, height = 60.dp).padding(end = 15.dp),
                readOnly = true,
                enabled = false,
                shape = RoundedCornerShape(16.dp),
                textStyle = TextStyle(),
                trailingIcon = {
                    Icon(imageVector = Icons.Rounded.CalendarMonth, contentDescription = null, tint = color_tint)
                },


                )
            Spacer(modifier = Modifier.size(20.dp))

            Button(onClick = { showDatePicker = true }, colors = ButtonDefaults.buttonColors(containerColor = color_button)) {

                if (state.selectedDateMillis != null) {
                    Text(text = "Modificar Fecha")
                }
                else {
                    Text(text = "Seleccionar Fecha")
                }
            }
            if (showDatePicker) {
                DatePickerDialog(onDismissRequest = { showDatePicker = false },
                    confirmButton = {
                        Button(onClick = { showDatePicker = false }) {
                            Text(text = "Aceptar")
                        }
                    }) {
                    DatePicker(state = state)
                }
            }

            state.selectedDateMillis?.let {
                val dateFormatter = Instant.ofEpochMilli(it).atZone(ZoneId.of("UTC")).toLocalDate()
                val formattedDate = dateFormatter.toString()
                selectedDateText.value = formattedDate
            }


        }
    }
}

//Boton con icono <
@Composable
fun IconOnlyButton(
    icon: ImageVector= Icons.Rounded.ChevronLeft,
    onClick: () -> Unit,
    contentDescription: String? = null,
    tint: Color = Color.Black
) {
    IconButton(
        onClick = onClick,
        content = {
            Icon(
                modifier = Modifier.size(width = 60.dp, height = 60.dp),
                imageVector = icon,
                contentDescription = contentDescription,
                tint = tint
            )
        }
    )
}

//RadioButton

@Composable
fun GenderSelection(selectedGender: MutableState<String>) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Selecciona tu género:", style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp))
        Spacer(modifier = Modifier.padding(bottom = 16.dp))
        Row(verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 35.dp),
            horizontalArrangement = Arrangement.SpaceEvenly){
            RadioButton(
                selected = selectedGender.value == "Masculino",
                onClick = { selectedGender.value = "Masculino" },
                colors = RadioButtonDefaults.colors(selectedColor = MyColorPalette.registros) // Color cuando está seleccionado
            )

            Text(text = "Masculino")

            RadioButton(
                selected = selectedGender.value == "Femenino",
                onClick = { selectedGender.value = "Femenino" },
                colors = RadioButtonDefaults.colors(selectedColor = MyColorPalette.registros) // Color cuando está seleccionado
            )

            Text(text = "Femenino")

        }

    }
}

@Composable
fun PastillaSelection(selected: MutableState<String>) {
    Column {
        Text(text = "¿Tomaste alguna pastilla para dormir?", style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 18.sp))
        Spacer(modifier = Modifier.padding(bottom = 16.dp))
        Row(verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 25.dp),
            horizontalArrangement = Arrangement.SpaceEvenly){
            RadioButton(
                selected = selected.value == "SI",
                onClick = { selected.value = "SI" },
                colors = RadioButtonDefaults.colors(selectedColor = MyColorPalette.SueñoF) // Color cuando está seleccionado
            )

            Text(text = "SI")

            RadioButton(
                selected = selected.value == "NO",
                onClick = { selected.value = "NO" },
                colors = RadioButtonDefaults.colors(selectedColor = MyColorPalette.SueñoF) // Color cuando está seleccionado
            )

            Text(text = "NO")

        }

    }
}

@Composable
fun PeriodoSelection(selected: MutableState<String>) {
    Column {
        Row(verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 25.dp),
            horizontalArrangement = Arrangement.SpaceEvenly){
            RadioButton(
                selected = selected.value == "Temporal",
                onClick = { selected.value = "Temporal" },
                colors = RadioButtonDefaults.colors(selectedColor = MyColorPalette.PastillasF) // Color cuando está seleccionado
            )

            Text(text = "Temporal")

            RadioButton(
                selected = selected.value == "Cronico",
                onClick = { selected.value = "Cronico" },
                colors = RadioButtonDefaults.colors(selectedColor = MyColorPalette.PastillasF) // Color cuando está seleccionado
            )

            Text(text = "Crónico")

        }

    }
}

@Composable
fun OpcionesSelection(opciones: List<String>,
                      selected: MutableState<String>,
                      text: String,
                      color: Color) {
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 20.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            opciones.forEach { opcion ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(end = 2.dp)
                ) {
                    RadioButton(
                        selected = selected.value == opcion,
                        onClick = { selected.value = opcion },
                        colors = RadioButtonDefaults.colors(selectedColor = color)
                    )
                    Text(text = opcion)
                }
            }
        }
    }
}


@Composable
fun Card_view(
    persona: JalarUsuariosDocRespuestaItem,
    onClick: () -> Unit,
    autoincrementcont: Int,
){
    val sexoformateado = when (persona.sexo) {
        "Masculino" -> "M"
        "Femenino" -> "F"
        else -> persona.sexo
    }
    Row(horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically){

        Card(
            modifier = Modifier
                .padding(16.dp)
                .width(300.dp)
                .shadow(
                    elevation = 10.dp,
                    shape = RoundedCornerShape(16.dp),
                    ambientColor = MyColorPalette.color_header,
                    spotColor = MyColorPalette.color_header
                ),
            colors = CardDefaults.cardColors(containerColor = Color.White),

        ) {
            Row(modifier = Modifier.padding(26.dp), horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically) {
                Box(modifier = Modifier.weight(.5f)) {
                    Text(text = autoincrementcont.toString())
                }
                Spacer(modifier = Modifier.padding(end = 30.dp))
                Box(modifier = Modifier.weight(4.5f)) {
                    Text(text = persona.nombre_completo)
                }
                Spacer(modifier = Modifier.padding(end = 20.dp))
                Box(modifier = Modifier.weight(1.5f)) {
                    Text(text = persona.edad.toString())
                }
                Spacer(modifier = Modifier.padding(end = 20.dp))
                Box(modifier = Modifier.weight(1f)) {
                    Text(text = sexoformateado)
                }
            }
        }
        Spacer(modifier = Modifier.padding(end = 5.dp))
        if (persona.sexo == "Masculino" || persona.sexo == "M") {
            Card(onClick = onClick, colors = CardDefaults.cardColors(containerColor = Color.Transparent)){
                Image(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Icono de Masculino",
                    modifier = Modifier.size(40.dp)
                )
            }
        } else {
            Card(onClick = onClick, colors = CardDefaults.cardColors(containerColor = Color.Transparent)) {
                Image(
                    imageVector = Icons.Default.Person2,
                    contentDescription = "Icono de Femenino",
                    modifier = Modifier.size(40.dp)
                )
            }
        }
    }
}



@Composable
fun Card_view_Alimentos(
    alimento: LeerAlimentosResultItem,
    onClick: () -> Unit
){
    val PorcionTransformado = when (alimento.tipo_porcion) {
        "Chico" -> "CH"
        "Mediano" -> "M"
        "Grande" -> "G"
        "Jumbo" -> "J"
        else -> alimento.tipo_porcion
    }

    Row(horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically){

        Card(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .shadow(
                    elevation = 10.dp,
                    shape = RoundedCornerShape(16.dp),
                    ambientColor = MyColorPalette.AlimentosC,
                    spotColor = MyColorPalette.AlimentosC
                ),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            onClick = onClick // No te olvides de usar onClick aquí si es necesario
        ) {
            Row(modifier = Modifier.padding(26.dp), horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically) {
                Box(modifier = Modifier.weight(4.7f)) {
                    Text(text = alimento.nombre)
                }
                Spacer(modifier = Modifier.padding(end = 30.dp))
                Box(modifier = Modifier.weight(1.2f)) {
                    Text(text = PorcionTransformado)
                }
                Spacer(modifier = Modifier.padding(end = 20.dp))
                Box(modifier = Modifier.weight(4.4f)) {
                    Text(text = alimento.fecha)
                }
            }
        }
    }
}





@Composable
fun Card_view_Actividad(
    actividad: LeerActividadRespuestaItem,
    onClick: () -> Unit
){
    Row(horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically){

        Card(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .shadow(
                    elevation = 10.dp,
                    shape = RoundedCornerShape(16.dp),
                    ambientColor = MyColorPalette.ActividadC,
                    spotColor = MyColorPalette.ActividadC
                ),
            colors = CardDefaults.cardColors(containerColor = Color.White),

            ) {
            Row(modifier = Modifier.padding(26.dp), horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically) {
                Box(modifier = Modifier.weight(2.5f)) {
                    Text(text = actividad.tipo_actividad)
                }
                Spacer(modifier = Modifier.padding(end = 30.dp))
                Box(modifier = Modifier.weight(2.5f)) {
                    Text(text = actividad.intensidad_actividad)
                }
                Spacer(modifier = Modifier.padding(end = 20.dp))
                Box(modifier = Modifier.weight(.5f)) {
                    Text(text = actividad.duracion.toString())
                }
            }
        }
    }
}



@Composable
fun Card_view_Presion(
    presion: LeerPresionesRespuestaItem,
    onClick: () -> Unit
){
    Row(horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically){

        Card(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .shadow(
                    elevation = 10.dp,
                    shape = RoundedCornerShape(16.dp),
                    ambientColor = MyColorPalette.PresionC,
                    spotColor = MyColorPalette.PresionC
                ),
            colors = CardDefaults.cardColors(containerColor = Color.White),

            ) {
            Row(modifier = Modifier.padding(26.dp), horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically) {
                Box(modifier = Modifier.weight(2.5f)) {
                    Text(text = formatTimeWithoutSeconds(presion.hora ))
                }
                Spacer(modifier = Modifier.padding(end = 40.dp))
                Box(modifier = Modifier.weight(2f)) {
                    Text(text = presion.sistolica.toString())
                }
                Spacer(modifier = Modifier.padding(end = 30.dp))
                Box(modifier = Modifier.weight(2f)) {
                    Text(text = presion.diastolica.toString())
                }
                Spacer(modifier = Modifier.padding(end = 20.dp))
                Box(modifier = Modifier.weight(4.3f)) {
                    Text(text = presion.emocion_presion)
                }
            }
        }
    }
}


@Composable
fun Card_view_Ansiedad(
    ansiedad: LeerAnsiedadItem,
    onClick: () -> Unit
){
    Row(horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically){

        Card(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .shadow(
                    elevation = 10.dp,
                    shape = RoundedCornerShape(16.dp),
                    ambientColor = MyColorPalette.SintomasC,
                    spotColor = MyColorPalette.SintomasC
                ),
            colors = CardDefaults.cardColors(containerColor = Color.White),

            ) {
            Row(modifier = Modifier.padding(26.dp), horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically) {
                Box(modifier = Modifier.weight(1f)) {
                    Text(text = ansiedad.sintoma_ansiedad )
                }
                Spacer(modifier = Modifier.padding(end = 30.dp))
                Box(modifier = Modifier.weight(.4f)) {
                    Text(text = formatTimeWithoutSeconds(ansiedad.hora))
                }
                Spacer(modifier = Modifier.padding(end = 20.dp))
                Box(modifier = Modifier.weight(.8f)) {
                    Text(text = ansiedad.intensidad_ansiedad)
                }
            }
        }
    }
}



@Composable
fun Card_view_Sueño(
    sueño: LeerSueñoRespuestaItem,
    onClick: () -> Unit
){
    Row(horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically){

        Card(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .shadow(
                    elevation = 10.dp,
                    shape = RoundedCornerShape(16.dp),
                    ambientColor = MyColorPalette.SueñoC,
                    spotColor = MyColorPalette.SueñoC
                ),
            colors = CardDefaults.cardColors(containerColor = Color.White),

            ) {
            Row(modifier = Modifier.padding(26.dp), horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically) {
                Box(modifier = Modifier.weight(.4f)) {
                    Text(text = sueño.pastilla_sueño )
                }
                Spacer(modifier = Modifier.padding(end = 30.dp))
                Box(modifier = Modifier.weight(.5f)) {
                    Text(text = formatTimeWithoutSeconds(sueño.horaDormir))
                }
                Spacer(modifier = Modifier.padding(end = 20.dp))
                Box(modifier = Modifier.weight(.5f)) {
                    Text(text = formatTimeWithoutSeconds(sueño.horaDespertar))
                }
                Spacer(modifier = Modifier.padding(end = 20.dp))
                Box(modifier = Modifier.weight(1f)) {
                    Text(text = sueño.calidad_sueño)
                }
            }
        }
    }
}

@Composable
fun Card_view_Pastillas(
    pastilla: LeerPastillasRespuestaItem,
    onClick: () -> Unit
){
    val tiempoformateado = when (pastilla.tiempo_pastilla) {
        "Temporal" -> "Temp"
        "Cronico" -> "Cron"
        "Crónico" -> "Cron"
        else -> pastilla.tiempo_pastilla
    }

    Row(horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically){

        Card(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .shadow(
                    elevation = 10.dp,
                    shape = RoundedCornerShape(16.dp),
                    ambientColor = MyColorPalette.PastillasC,
                    spotColor = MyColorPalette.PastillasC
                ),
            colors = CardDefaults.cardColors(containerColor = Color.White),

            ) {
            Row(modifier = Modifier.padding(26.dp), horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically) {
                Box(modifier = Modifier.weight(2.4f)) {
                    Text(text = pastilla.nombre )
                }
                Spacer(modifier = Modifier.padding(end = 30.dp))
                Box(modifier = Modifier.weight(1f)) {
                    Text(text = pastilla.dosis.toString())
                }
                Spacer(modifier = Modifier.padding(end = 20.dp))
                Box(modifier = Modifier.weight(.6f)) {
                    Text(text = pastilla.periodo_pastilla.toString())
                }
                Spacer(modifier = Modifier.padding(end = 20.dp))
                Box(modifier = Modifier.weight(1f)) {
                    Text(text = tiempoformateado)
                }
            }
        }
    }
}






// Cards with onCLick

@Composable
fun Card_view_Alimentos_won(
    alimento: LeerAlimentosResultItem,
    onClick: () -> Unit
){
    val PorcionTransformado = when (alimento.tipo_porcion) {
        "Chico" -> "CH"
        "Mediano" -> "M"
        "Grande" -> "G"
        "Jumbo" -> "J"
        else -> alimento.tipo_porcion
    }
    Row(horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically){

        Card(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .shadow(
                    elevation = 10.dp,
                    shape = RoundedCornerShape(16.dp),
                    ambientColor = MyColorPalette.AlimentosC,
                    spotColor = MyColorPalette.AlimentosC
                ),
            onClick = onClick,
            colors = CardDefaults.cardColors(containerColor = Color.White),

            ) {
            Row(modifier = Modifier.padding(26.dp), horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically) {
                Box(modifier = Modifier.weight(4.7f)) {
                    Text(text = alimento.nombre)
                }
                Spacer(modifier = Modifier.padding(end = 30.dp))
                Box(modifier = Modifier.weight(1.2f)) {
                    Text(text = PorcionTransformado)
                }
                Spacer(modifier = Modifier.padding(end = 20.dp))
                Box(modifier = Modifier.weight(4.4f)) {
                    Text(text = alimento.fecha)
                }
            }
        }
    }
}


@Composable
fun Card_view_Actividad_won(
    actividad: LeerActividadRespuestaItem,
    onClick: () -> Unit
){
    Row(horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically){

        Card(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .shadow(
                    elevation = 10.dp,
                    shape = RoundedCornerShape(16.dp),
                    ambientColor = MyColorPalette.ActividadC,
                    spotColor = MyColorPalette.ActividadC
                ),
            onClick = onClick,
            colors = CardDefaults.cardColors(containerColor = Color.White),

            ) {
            Row(modifier = Modifier.padding(26.dp), horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically) {
                Box(modifier = Modifier.weight(2.5f)) {
                    Text(text = actividad.tipo_actividad)
                }
                Spacer(modifier = Modifier.padding(end = 30.dp))
                Box(modifier = Modifier.weight(2.5f)) {
                    Text(text = actividad.intensidad_actividad)
                }
                Spacer(modifier = Modifier.padding(end = 20.dp))
                Box(modifier = Modifier.weight(.5f)) {
                    Text(text = actividad.duracion.toString())
                }
            }
        }
    }
}



@Composable
fun Card_view_Presion_won(
    presion: LeerPresionesRespuestaItem,
    onClick: () -> Unit
){
    Row(horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically){

        Card(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .shadow(
                    elevation = 10.dp,
                    shape = RoundedCornerShape(16.dp),
                    ambientColor = MyColorPalette.PresionC,
                    spotColor = MyColorPalette.PresionC
                ),
            onClick = onClick,
            colors = CardDefaults.cardColors(containerColor = Color.White),

            ) {
            Row(modifier = Modifier.padding(26.dp), horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically) {
                Box(modifier = Modifier.weight(2f)) {
                    Text(text = formatTimeWithoutSeconds(presion.hora))
                }
                Spacer(modifier = Modifier.padding(end = 40.dp))
                Box(modifier = Modifier.weight(1.5f)) {
                    Text(text = presion.sistolica.toString())
                }
                Spacer(modifier = Modifier.padding(end = 40.dp))
                Box(modifier = Modifier.weight(1.5f)) {
                    Text(text = presion.diastolica.toString())
                }
                Spacer(modifier = Modifier.padding(end = 40.dp))
                Box(modifier = Modifier.weight(4.3f)) {
                    Text(text = presion.emocion_presion)
                }
            }
        }
    }
}


@Composable
fun Card_view_Ansiedad_won(
    ansiedad: LeerAnsiedadItem,
    onClick: () -> Unit
){
    Row(horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically){

        Card(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .shadow(
                    elevation = 10.dp,
                    shape = RoundedCornerShape(16.dp),
                    ambientColor = MyColorPalette.SintomasC,
                    spotColor = MyColorPalette.SintomasC
                ),
            onClick = onClick,
            colors = CardDefaults.cardColors(containerColor = Color.White),

            ) {
            Row(modifier = Modifier.padding(26.dp), horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically) {
                Box(modifier = Modifier.weight(1f)) {
                    Text(text = ansiedad.sintoma_ansiedad )
                }
                Spacer(modifier = Modifier.padding(end = 30.dp))
                Box(modifier = Modifier.weight(.4f)) {
                    Text(text = formatTimeWithoutSeconds(ansiedad.hora))
                }
                Spacer(modifier = Modifier.padding(end = 20.dp))
                Box(modifier = Modifier.weight(.8f)) {
                    Text(text = ansiedad.intensidad_ansiedad)
                }
            }
        }
    }
}



@Composable
fun Card_view_Sueño_won(
    sueño: LeerSueñoRespuestaItem,
    onClick: () -> Unit
){
    Row(horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically){

        Card(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .shadow(
                    elevation = 10.dp,
                    shape = RoundedCornerShape(16.dp),
                    ambientColor = MyColorPalette.SueñoC,
                    spotColor = MyColorPalette.SueñoC
                ),
            onClick = onClick,
            colors = CardDefaults.cardColors(containerColor = Color.White),

            ) {
            Row(modifier = Modifier.padding(26.dp), horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically) {
                Box(modifier = Modifier.weight(.4f)) {
                    Text(text = sueño.pastilla_sueño )
                }
                Spacer(modifier = Modifier.padding(end = 30.dp))
                Box(modifier = Modifier.weight(.5f)) {
                    Text(text = formatTimeWithoutSeconds(sueño.horaDormir))
                }
                Spacer(modifier = Modifier.padding(end = 20.dp))
                Box(modifier = Modifier.weight(.5f)) {
                    Text(text = formatTimeWithoutSeconds(sueño.horaDespertar))
                }
                Spacer(modifier = Modifier.padding(end = 20.dp))
                Box(modifier = Modifier.weight(1f)) {
                    Text(text = sueño.calidad_sueño)
                }
            }
        }
    }
}

@Composable
fun Card_view_Pastillas_won(
    pastilla: LeerPastillasRespuestaItem,
    onClick: () -> Unit
){
    val tiempoformateado = when (pastilla.tiempo_pastilla) {
        "Temporal" -> "Temp"
        "Cronico" -> "Cron"
        "Crónico" -> "Cron"
        else -> pastilla.tiempo_pastilla
    }
    Row(horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically){

        Card(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .shadow(
                    elevation = 10.dp,
                    shape = RoundedCornerShape(16.dp),
                    ambientColor = MyColorPalette.PastillasC,
                    spotColor = MyColorPalette.PastillasC
                ),
            onClick = onClick,
            colors = CardDefaults.cardColors(containerColor = Color.White),

            ) {
            Row(modifier = Modifier.padding(26.dp), horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically) {
                Box(modifier = Modifier.weight(2.4f)) {
                    Text(text = pastilla.nombre )
                }
                Spacer(modifier = Modifier.padding(end = 30.dp))
                Box(modifier = Modifier.weight(1f)) {
                    Text(text = pastilla.dosis)
                }
                Spacer(modifier = Modifier.padding(end = 20.dp))
                Box(modifier = Modifier.weight(.6f)) {
                    Text(text = pastilla.periodo_pastilla.toString())
                }
                Spacer(modifier = Modifier.padding(end = 20.dp))
                Box(modifier = Modifier.weight(1f)) {
                    Text(text = tiempoformateado)
                }
            }
        }
    }
}


@Composable

fun alterdialogs_one(
    showyDialog: MutableState<Boolean>,
    TextoPrin: String,
    Boton1: String,
){
    AlertDialog(
        modifier = Modifier.fillMaxWidth(),
        onDismissRequest = { showyDialog.value = false },
        title = { Text(TextoPrin, textAlign = TextAlign.Center)},
        confirmButton = {
            Column(modifier = Modifier.fillMaxWidth().height(35.dp),horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center) {
                TextButton(onClick = {
                    // Lógica para cerrar sesión
                    showyDialog.value = false
                }) {
                    Text(Boton1)
                }
            }
        }
    )
}



