package com.yuriisurzhykov.tddgraded.tictactoe.core.domain.rules

import com.yuriisurzhykov.tddgraded.tictactoe.core.data.Player
import com.yuriisurzhykov.tddgraded.tictactoe.core.domain.Cell
import com.yuriisurzhykov.tddgraded.tictactoe.core.domain.RuleConditionResult

abstract class CellRule : GameRule<Cell> {
    override fun checkCondition(cell: Cell, player: Player): RuleConditionResult {
        TODO("Not yet implemented")
    }
}