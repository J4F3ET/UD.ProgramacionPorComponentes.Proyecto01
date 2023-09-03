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
        gameState = updateGameState(currentThrow1+currentThrow2,gameState)
        /*
        val position = currentThrow1+currentThrow2
        var updatedBoard = gameState.board.toMutableList()
        val currentPlayer = gameState.currentPlayer
        val currentPosition = gameState.selectedCell // selectCell Corresponde a la posicion actual del jugador actual
        val newPosition = if(position+currentPosition >= 100){100}else{position+currentPosition}
        val positionPlayerOpossite = updatedBoard.indexOfFirst { cell ->
            (gameState.currentPlayer == Player.PLAYER_A && cell.player2 == Player.PLAYER_B) ||
                    (gameState.currentPlayer == Player.PLAYER_B && cell.player == Player.PLAYER_A)
        }
        updatedBoard[currentPosition] = updateCell(updatedBoard[currentPosition].copy(),true,currentPlayer)
        updatedBoard[newPosition] = updateCell(updatedBoard[newPosition].copy(),false,currentPlayer)

        gameState.board = updatedBoard
        gameState.selectedCell = positionPlayerOpossite
        gameState.currentPlayer =  if (gameState.currentPlayer == Player.PLAYER_A) Player.PLAYER_B else Player.PLAYER_A
        gameState = gameState.copy(
            board = updatedBoard,
            selectedCell = positionPlayerOpossite,
            currentPlayer = if (gameState.currentPlayer == Player.PLAYER_A) Player.PLAYER_B else Player.PLAYER_A
        )
         */
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