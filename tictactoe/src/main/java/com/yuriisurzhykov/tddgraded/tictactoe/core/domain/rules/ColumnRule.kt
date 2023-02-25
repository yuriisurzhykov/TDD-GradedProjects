package com.yuriisurzhykov.tddgraded.tictactoe.core.domain.rules

import com.yuriisurzhykov.tddgraded.tictactoe.core.domain.Field

abstract class ColumnRule(private val cellRule: CellRule) : GameRule<Field> {

}