package com.example.proyecto01.services
data class BoardCell(
    var position: Int,
    var player: Player?,
    var player2: Player?,
    var rocket: Player?,
    var parachute: Player?,
    var detour: Position?,
    var shortCut: Position?
)
