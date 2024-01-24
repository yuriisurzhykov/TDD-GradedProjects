/*
 * Copyright (c) 2023-2024 Yurii Surzhykov.
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
import com.yuriisurzhykov.tddgraded.fibonacci.R
import com.yuriisurzhykov.tddgraded.fibonacci.domain.FibonacciUseCase
import com.yuriisurzhykov.tddgraded.fibonacci.domain.StringToIntegerMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.plus
import javax.inject.Inject

@HiltViewModel
class FibonacciViewModel @Inject constructor(
    private val dispatchers: Dispatchers,
    private val fibonacciUseCase: FibonacciUseCase,
    private val mapper: StringToIntegerMapper
) : ViewModel(), FibonacciScreenApi {

    private val fibonacciFlow = MutableStateFlow<FibonacciScreenState>(FibonacciScreenState.Idle)
    private var collectScope: CoroutineScope? = null

    override fun startGenerate(amount: String) {
        collectScope?.cancel()
        collectScope = viewModelScope + SupervisorJob()
        dispatchers.launchBackground(collectScope!!) {
            try {
                val flow = fibonacciUseCase.fibonacciFlow(number = mapper.map(amount))
                fibonacciFlow.emit(FibonacciScreenState.Generating(flow))
            } catch (e: OutOfMemoryError) {
                fibonacciFlow.emit(FibonacciScreenState.Error(R.string.error_out_of_memory))
            } catch (e: IllegalArgumentException) {
                fibonacciFlow.emit(FibonacciScreenState.Error(R.string.error_invalid_fibonacci_item))
            } catch (e: Exception) {
                fibonacciFlow.emit(FibonacciScreenState.Error(R.string.error_unknown_happened))
            }
        }
    }

    override fun screenStateFlow(): Flow<FibonacciScreenState> = fibonacciFlow

    override fun onCleared() {
        collectScope?.cancel()
    }
}