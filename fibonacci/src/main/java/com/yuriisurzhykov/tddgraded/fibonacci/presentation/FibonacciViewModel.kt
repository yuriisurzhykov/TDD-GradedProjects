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

package com.yuriisurzhykov.tddgraded.fibonacci.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yuriisurzhykov.tddgraded.core.Dispatchers
import com.yuriisurzhykov.tddgraded.fibonacci.data.FibonacciItem
import com.yuriisurzhykov.tddgraded.fibonacci.domain.FibonacciUseCase
import com.yuriisurzhykov.tddgraded.fibonacci.domain.StringToIntegerMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.plus
import javax.inject.Inject

@HiltViewModel
class FibonacciViewModel @Inject constructor(
    private val dispatchers: Dispatchers,
    private val fibonacciFlowGenerator: FibonacciUseCase,
    private val mapper: StringToIntegerMapper
) : ViewModel(), FibonacciScreenApi {

    private val fibonacciFlow = MutableSharedFlow<List<FibonacciItem>>()
    private var collectJobs: CoroutineScope? = null

    override fun startGenerate(amount: String) {
        collectJobs?.cancel()
        collectJobs = viewModelScope + SupervisorJob()
        dispatchers.launchBackground(collectJobs!!) {
            fibonacciFlowGenerator.fibonacciFlow(number = mapper.map(amount))
            fibonacciFlow.collect()
        }
    }

    override fun fibonacciSequence(): Flow<List<FibonacciItem>> = fibonacciFlow
}