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

package com.yuriisurzhykov.tddgraded.palindrome

import com.yuriisurzhykov.tddgraded.palindrome.data.IPalindromeCheckState
import com.yuriisurzhykov.tddgraded.palindrome.data.PalindromeInputEntity
import com.yuriisurzhykov.tddgraded.palindrome.domain.IPalindromeCheckUseCase
import com.yuriisurzhykov.tddgraded.palindrome.domain.IPunctuationRemove
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class PalindromeCheckUseCaseTest {

    private lateinit var checker: IPalindromeCheckUseCase
    private lateinit var remover: IPunctuationRemove

    @Before
    fun init() {
        remover = IPunctuationRemove.Base()
        checker = IPalindromeCheckUseCase.Base(remover)
    }

    @Test
    fun `test palindrome check with success`() = runBlocking {
        val testData = PalindromeInputEntity("Madam")
        val actual = checker.check(testData)
        Assert.assertTrue(actual is IPalindromeCheckState.Success)
    }

    @Test
    fun `test palindrome check with empty input`() = runBlocking {
        val testData = PalindromeInputEntity("")
        val actual = checker.check(testData)
        Assert.assertTrue(actual is IPalindromeCheckState.InputError)
    }

    @Test
    fun `test palindrome check with failed result`() = runBlocking {
        val testData = PalindromeInputEntity("something for test")
        val actual = checker.check(testData)
        Assert.assertTrue(actual is IPalindromeCheckState.CheckError)
    }

    @Test
    fun `test palindrome check with palindrome sentence success`() = runBlocking {
        val testData = PalindromeInputEntity("A man, a plan, a canal: Panama!")
        val actual = checker.check(testData)
        Assert.assertTrue(actual is IPalindromeCheckState.Success)
    }

    @Test
    fun `test palindrome from only punctuations string`() = runBlocking {
        val testData = PalindromeInputEntity(".,.':;;")
        val actual = checker.check(testData)
        Assert.assertTrue(actual is IPalindromeCheckState.InputError)
    }

    @Test
    fun `test palindrome with cyrillic sentence success`() = runBlocking {
        val testData = PalindromeInputEntity("торт, с кофе - не фокстрот!")
        val actual = checker.check(testData)
        Assert.assertTrue(actual is IPalindromeCheckState.Success)
    }

    @Test
    fun `test palindrome with cyrillic sentence failed`() = runBlocking {
        val testData = PalindromeInputEntity("торт, с чаем - не фокстрот!")
        val actual = checker.check(testData)
        Assert.assertTrue(actual is IPalindromeCheckState.CheckError)
    }

}