package com.example.proyecto01.screens.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ShapeDefaults.ExtraSmall
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.proyecto01.R
import com.example.proyecto01.services.GameState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun topBarGameScreen(navController:NavController,gameState:GameState){
    TopAppBar(
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Color.Transparent),
        title = {titleTopBarGameScreen(navController = navController,gameState=gameState)},
    )
}
@Composable
fun titleTopBarGameScreen(navController:NavController,gameState: GameState){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End,


        modifier = Modifier
            .fillMaxHeight()
            .padding(0.dp, 15.dp, 0.dp, 0.dp)
    ) {
        IconButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier
                .padding(16.dp)
                .background(Color.Transparent)
        ) {
            Icon(
                Icons.Default.ArrowBack,
                contentDescription = "Ir a Home",
                tint = Color.White
            )
        }
        Column {
            Image(
                painter = painterResource(id = R.drawable.ghost),
                modifier = Modifier.size(35.dp),
                contentDescription = "playerImgControl",
            )
            Text(
                text = "Jugador 1",
                color = Color.White,
                modifier = Modifier.size(30.dp),
                style = TextStyle(
                    fontSize = 6.5.sp, // Cambia el tamaño del texto
                    lineHeight = 15.sp, // Ajusta la altura de línea
                    letterSpacing = 0.sp,
                    shadow = Shadow(
                        color = Color(0xFFFFFFFF), // Color del sombreado (negro)
                        offset = Offset(2f, 2f), // Desplazamiento del sombreado en dp
                        blurRadius = 15f // Radio de desenfoque del sombreado en dp
                    )
                )
            )
        }

        Spacer(modifier = Modifier.width(8.dp)) // Espacio entre las imágenes
        Column {
            Image(
                painter = painterResource(id = R.drawable.blob),
                modifier = Modifier.size(35.dp),
                contentDescription = "playerImgControl",
            )
            Text(
                text = "Jugador 2",
                color = Color.White,
                modifier = Modifier.size(30.dp),
                style = TextStyle(
                    fontSize = 6.5.sp, // Cambia el tamaño del texto
                    lineHeight = 15.sp, // Ajusta la altura de línea
                    letterSpacing = 0.sp,
                    shadow = Shadow(
                        color = Color(0xFFFFFFFF), // Color del sombreado (negro)
                        offset = Offset(2f, 2f), // Desplazamiento del sombreado en dp
                        blurRadius = 15f // Radio de desenfoque del sombreado en dp
                    )
                )
            )
        }
    }
}
@Composable
fun bottomBarGameScreen(currentThrow1: Int, currentThrow2: Int, gameState: GameState, updateGame:() -> Unit){
    BottomAppBar(
        modifier = Modifier
            .height(60.dp)
            .padding(0.dp, 0.dp, 0.dp, 5.dp),
        containerColor = Color.Transparent,
        actions = {
            Spacer(modifier = Modifier.width(23.dp))
            Column{
                IconButton(
                    onClick = {updateGame()},
                    modifier = Modifier
                        .background(Color.Transparent)
                        .size(80.dp)
                ){ Icon(Icons.Filled.Refresh, contentDescription = "Puntaje",tint = Color.White) }
            }
        },
        floatingActionButton = {
            floatingActionButtonGameScreen(currentThrow1,currentThrow2)
        }
    )
}
@Composable
fun floatingActionButtonGameScreen(currentThrow1: Int, currentThrow2: Int) {
    @Composable
    fun newColumn(currentThrow:Int){
        val textStyle = TextStyle(
            color = Color.White,
            fontSize = 10.sp, // Cambia el tamaño del texto
            lineHeight = 25.5.sp, // Ajusta la altura de línea
            letterSpacing = 0.sp
        )
        Column(
            modifier = Modifier
                .border(width = 1.dp, color = Color.White, shape = ExtraSmall)
                .padding(16.dp)
        ) {
            Text(
                text = "$currentThrow",
                style = textStyle
            )
        }
    }
    Row{
        newColumn(currentThrow = currentThrow1)
        Spacer(modifier = Modifier.width(8.dp))
        newColumn(currentThrow = currentThrow2)
    }
}
