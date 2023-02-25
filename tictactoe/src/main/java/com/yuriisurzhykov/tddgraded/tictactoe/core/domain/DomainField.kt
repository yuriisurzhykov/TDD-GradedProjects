package com.yuriisurzhykov.tddgraded.tictactoe.core.domain

import com.yuriisurzhykov.tddgraded.tictactoe.core.data.Player

interface Field {

    fun containsCellForPlayer(player: Player, cell: Cell): Boolean

}

data class DomainField(
    private val cells: Set<Cell>
) : Field {

    override fun containsCellForPlayer(player: Player, cell: Cell): Boolean {
        return cells.any { fieldCell ->
            fieldCell.isCellUsedForPlayer(player) && fieldCell.withCoordinates(cell)
        }
    }
}