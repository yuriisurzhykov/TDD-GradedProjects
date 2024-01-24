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

import com.yuriisurzhykov.tddgraded.fibonacci.domain.StringToIntegerMapper
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class StringToIntegerMapperTest {
    @Test(expected = IllegalArgumentException::class)
    fun `test empty string`() = runTest {
        val mapper = StringToIntegerMapper.Base()
        mapper.map("")
    }

    @Test
    fun `test negative string value`() = runTest {
        val mapper = StringToIntegerMapper.Base()

        val actual = mapper.map("-1231")
        val expected = -1231

        assertEquals(expected, actual)
    }

    @Test
    fun `test positive string value`() = runTest {
        val mapper = StringToIntegerMapper.Base()

        val actual = mapper.map("1412")
        val expected = 1412

        assertEquals(expected, actual)
    }

    @Test
    fun `test positive string value and dot`() = runTest {
        val mapper = StringToIntegerMapper.Base()

        val actual = mapper.map("123.151")
        val expected = 123

        assertEquals(expected, actual)
    }

    @Test
    fun `test string with spaces`() = runTest {
        val mapper = StringToIntegerMapper.Base()

        val actual = mapper.map("123 151")
        val expected = 123151

        assertEquals(expected, actual)
    }

    @Test
    fun `test string with spaces and negative`() = runTest {
        val mapper = StringToIntegerMapper.Base()

        val actual = mapper.map("-123 151")
        val expected = -123151

        assertEquals(expected, actual)
    }

    @Test
    fun `test string with spaces and negative and dot`() = runTest {
        val mapper = StringToIntegerMapper.Base()

        val actual = mapper.map("-123.151")
        val expected = -123

        assertEquals(expected, actual)
    }

    @Test
    fun `test string with spaces and negative and dot and 0`() = runTest {
        val mapper = StringToIntegerMapper.Base()

        val actual = mapper.map("-123.0")
        val expected = -123

        assertEquals(expected, actual)
    }

    @Test
    fun `test string with spaces and negative and dot and 0 and 1`() = runTest {
        val mapper = StringToIntegerMapper.Base()

        val actual = mapper.map("-123.01")
        val expected = -123

        assertEquals(expected, actual)
    }

    @Test
    fun `test positive string with dot and spaces`() = runTest {
        val mapper = StringToIntegerMapper.Base()

        val actual = mapper.map("123 . 151")
        val expected = 123

        assertEquals(expected, actual)
    }
}