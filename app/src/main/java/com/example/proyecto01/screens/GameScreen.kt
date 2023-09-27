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
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.proyecto01.screens.components.GameBoard
import com.example.proyecto01.screens.components.bottomBarGameScreen
import com.example.proyecto01.screens.components.topBarGameScreen
import com.example.proyecto01.services.GameState
import com.example.proyecto01.services.util.createInitialGameState
import com.example.proyecto01.services.util.updateGameState
import com.example.proyecto01.ui.theme.BackgroundImage
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.proyecto01.services.util.SoundViewModel
import com.example.proyecto01.services.util.speak


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "NewApi")
@Composable
fun GameScreen(navController: NavController,viewModel: SoundViewModel = viewModel()){
    var gameState by remember { mutableStateOf(createInitialGameState()) }
    var currentThrow1 by remember { mutableStateOf(0) }
    var currentThrow2 by remember { mutableStateOf(0) }
    val context = LocalContext.current
    fun updateGame() {
        val oldGameState = gameState.copy()
        currentThrow1 = (1..6).random()
        currentThrow2 = (1..6).random()
        gameState.currentPlayer.peerCounts = if(currentThrow1==6 || currentThrow2 == 6) gameState.currentPlayer.peerCounts++ else 0
        gameState = if (gameState.winner == null)updateGameState(currentThrow1 + currentThrow2, gameState)else gameState
        speak(viewModel, context ,gameState,oldGameState)
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