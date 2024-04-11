/*
 * Copyright (c) 2024 Yurii Surzhykov.
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

package com.yuriisurzhykov.tddgraded.fibonacci.presentation

import androidx.annotation.StringRes
import com.yuriisurzhykov.tddgraded.fibonacci.data.FibonacciItem
import kotlinx.coroutines.flow.Flow

interface FibonacciScreenState {

    object Idle : FibonacciScreenState

    data class Error(@StringRes val reason: Int) : FibonacciScreenState

    class Generating(val itemsFlow: Flow<List<FibonacciItem>>) : FibonacciScreenState
}