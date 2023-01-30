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

package com.yuriisurzhykov.palindrome

import com.yuriisurzhykov.palindrome.data.IPalindromeCheckState
import com.yuriisurzhykov.palindrome.data.PalindromeInputEntity
import com.yuriisurzhykov.palindrome.domain.IPalindromeCheckUseCase
import com.yuriisurzhykov.palindrome.presentation.PalindromeCheckViewModel
import com.yuriisurzhykov.tddgraded.core.Communication
import com.yuriisurzhykov.tddgraded.core.Dispatchers
import junit.framework.TestCase.assertTrue
import org.junit.Test
import kotlin.properties.Delegates.notNull

class PalindromeCheckViewModelTest {

    @Test
    fun `check view model input error`() {
        val useCase = FakePalindromeCheckUseCase(IPalindromeCheckState.InputError())
        val communication = FakePalindromeCommunication()
        val viewModel = PalindromeCheckViewModel(useCase, communication, Dispatchers.Base())
        viewModel.applyInput(PalindromeInputEntity("madam"))
        assertTrue(communication.checkIsState(IPalindromeCheckState.InputError()))
        assertTrue(communication.checkCallCount(1))
        viewModel.applyInput(PalindromeInputEntity(""))
        assertTrue(communication.checkIsState(IPalindromeCheckState.InputError()))
        assertTrue(communication.checkCallCount(2))
    }

    @Test
    fun `check view model success`() {
        val useCase = FakePalindromeCheckUseCase(IPalindromeCheckState.Success())
        val communication = FakePalindromeCommunication()
        val viewModel = PalindromeCheckViewModel(useCase, communication, Dispatchers.Base())
        viewModel.applyInput(PalindromeInputEntity("madam"))
        assertTrue(communication.checkIsState(IPalindromeCheckState.Success()))
        assertTrue(communication.checkCallCount(1))
    }

    @Test
    fun `check view model have check error`() {
        val useCase = FakePalindromeCheckUseCase(IPalindromeCheckState.CheckError())
        val communication = FakePalindromeCommunication()
        val viewModel = PalindromeCheckViewModel(useCase, communication, Dispatchers.Base())
        viewModel.applyInput(PalindromeInputEntity("asdjkdfjksdfg"))
        assertTrue(communication.checkIsState(IPalindromeCheckState.CheckError()))
        assertTrue(communication.checkCallCount(1))
    }

}

private class FakePalindromeCheckUseCase constructor(
    private val checkState: IPalindromeCheckState
) : IPalindromeCheckUseCase {
    override suspend fun check(input: PalindromeInputEntity) = checkState
}

private class FakePalindromeCommunication : Communication.Abstract<IPalindromeCheckState>() {

    private var value: IPalindromeCheckState by notNull()
    private var callCount: Int = 0

    override fun put(value: IPalindromeCheckState) {
        callCount++
        this.value = value
    }

    fun checkIsState(state: IPalindromeCheckState) = state == value

    fun checkCallCount(count: Int) = callCount == count
}