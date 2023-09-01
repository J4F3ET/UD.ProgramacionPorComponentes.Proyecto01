package com.example.proyecto01.Screens

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults.filledIconButtonColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.proyecto01.R
import com.example.proyecto01.ui.theme.BackgroundImage



@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "NewApi")
@Composable
fun GameScreen(navController: NavController){
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Color.Transparent),
                title = {
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
                            Icon(Icons.Default.ArrowBack, contentDescription = "Ir a Home", tint = Color.White)
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
                                painter = painterResource(id = R.drawable.subzero),
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
                },
            )
        },
        bottomBar = {
            BottomAppBar(
                modifier = Modifier.height(60.dp).padding(0.dp,0.dp,0.dp,5.dp),
                containerColor = Color.Transparent,
                actions = {
                    Spacer(modifier = Modifier.width(23.dp))
                    Column{
                        IconButton(
                            onClick = { /* do something */ },
                            modifier = Modifier
                                .background(Color.Transparent)
                                .size(80.dp)
                        ){Icon(Icons.Filled.Refresh, contentDescription = "Localized description",tint = Color.White)}
                    }
                },
                floatingActionButton = {
                    Row {
                        Column {
                            FloatingActionButton(
                                onClick = { /* do something */ },
                                containerColor = BottomAppBarDefaults.bottomAppBarFabColor,
                                elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
                            ){Text(text = "1")}
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            FloatingActionButton(
                                onClick = { /* do something */ },
                                containerColor = BottomAppBarDefaults.bottomAppBarFabColor,
                                elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
                            ){Text(text = "1")}
                        }
                    }
                }
            )
        }){
        BackgroundImage()
        GameBodyContent(navController)
    }
}
@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun GameBodyContent(navController: NavController){
    SnakeGameBoard()
}

@Preview(widthDp = 568, heightDp = 320)
@Composable
fun GameControls(){
    GameScreen(navController = rememberNavController())
}
@Composable
fun SnakeGameBoard() {
    LazyVerticalGrid(
        columns = GridCells.Fixed(10),//Columnas
        modifier = Modifier.fillMaxSize().background(Color.Transparent),
        verticalArrangement = Arrangement.Center,
        horizontalArrangement = Arrangement.Center,
        contentPadding = PaddingValues(all = 8.dp)
    ) {
        items(5 * 10) { index ->
            SnakeCell(modifier = Modifier.size(40.dp))// Tamaño de cada celda
        }
    }
}
@Composable
fun SnakeCell(modifier: Modifier) {
    Box(
        modifier = modifier
            .background(Color.Transparent)
            .padding(2.dp)
            .border(width = 1.dp, color = Color.White) // Espacio entre celdas
    )
}
@Composable
fun gif(){
    val composition_ghost by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.ghos))
    Column (verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
        LottieAnimation(composition = composition_ghost, modifier = Modifier.size(54.dp))
    }
}