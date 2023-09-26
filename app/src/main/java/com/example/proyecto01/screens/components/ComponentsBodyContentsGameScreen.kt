package com.example.proyecto01.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ShapeDefaults.ExtraSmall
import androidx.compose.material3.Text
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieConstants
import com.example.proyecto01.services.GameState
import com.example.proyecto01.services.Player
import com.example.proyecto01.services.util.SettingGame
import com.example.proyecto01.services.util.selectPlayers
@Composable
fun GameBoard(gameState: GameState) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(10),//Columnas
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent),
        verticalArrangement = Arrangement.Center,
        horizontalArrangement = Arrangement.Center,
        contentPadding = PaddingValues(all = 5.dp),
        reverseLayout = true
    ) {
        items(SettingGame.numCells) { index ->
            BuildCell(index+1,modifier = Modifier.size(10.dp,24.dp), *selectPlayers(index,gameState.board).toTypedArray())
        }
    }
}
@Composable
fun BuildCell(index:Int,modifier: Modifier,vararg  players: Player?){
    Box(
        modifier = modifier
            .background(Color.Transparent)
            .padding(1.5.dp)// Espacio entre celdas
            .border(width = 0.5.dp, color = Color.White, shape = ExtraSmall)
    ){
        Row{
            Column{
                Text(
                    text = "$index",
                    color = Color.White,
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 10.sp, // Cambia el tamaño del texto
                        lineHeight = 15.5.sp, // Ajusta la altura de línea
                        letterSpacing = 0.sp,
                    )
                )
            }
            for(player in players){
                if(player != null){
                    Column {
                        val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(player.animationRaw))
                        Column (verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
                            LottieAnimation(
                                composition = composition,
                                modifier = Modifier.size(25.dp),
                                isPlaying = true, // La animación está en reproducción
                                restartOnPlay = true, // La animación se reinicia cada vez que se reanuda
                                iterations = LottieConstants.IterateForever
                            )
                        }
                    }
                }
            }

        }
    }

}
