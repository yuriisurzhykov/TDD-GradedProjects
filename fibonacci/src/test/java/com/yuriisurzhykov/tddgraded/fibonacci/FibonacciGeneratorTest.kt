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
        val expected = listOf(FibonacciItem.Base(1, null))

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

        val actual = generator.generate(4)
        val expected =
            listOf(
                FibonacciItem.Base(1, null),
                FibonacciItem.Base(1, FibonacciItem.Base(1, null)),
                FibonacciItem.Base(2, FibonacciItem.Base(1, FibonacciItem.Base(1, null))),
                FibonacciItem.Base(
                    3,
                    FibonacciItem.Base(2, FibonacciItem.Base(1, FibonacciItem.Base(1, null)))
                ),
            )
        assertEquals(expected, actual)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `generateSequence for when param is about max integer`(): Unit = runBlocking {
        val generator = FibonacciGenerator.Base()

        generator.generate(Int.MAX_VALUE)
    }
}