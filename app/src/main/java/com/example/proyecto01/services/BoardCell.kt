package com.example.proyecto01.services
data class BoardCell(
    var position: Int,
    var player: Player?,
    var player2: Player?,
    var detourEntity: Player?,
    var detour: Position?,
)
