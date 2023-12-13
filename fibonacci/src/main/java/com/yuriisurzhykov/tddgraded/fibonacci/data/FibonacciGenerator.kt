package com.yuriisurzhykov.tddgraded.fibonacci.data

`import java.lang.IllegalArgumentException

interface FibonacciGenerator {


    suspend fun generate(sequenceAmount: Int): List<FibonacciItem>

    class Base : FibonacciGenerator {
        override suspend fun generate(sequenceAmount: Int): List<FibonacciItem> {
            val resultList = mutableListOf<FibonacciItem>()

            if (sequenceAmount <= 0) error("Sequence number must be at least 1!")

            var counter = 1
            var prevItem: FibonacciItem = FibonacciItem.Base(0, null)
            var currentItem: FibonacciItem = FibonacciItem.Base(1, null)

            resultList.add(currentItem)

            try {
                while (counter < sequenceAmount) {
                    val new = currentItem + prevItem
                    prevItem = currentItem
                    currentItem = new
                    resultList.add(new)
                    ++counter
                }
            } catch (e: OutOfMemoryError) {
                throw IllegalArgumentException("Value $sequenceAmount too big to generate sequence!")
            }
            return resultList
        }
    }
}