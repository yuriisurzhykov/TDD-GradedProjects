package com.yuriisurzhykov.tddgraded.tictactoe.core.domain.rules

import com.yuriisurzhykov.tddgraded.tictactoe.core.data.Player
import com.yuriisurzhykov.tddgraded.tictactoe.core.domain.RuleConditionResult

interface GameRule<T> {

    fun checkCondition(element: T, player: Player): RuleConditionResult

}