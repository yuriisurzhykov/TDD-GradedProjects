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
import com.yuriisurzhykov.tddgraded.fibonacci.data.FibonacciGenerator
import com.yuriisurzhykov.tddgraded.fibonacci.data.FibonacciItem
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class FibonacciFlowGeneratorTest {

    @Test
    fun `check generates flow from actual generator with delay`() = runTest {
        val delay = 100L
        val results = listOf(FibonacciItem.Base(1, null))
        val fakeGenerator = FakeGenerator(results)
        val generator = FibonacciFlowGenerator.Base(delay, fakeGenerator)

        val actual = generator.fibonacciFlow(1).collect()
        val expected = results.asFlow().collect()

        assertEquals(expected, actual)
    }
}

private class FakeGenerator(
    private val testList: List<FibonacciItem>
) : FibonacciGenerator {
    override suspend fun generate(sequenceAmount: Int): List<FibonacciItem> {
        return testList
    }
}