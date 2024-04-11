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

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.onEach

interface FibonacciFlowGenerator {

    suspend fun fibonacciFlow(sequenceAmount: Int): Flow<FibonacciItem>

    class Base(
        private val generator: FibonacciGenerator,
        private val eachDelay: Long
    ) : FibonacciFlowGenerator {
        override suspend fun fibonacciFlow(sequenceAmount: Int): Flow<FibonacciItem> = generator
            .generate(sequenceAmount)
            .asFlow()
            .onEach { delay(eachDelay) }
    }
}