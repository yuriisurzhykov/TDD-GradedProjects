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

interface FibonacciItem {

    interface Mapper<T : Any> {
        fun map(currentValue: Int): T

        class Plus(
            private val assignValue: Int,
        ) : Mapper<FibonacciItem> {
            override fun map(currentValue: Int): FibonacciItem {
                return Base(assignValue + currentValue)
            }
        }
    }

    fun <T : Any> map(mapper: Mapper<T>): T

    operator fun plus(item: FibonacciItem): FibonacciItem

    data class Base(
        private val currentValue: Int
    ) : FibonacciItem {
        override fun <T : Any> map(mapper: Mapper<T>): T = mapper.map(currentValue)

        override fun plus(item: FibonacciItem): FibonacciItem =
            item.map(Mapper.Plus(currentValue))
    }
}