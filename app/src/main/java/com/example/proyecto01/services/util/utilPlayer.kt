package com.example.proyecto01.services.util

import com.example.proyecto01.services.BoardCell
import com.example.proyecto01.services.Player

fun selectPlayers(index:Int,board: List<BoardCell>): List<Player> {
    fun findPlayer(player: Player?) =
        when (player) {
            Player.PLAYER_A -> Player.PLAYER_A

            Player.PLAYER_B -> Player.PLAYER_B
            Player.PARACHUTE -> Player.PARACHUTE
            Player.ROCKET -> Player.ROCKET
            Player.MOON -> Player.MOON
            Player.GRASS -> Player.GRASS
            else -> null
        }
    val players = (mutableListOf <Player>())
    findPlayer(board[index].player)?.let { players.add(it) }
    findPlayer(board[index].player2)?.let { players.add(it) }
    findPlayer(board[index].detourEntity)?.let { players.add(it) }
    return players
}
fun deletePlayerCell(cell: BoardCell): BoardCell {
    return if (cell.player == Player.PLAYER_A) cell.copy(player = null)
    else cell.copy(player2 = null)
}
fun setPlayerCell(cell: BoardCell, currentPlayer: Player): BoardCell {
    return when(currentPlayer){
        Player.PLAYER_A -> cell.copy(player =Player.PLAYER_A)
        Player.PLAYER_B -> cell.copy(player2 = Player.PLAYER_B)
        else -> cell
    }
}