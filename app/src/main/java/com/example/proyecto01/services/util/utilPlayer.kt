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
            else -> null
        }
    val players = (mutableListOf <Player>())
    findPlayer(board[index].player)?.let { players.add(it) }
    findPlayer(board[index].player2)?.let { players.add(it) }
    findPlayer(board[index].parachute)?.let { players.add(it) }
    findPlayer(board[index].rocket)?.let { players.add(it) }
    return players
}