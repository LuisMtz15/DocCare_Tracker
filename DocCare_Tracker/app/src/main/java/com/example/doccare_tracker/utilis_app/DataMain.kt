package com.example.doccare_tracker.utils_app

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.doccare_tracker.R


@Composable
fun Alimentos_ini(click: () -> Unit) {
    val image = painterResource(id = R.drawable.alimentos)
    Column{
        Card(
            modifier = Modifier
                .size(width = 390.dp, height = 120.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            onClick = click
        ) {
            Image(painter = image, contentDescription = "Des",
                modifier = Modifier.fillMaxSize()
                    .scale(1.1f))
        }
    }
}

@Composable
fun Actividad_ini(click: () -> Unit) {
    val image = painterResource(id = R.drawable.actividad)
    Column {
        Card(
            modifier = Modifier
                .size(width = 390.dp, height = 120.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            onClick = click
        ) {
            Image(painter = image, contentDescription = "Des",
                modifier = Modifier.fillMaxSize()
                    .scale(1.1f))
        }
    }
}

@Composable
fun ansiedad_ini(click: () -> Unit) {
    val image = painterResource(id = R.drawable.ansiedad2)
    Column {
        Card(
            modifier = Modifier
                .size(width = 390.dp, height = 120.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            onClick = click
        ) {
            Image(painter = image, contentDescription = "Des",
                modifier = Modifier.fillMaxSize()
                    .scale(1.1f))
        }
    }
}

@Composable
fun presion_ini(click: () -> Unit) {
    val image = painterResource(id = R.drawable.presion)
    Column {
        Card(
            modifier = Modifier
                .size(width = 390.dp, height = 120.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            onClick = click
        ) {
            Image(painter = image, contentDescription = "Des",
                modifier = Modifier.fillMaxSize()
                    .scale(1.1f))
        }
    }
}

@Composable
fun sueno_ini(click: () -> Unit) {
    val image = painterResource(id = R.drawable.sueno)
    Column {
        Card(
            modifier = Modifier
                .size(width = 390.dp, height = 120.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            onClick = click
        ) {
            Image(painter = image, contentDescription = "Des",
                modifier = Modifier.fillMaxSize()
                    .scale(1.1f))
        }
    }
}

@Composable
fun pastillas_ini(click: () -> Unit) {
    val image = painterResource(id = R.drawable.pastillas)
    Column {
        Card(
            modifier = Modifier
                .size(width = 390.dp, height = 120.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            onClick = click


        ) {
            Image(painter = image, contentDescription = "Des",
                modifier = Modifier.fillMaxSize()
                    .scale(1.1f))
        }
    }
}
