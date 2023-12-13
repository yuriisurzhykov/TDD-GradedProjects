package com.yuriisurzhykov.tddgraded.fibonacci.data

interface FibonacciItem {

    interface Mapper<T : Any> {
        fun map(currentValue: Int, parentValue: FibonacciItem?): T

        class Plus(
            private val assignValue: Int,
            private val newParent: FibonacciItem
        ) : Mapper<FibonacciItem> {
            override fun map(currentValue: Int, parentValue: FibonacciItem?): FibonacciItem {
                return Base(assignValue + currentValue, newParent)
            }
        }
    }

    fun <T : Any> map(mapper: Mapper<T>): T

    operator fun plus(item: FibonacciItem): FibonacciItem

    data class Base(
        private val currentValue: Int,
        private val parentValue: FibonacciItem?
    ) : FibonacciItem {
        override fun <T : Any> map(mapper: Mapper<T>): T = mapper.map(currentValue, parentValue)

        override fun plus(item: FibonacciItem): FibonacciItem =
            item.map(Mapper.Plus(currentValue, this))
    }
}