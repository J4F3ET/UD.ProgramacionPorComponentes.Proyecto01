package com.example.proyecto01.services.util

import com.example.proyecto01.services.BoardCell
import com.example.proyecto01.services.GameState
import com.example.proyecto01.services.Player

fun createInitialBoard(): List<BoardCell> {
    val initialBoard = mutableListOf<BoardCell>()
    for (position in 0..99) {
        if (position == 0)
            initialBoard.add(
                BoardCell(
                    position= position,
                    player = Player.PLAYER_A,
                    player2 = Player.PLAYER_B,
                    rocket = null,
                    parachute = null,
                    detour = null,
                    shortCut = null
                )
            )
        else
            initialBoard.add(
                BoardCell(
                    position= position,
                    player = null,
                    player2 = null,
                    rocket = null,
                    parachute = null,
                    detour = null,
                    shortCut = null
                )
            )
    }
    return initialBoard
}
fun createInitialGameState(): GameState {
    return GameState(
        board = createInitialBoard(),
        currentPlayer = Player.PLAYER_A,
        selectedCell = 0,
        winner = null
    )
}
fun updateCell(cell: BoardCell, action: Boolean, currentPlayer: Player): BoardCell {
    if (action )if(cell.player == Player.PLAYER_A)cell.player = null else cell.player2 = null
    else when(currentPlayer){
        Player.PLAYER_A -> cell.player = Player.PLAYER_A
        Player.PLAYER_B -> cell.player2 = Player.PLAYER_B
        else -> cell.player
    }
    return cell
}
fun updateGameState(position:Int,gameState:GameState):GameState {
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
    return GameState(
        board = updatedBoard,
        currentPlayer = if (gameState.currentPlayer == Player.PLAYER_A) Player.PLAYER_B else Player.PLAYER_A,
        selectedCell = positionPlayerOpossite,
        winner = if(newPosition == 100) currentPlayer else null
    )
}