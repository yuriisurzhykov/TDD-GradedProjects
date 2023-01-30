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
import com.yuriisurzhykov.tddgraded.palindrome.domain.PalindromeCheckCommunication
import com.yuriisurzhykov.tddgraded.palindrome.presentation.PalindromeCheckViewModel
import com.yuriisurzhykov.tddgraded.core.Communication
import com.yuriisurzhykov.tddgraded.core.Dispatchers
import com.yuriisurzhykov.tddgraded.presentation.resources.StringResource
import junit.framework.TestCase
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.Before
import org.junit.Test

class PalindromeCheckViewModelTest {

    private lateinit var dispatchers: Dispatchers
    private var stringResource = StringResource.Base()

    @Before
    fun initialize() {
        dispatchers = FakeDispatchers()
    }

    @Test
    fun `check view model input error`() {
        val testData = IPalindromeCheckState.InputError(stringResource)
        val useCase = FakePalindromeCheckUseCase(testData)
        val communication = FakePalindromeCommunication()
        val viewModel = PalindromeCheckViewModel(useCase, communication, dispatchers)
        viewModel.applyInput(PalindromeInputEntity("madam"))
        TestCase.assertEquals(true, communication.checkIsState(testData))
        TestCase.assertEquals(true, communication.checkCallCount(1))
        viewModel.applyInput(PalindromeInputEntity(""))
        TestCase.assertEquals(true, communication.checkIsState(testData))
        TestCase.assertEquals(true, communication.checkCallCount(2))
    }

    @Test
    fun `check view model success`() {
        val testData = IPalindromeCheckState.Success(stringResource)
        val useCase = FakePalindromeCheckUseCase(testData)
        val communication = FakePalindromeCommunication()
        val viewModel = PalindromeCheckViewModel(useCase, communication, dispatchers)
        viewModel.applyInput(PalindromeInputEntity("madam"))
        TestCase.assertEquals(true, communication.checkIsState(testData))
        TestCase.assertEquals(true, communication.checkCallCount(1))
    }

    @Test
    fun `check view model have check error`() {
        val testData = IPalindromeCheckState.CheckError(stringResource)
        val useCase = FakePalindromeCheckUseCase(testData)
        val communication = FakePalindromeCommunication()
        val viewModel = PalindromeCheckViewModel(useCase, communication, dispatchers)
        viewModel.applyInput(PalindromeInputEntity("asdjkdfjksdfg"))
        TestCase.assertEquals(true, communication.checkIsState(testData))
        TestCase.assertEquals(true, communication.checkCallCount(1))
    }

}

private class FakePalindromeCheckUseCase constructor(
    private val checkState: IPalindromeCheckState
) : IPalindromeCheckUseCase {
    override suspend fun check(input: PalindromeInputEntity) = checkState
}

private class FakePalindromeCommunication : Communication.Abstract<IPalindromeCheckState>(),
    PalindromeCheckCommunication {

    private lateinit var value: IPalindromeCheckState
    private var callCount: Int = 0

    override fun put(value: IPalindromeCheckState) {
        callCount++
        this.value = value
    }

    fun checkIsState(state: IPalindromeCheckState) = state == value

    fun checkCallCount(count: Int) = callCount == count
}

private class FakeDispatchers : Dispatchers.Abstract(UnconfinedTestDispatcher(), UnconfinedTestDispatcher())