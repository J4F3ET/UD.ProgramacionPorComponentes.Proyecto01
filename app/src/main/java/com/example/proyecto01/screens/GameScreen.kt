package com.example.proyecto01.screens

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.proyecto01.screens.components.GameBoard
import com.example.proyecto01.screens.components.bottomBarGameScreen
import com.example.proyecto01.screens.components.topBarGameScreen
import com.example.proyecto01.services.GameState
import com.example.proyecto01.services.util.createInitialGameState
import com.example.proyecto01.services.util.updateGameState
import com.example.proyecto01.ui.theme.BackgroundImage
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "NewApi")
@Composable
fun GameScreen(navController: NavController){
    var gameState by remember { mutableStateOf(createInitialGameState()) }
    var currentThrow1 by remember { mutableStateOf(0) }
    var currentThrow2 by remember { mutableStateOf(0) }
    fun updateGame() {
        currentThrow1 = (1..6).random()
        currentThrow2 = (1..6).random()
        gameState.currentPlayer.peerCounts = if(currentThrow1==6 || currentThrow2 == 6) gameState.currentPlayer.peerCounts++ else 0
        if (gameState.winner == null)
            gameState = updateGameState(currentThrow1+currentThrow2,gameState)
        else
            gameState
    }
    Scaffold(
        topBar = {topBarGameScreen(navController,gameState)},
        bottomBar = { bottomBarGameScreen(currentThrow1, currentThrow2,gameState,::updateGame)}
    )
    {
        BackgroundImage()
        GameBodyContent(navController,gameState)
    }
}

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun GameBodyContent(navController: NavController,gameState: GameState){
    GameBoard(gameState)
}
@Preview(widthDp = 568, heightDp = 320)
@Composable
fun GameControls(){
    GameScreen(navController = rememberNavController())
}