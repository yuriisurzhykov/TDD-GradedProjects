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

import com.yuriisurzhykov.tddgraded.fibonacci.data.FibonacciGenerator
import com.yuriisurzhykov.tddgraded.fibonacci.data.FibonacciItem
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class FibonacciGeneratorTest {

    @Test
    fun `generate sequence for one`() = runBlocking {
        val generator = FibonacciGenerator.Base()

        val actual = generator.generate(1)
        val expected = listOf(FibonacciItem.Base(1))

        assertEquals(expected, actual)
    }

    @Test(expected = IllegalStateException::class)
    fun `generate sequence for zero`(): Unit = runBlocking {
        val generator = FibonacciGenerator.Base()

        generator.generate(0)
    }

    @Test(expected = IllegalStateException::class)
    fun `generateSequence for negative`(): Unit = runBlocking {
        val generator = FibonacciGenerator.Base()

        generator.generate(-12)
    }

    @Test
    fun `generateSequence for positive`() = runBlocking {
        val generator = FibonacciGenerator.Base()

        val actual = generator.generate(5)
        val expected =
            listOf(
                FibonacciItem.Base(1),
                FibonacciItem.Base(1),
                FibonacciItem.Base(2),
                FibonacciItem.Base(3),
                FibonacciItem.Base(5)
            )
        assertEquals(expected, actual)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `generateSequence for when param is about max integer`(): Unit = runBlocking {
        val generator = FibonacciGenerator.Base()

        generator.generate(Int.MAX_VALUE)
    }
}