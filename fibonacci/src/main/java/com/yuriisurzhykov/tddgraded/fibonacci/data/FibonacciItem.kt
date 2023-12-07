package com.yuriisurzhykov.tddgraded.fibonacci.data

interface FibonacciItem {

    data class Base(
        private val currentValue: Int,
        private val parentValue: FibonacciItem
    )
}