package com.yuriisurzhykov.tddgraded.tictactoe.core.domain

interface RuleConditionResult {

    class ConditionSuccess : RuleConditionResult

    class ConditionIncomplete : RuleConditionResult
}