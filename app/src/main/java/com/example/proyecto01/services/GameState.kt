package com.example.proyecto01.services
data class GameState(
    var board: List<BoardCell>,
    var currentPlayer: Player,
    var selectedCell: Int,
    var winner: Player?
)