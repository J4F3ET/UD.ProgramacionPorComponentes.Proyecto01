package com.example.proyecto01.ui.theme

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.proyecto01.R

@Composable
fun BackgroundImage() {
    Box(modifier = Modifier.fillMaxSize()){
        Image(
            painter = painterResource(id = R.drawable.sky),
            modifier = Modifier
                .fillMaxWidth() // Ancho de la imagen igual al ancho de la pantalla
                .align(Alignment.TopStart),
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )
        Image(
            painter = painterResource(id = R.drawable.rocks),
            modifier = Modifier
                .fillMaxWidth() // Ancho de la imagen igual al ancho de la pantalla
                .align(Alignment.BottomEnd),
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )
        Image(
            painter = painterResource(id = R.drawable.ground_1),
            modifier = Modifier
                .fillMaxWidth() // Ancho de la imagen igual al ancho de la pantalla
                .align(Alignment.Center),
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )
        Image(
            painter = painterResource(id = R.drawable.plant),
            modifier = Modifier
                .fillMaxWidth() // Ancho de la imagen igual al ancho de la pantalla
                .align(Alignment.Center),
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )
    }
    InfiniteTransicionSky()
}
@Composable
fun InfiniteTransicionSky() {
    val infiniteTransition = rememberInfiniteTransition()
    val positionSky1 by infiniteTransition.animateFloat(
        initialValue = 0.0f, // Inicia desde la parte derecha (0 es la posición más a la derecha)
        targetValue = -100.0f, // Termina a la izquierda (por ejemplo, a -100)
        animationSpec = infiniteRepeatable(
            animation = tween(30000, delayMillis = 100, easing = FastOutLinearInEasing),
            repeatMode = RepeatMode.Reverse
        )
    )
    val positionSky2 by infiniteTransition.animateFloat(
        initialValue = -100.0f, // Inicia desde la parte derecha (0 es la posición más a la derecha)
        targetValue = 0.0f, // Termina a la izquierda (por ejemplo, a -100)
        animationSpec = infiniteRepeatable(
            animation = tween(60000, delayMillis = 100, easing = FastOutLinearInEasing),
            repeatMode = RepeatMode.Reverse
        )
    )
    Image(
        painter = painterResource(id = R.drawable.clouds_1),
        contentDescription = "Nubes",
        modifier = Modifier.offset(x = positionSky1.dp, y = 0.dp) // Mantén la y en 0 para la parte superior
    )
    Image(
        painter = painterResource(id = R.drawable.clouds_2),
        contentDescription = "Nubes",
        modifier = Modifier.offset(x = positionSky2.dp, y = 0.dp) // Mantén la y en 0 para la parte superior
    )
}
