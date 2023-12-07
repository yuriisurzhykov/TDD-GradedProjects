package com.yuriisurzhykov.tddgraded.fibonacci.data

import kotlinx.coroutines.flow.Flow

interface FibonacciGenerator {

    fun fibonacciFlow(): Flow<>
}