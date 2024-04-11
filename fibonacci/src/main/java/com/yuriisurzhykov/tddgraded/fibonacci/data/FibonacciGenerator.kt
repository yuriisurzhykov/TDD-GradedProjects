/*
 * Copyright (c) 2023 Yurii Surzhykov.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.yuriisurzhykov.tddgraded.fibonacci.data

interface FibonacciGenerator {


    suspend fun generate(sequenceAmount: Int): List<FibonacciItem>

    class Base : FibonacciGenerator {
        override suspend fun generate(sequenceAmount: Int): List<FibonacciItem> {
            val resultList = mutableListOf<FibonacciItem>()

            if (sequenceAmount <= 0) error("Sequence number must be at least 1!")

            var counter = 1
            var prevItem: FibonacciItem = FibonacciItem.Base(0)
            var currentItem: FibonacciItem = FibonacciItem.Base(1)

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