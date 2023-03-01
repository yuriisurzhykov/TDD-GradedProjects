package com.yuriisurzhykov.tddgraded.tictactoe.core.domain

interface GameField {
    fun isCellFilled(coordinateX: Int, coordinateY: Int): Boolean
    fun fillCell(coordinateX: Int, coordinateY: Int, player: Player)
}