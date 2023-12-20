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

package com.yuriisurzhykov.tddgraded.fibonacci

import com.yuriisurzhykov.tddgraded.fibonacci.data.FibonacciFlowGenerator
import com.yuriisurzhykov.tddgraded.fibonacci.data.FibonacciItem
import com.yuriisurzhykov.tddgraded.fibonacci.domain.FibonacciUseCase
import com.yuriisurzhykov.tddgraded.fibonacci.domain.SequenceNumberInputValidator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.toCollection
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class FibonacciUseCaseTest {

    @Test
    fun `check gives flow if input valid`() = runTest {
        val testFlow = listOf(FibonacciItem.Base(1, null)).asFlow()
        val fakeGenerator = FakeFlowGenerator(testFlow)
        val fakeValidator = FakeValidator()
        val useCase = FibonacciUseCase.Base(fakeGenerator, fakeValidator)

        fakeValidator.valid = { true }

        var actual: List<FibonacciItem> = emptyList()
        useCase.fibonacciFlow(1).collect {
            actual = it
        }
        val expected: MutableList<FibonacciItem> = mutableListOf()
        testFlow.toCollection(expected)

        assertEquals(expected, actual)
    }

    @Test
    fun `check gives flow if input valid and number is 2`() = runTest {
        val testFlow = listOf(
            FibonacciItem.Base(1, FibonacciItem.Base(1, null)),
            FibonacciItem.Base(1, null)
        ).asFlow()
        val fakeGenerator = FakeFlowGenerator(testFlow)
        val fakeValidator = FakeValidator()
        val useCase = FibonacciUseCase.Base(fakeGenerator, fakeValidator)

        fakeValidator.valid = { true }

        var actual: List<FibonacciItem> = emptyList()
        useCase.fibonacciFlow(2).collect {
            actual = it
        }
        val expected: MutableList<FibonacciItem> = mutableListOf()
        testFlow.toCollection(expected)

        assertEquals(expected, actual)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `check throws exception if input invalid`() = runTest {
        val fakeGenerator = FakeFlowGenerator(emptyList<FibonacciItem>().asFlow())
        val fakeValidator = FakeValidator()
        val useCase = FibonacciUseCase.Base(fakeGenerator, fakeValidator)

        fakeValidator.valid = { false }
        useCase.fibonacciFlow(-1).collect()
    }

    @Test(expected = RuntimeException::class)
    fun `check use case throws exception if it occur`() = runTest {
        val fakeGenerator = FakeFlowGenerator(emptyList<FibonacciItem>().asFlow())
        val fakeValidator = FakeValidator()
        val useCase = FibonacciUseCase.Base(fakeGenerator, fakeValidator)

        fakeValidator.valid = { throw RuntimeException("") }
        useCase.fibonacciFlow(-1).collect()
    }
}

private class FakeValidator : SequenceNumberInputValidator {
    var valid: () -> Boolean = {
        false
    }

    override fun validate(sequenceNumber: Int): Boolean = valid.invoke()
}

private class FakeFlowGenerator(
    private val flow: Flow<FibonacciItem>
) : FibonacciFlowGenerator {
    override suspend fun fibonacciFlow(sequenceAmount: Int) = flow
}