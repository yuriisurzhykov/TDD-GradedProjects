package com.yuriisurzhykov.tddgraded.tictactoe.core.domain

interface GameRule {
    fun check(): RuleCheckResult
}