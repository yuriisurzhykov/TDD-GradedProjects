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

package com.yuriisurzhykov.tddgraded.stringreverse

import com.yuriisurzhykov.tddgraded.stringreverse.domain.IStringReverseUseCase
import com.yuriisurzhykov.tddgraded.stringreverse.domain.ManualStringReverseUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class StringReverseUseCaseTest {

    @Test
    fun `test reverse string with normal length`() = runBlocking {
        val useCase: IStringReverseUseCase = ManualStringReverseUseCase()
        val testData = "Some string for test!"
        val expected = "!tset rof gnirts emoS"
        assertEquals(expected, useCase.reverse(testData))
    }

    @Test
    fun `test reverse string with empty string`() = runBlocking {
        val useCase: IStringReverseUseCase = ManualStringReverseUseCase()
        val testData = ""
        val expected = ""
        assertEquals(expected, useCase.reverse(testData))
    }


}