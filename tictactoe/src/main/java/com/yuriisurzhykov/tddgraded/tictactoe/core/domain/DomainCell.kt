package com.yuriisurzhykov.tddgraded.tictactoe.core.domain

import com.yuriisurzhykov.tddgraded.tictactoe.core.data.Player

interface Cell {
    fun isCellUsedForPlayer(player: Player): Boolean
    fun withCoordinates(other: Cell): Boolean
}

data class DomainCell(
    private val coordinateX: Int,
    private val coordinateY: Int,
    private val playerSign: Char
) : Cell {

    override fun isCellUsedForPlayer(player: Player): Boolean {
        return player.isPlayingForSign(playerSign)
    }

    override fun withCoordinates(other: Cell): Boolean {
        return other is DomainCell
                && coordinateX == other.coordinateX
                && coordinateY == other.coordinateY
    }
}
