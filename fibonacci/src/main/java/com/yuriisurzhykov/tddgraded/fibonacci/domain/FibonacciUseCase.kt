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

package com.yuriisurzhykov.tddgraded.fibonacci.domain

import com.yuriisurzhykov.tddgraded.fibonacci.data.FibonacciFlowGenerator
import com.yuriisurzhykov.tddgraded.fibonacci.data.FibonacciItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface FibonacciUseCase {

    suspend fun fibonacciFlow(number: Int): Flow<FibonacciItem>

    class Base @Inject constructor(
        private val flowGenerator: FibonacciFlowGenerator,
        private val validator: SequenceNumberInputValidator
    ) : FibonacciUseCase {
        override suspend fun fibonacciFlow(number: Int): Flow<FibonacciItem> {
            return if (validator.validate(number)) {
                flowGenerator.fibonacciFlow(number)
            } else throw IllegalArgumentException("Number must be positive!")
        }
    }
}
