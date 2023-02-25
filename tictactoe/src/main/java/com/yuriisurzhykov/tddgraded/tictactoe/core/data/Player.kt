package com.yuriisurzhykov.tddgraded.tictactoe.core.data

abstract class Player(private val playerSign: Char) {
    fun isPlayingForSign(sign: Char): Boolean {
        return playerSign == sign
    }
}