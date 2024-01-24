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

import com.yuriisurzhykov.tddgraded.fibonacci.domain.SequenceNumberInputValidator
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class SequenceNumberInputValidatorTest {

    @Test
    fun `test valid input when positive number`() = runTest {
        val validator = SequenceNumberInputValidator.Base()

        val actual = validator.validate(10)
        val expected = true

        assertEquals(expected, actual)
    }

    @Test
    fun `test valid input when negative number`() = runTest {
        val validator = SequenceNumberInputValidator.Base()

        val actual = validator.validate(-10)
        val expected = false

        assertEquals(expected, actual)
    }

    @Test
    fun `test valid input when zero`() = runTest {
        val validator = SequenceNumberInputValidator.Base()

        val actual = validator.validate(0)
        val expected = false

        assertEquals(expected, actual)
    }

    @Test
    fun `test invalid input when max value`() = runTest {
        val validator = SequenceNumberInputValidator.Base()

        val actual = validator.validate(Long.MAX_VALUE.toInt())
        val expected = false

        assertEquals(expected, actual)
    }

    @Test
    fun `test valid input when 93`() = runTest {
        val validator = SequenceNumberInputValidator.Base()

        val actual = validator.validate(93)
        val expected = true

        assertEquals(expected, actual)
    }

    @Test
    fun `test invalid input when 94`() = runTest {
        val validator = SequenceNumberInputValidator.Base()

        val actual = validator.validate(94)
        val expected = false

        assertEquals(expected, actual)
    }
}
