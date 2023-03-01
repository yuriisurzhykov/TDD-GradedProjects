package com.yuriisurzhykov.tddgraded.tictactoe.core.domain

interface RuleCheckResult {

    class ConditionSuccess : RuleCheckResult
    class ConditionIncomplete : RuleCheckResult
}