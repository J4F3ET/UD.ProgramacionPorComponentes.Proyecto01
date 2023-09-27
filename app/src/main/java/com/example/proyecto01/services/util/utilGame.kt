package com.example.proyecto01.services.util

import android.content.Context
import com.example.proyecto01.services.BoardCell
import com.example.proyecto01.services.GameState
import com.example.proyecto01.services.Player
import com.example.proyecto01.services.Position

fun createInitialBoard(): List<BoardCell>
{
    val initialBoard = mutableListOf<BoardCell>()
    val listRocket = generateRocketPositions()
    val listParachute = generateParachutePositions()
    for (position in 0..(SettingGame.numCells)) {
        if (position == 0)
            initialBoard.add(BoardCell(position,Player.PLAYER_A,Player.PLAYER_B,null,null))
        else
            initialBoard.add(
                BoardCell(
                    position = position,
                    player = null,
                    player2 = null,
                    detourEntity = verificateStartDetourEntity(listRocket,listParachute,position)?:verificateEndDetourEntity(listRocket,listParachute,position),
                    detour = verificateStartDetour(listRocket,listParachute,position)?:verificateEndDetour(listRocket,listParachute,position)
                )
            )
    }
    return initialBoard
}
fun verificateEndDetourEntity(listRocket:List<Position>,listParachute:List<Position>,position:Int):Player?{
    val detourParachute = listParachute.find { positionParachute ->positionParachute.endPosition == position}?.let{ Player.GRASS }
    val detourRocket = listRocket.find { positionParachute ->positionParachute.endPosition == position}?.let{ Player.EARTH }
    return detourParachute?:detourRocket
}
fun verificateEndDetour(listRocket:List<Position>,listParachute:List<Position>,position:Int):Position?{
    val detourParachute = listParachute.find { positionParachute ->positionParachute.endPosition == position}?.let{ Position(it.startPosition, it.endPosition) }
    val detourRocket = listRocket.find { positionParachute ->positionParachute.endPosition == position}?.let{ Position(it.startPosition, it.endPosition) }
    return detourParachute?:detourRocket
}
fun verificateStartDetourEntity(listRocket:List<Position>,listParachute:List<Position>,position:Int):Player?{
    val detourParachute = listParachute.find { positionParachute ->positionParachute.startPosition == position}?.let{ Player.PARACHUTE }
    val detourRocket = listRocket.find { positionParachute ->positionParachute.startPosition == position}?.let{ Player.ROCKET }
    return detourParachute?:detourRocket
}
fun verificateStartDetour(listRocket:List<Position>,listParachute:List<Position>,position:Int):Position?{
    val detourParachute = listParachute.find { positionParachute ->positionParachute.startPosition == position}?.let{ Position(it.startPosition, it.endPosition) }
    val detourRocket = listRocket.find { positionParachute ->positionParachute.startPosition == position}?.let{ Position(it.startPosition, it.endPosition) }
    return detourParachute?:detourRocket
}
fun generateRocketPositions(): List<Position> {
    val rocketPositions = mutableListOf<Position>()
    val percentage98 = (SettingGame.numCells*0.98).toInt()
    val percentage60 = (SettingGame.numCells*0.6).toInt()
    val percentage10 = (SettingGame.numCells*0.1).toInt()
    repeat(SettingGame.numShortcut) {
        var start = (1 .. percentage60).random()
        var end = (start + percentage10 .. percentage98).random()
        if (start > end) {
            val temp = start
            start = end
            end = temp
        }
        // Evitar que combinaciones de start y end se repita
        while (rocketPositions.any { it.startPosition == start && it.endPosition == end }) {
            start = (1 .. percentage60).random()
            end = (start + 10 .. percentage98).random()
            if (start > end) {
                val temp = start
                start = end
                end = temp
            }
        }
        rocketPositions.add(Position(start, end))
    }
    return rocketPositions
}
fun generateParachutePositions(): List<Position> {
    val parachutePositions = mutableListOf<Position>()
    val percentage98 = (SettingGame.numCells*0.98).toInt()
    val percentage50 = (SettingGame.numCells*0.5).toInt()
    val percentage40 = (SettingGame.numCells*0.4).toInt()
    repeat(SettingGame.numShortcut) {
        var start = (percentage50 .. percentage98).random()
        var end = (1..percentage40).random()

        if (start < end) {
            val temp = start
            start = end
            end = temp
        }
        while (parachutePositions.any { it.startPosition == start && it.endPosition == end }) {
            start = (1 .. percentage98).random()
            end = (1..percentage40).random()
            if (start < end) {
                val temp = start
                start = end
                end = temp
            }
        }
        parachutePositions.add(Position(start, end))
    }

    return parachutePositions
}
fun createInitialGameState(): GameState {
    return GameState(
        board = createInitialBoard(),
        currentPlayer = Player.PLAYER_A,
        selectedCell = 0,
        winner = null
    )
}
fun updateGameState(position:Int,gameState:GameState):GameState {
    val updatedBoard = gameState.board.toMutableList()//Tablero actual
    val currentPlayer = gameState.currentPlayer // Jugador actual
    val currentPosition = gameState.selectedCell // Posicion actual del jugador actual
    var newPosition = if(position+currentPosition >=(SettingGame.numCells-1)){(SettingGame.numCells-1)}else{position+currentPosition}//Posicion al que se movera la ficha
    val positionPlayerOpossite = updatedBoard.indexOfFirst { cell ->
                (gameState.currentPlayer == Player.PLAYER_A && cell.player2 == Player.PLAYER_B)||
                (gameState.currentPlayer == Player.PLAYER_B && cell.player == Player.PLAYER_A)
    }//Posicion del jugador contrario

    newPosition = if(gameState.currentPlayer.peerCounts == 3) 0 else newPosition // Si lleva tres "6" acumulados

    newPosition = if(updatedBoard[newPosition].detour == null) newPosition else updatedBoard[newPosition].detour!!.endPosition//Si pa nueva ubicacion existe un

    updatedBoard[currentPosition] = deletePlayerCell(updatedBoard[currentPosition].copy())//Elimina player de celda alctual

    updatedBoard[newPosition] = setPlayerCell(updatedBoard[newPosition].copy(),currentPlayer)//Agrega player de celda nueva


    val newGameState = if(position==6 && gameState.winner == null && newPosition != SettingGame.numCells-1){
        GameState(
            board = updatedBoard,
            currentPlayer = currentPlayer,
            selectedCell = newPosition,
            winner = if(newPosition == SettingGame.numCells) currentPlayer else null
        )
    }else{
        GameState(
            board = updatedBoard,
            currentPlayer = if (gameState.currentPlayer == Player.PLAYER_A) Player.PLAYER_B else Player.PLAYER_A,
            selectedCell = positionPlayerOpossite,
            winner = if(newPosition == SettingGame.numCells) currentPlayer else null
        )
    }
    return newGameState
}
fun speak(viewModel: SoundViewModel, context: Context, gameState: GameState, oldGameState: GameState){
    val stateViewModel = viewModel.state.value
    var player:Player = oldGameState.currentPlayer
    val positionPlayer = gameState.board.indexOfFirst { cell -> cell.player == player || cell.player2 == player}
    var mensaje = if(gameState.board.get(positionPlayer).player == player)
        "Jugador 1 esta en la posicion "+(positionPlayer+1)
    else "Jugador 2 esta en la posicion "+(positionPlayer+1)
    stateViewModel.text = mensaje
    viewModel.onTextFieldValue(mensaje)
    viewModel.textToSpeech(context)
}