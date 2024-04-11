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

package com.yuriisurzhykov.tddgraded.fibonacci

import com.yuriisurzhykov.tddgraded.fibonacci.data.FibonacciItem
import com.yuriisurzhykov.tddgraded.fibonacci.domain.FibonacciUseCase
import com.yuriisurzhykov.tddgraded.fibonacci.domain.StringToIntegerMapper
import com.yuriisurzhykov.tddgraded.fibonacci.presentation.FibonacciViewModel
import com.yuriisurzhykov.testingcore.FakeDispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class FibonacciViewModelTest {

    @Test
    fun `test view model initial value`() = runTest {
        val dispatchers = FakeDispatchers()
        val useCase = FakeFibonacciUseCase()
        val mapper = StringToIntegerMapper.Base()
        val viewModel = FibonacciViewModel(dispatchers, useCase, mapper)
        val actualFlow = viewModel.screenStateFlow().toList()
        val expectedFlow = emptyFlow<List<FibonacciItem>>().toList()

        assertEquals(expectedFlow, actualFlow)
    }

    @Test
    fun `test view model initial generating sequentially`() = runTest {
        val dispatchers = FakeDispatchers()

        val useCase = FakeFibonacciUseCase()
        val mapper = StringToIntegerMapper.Base()
        val viewModel = FibonacciViewModel(dispatchers, useCase, mapper)

        val expectedFlow = flowOf(
            listOf(FibonacciItem.Base(1)),
            listOf(FibonacciItem.Base(1), FibonacciItem.Base(1))
        )
        useCase.expectedFlow = expectedFlow

        viewModel.startGenerate("2")

        val actualFlow = viewModel.screenStateFlow()

        assertEquals(expectedFlow, actualFlow)
    }
}

private class FakeFibonacciUseCase : FibonacciUseCase {

    lateinit var expectedFlow: Flow<List<FibonacciItem>>

    override suspend fun fibonacciFlow(number: Int): Flow<List<FibonacciItem>> = expectedFlow
}