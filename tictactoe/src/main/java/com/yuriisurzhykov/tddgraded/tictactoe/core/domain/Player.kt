package com.yuriisurzhykov.tddgraded.tictactoe.core.domain

abstract class Player(private val playerSign: String) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as Player
        if (playerSign != other.playerSign) return false

        return true
    }

    override fun hashCode(): Int {
        return playerSign.hashCode()
    }

    class XPlayer : Player("X")
    class OPlayer : Player("O")
}