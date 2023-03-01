package com.yuriisurzhykov.tddgraded.tictactoe.core.domain

interface GameController {
    fun makeMove(player: Player, move: Move): GameState

    abstract class Abstract(private val field: GameField) : GameController {

    }
}