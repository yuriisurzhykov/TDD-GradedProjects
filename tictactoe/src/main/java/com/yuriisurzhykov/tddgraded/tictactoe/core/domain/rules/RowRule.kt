package com.yuriisurzhykov.tddgraded.tictactoe.core.domain.rules

import com.yuriisurzhykov.tddgraded.tictactoe.core.data.Player
import com.yuriisurzhykov.tddgraded.tictactoe.core.domain.Field
import com.yuriisurzhykov.tddgraded.tictactoe.core.domain.RuleConditionResult

abstract class RowRule(private val cellRule: CellRule) : GameRule<Field> {
    override fun checkCondition(field: Field, player: Player): RuleConditionResult {
        TODO("Not yet implemented")
    }
}