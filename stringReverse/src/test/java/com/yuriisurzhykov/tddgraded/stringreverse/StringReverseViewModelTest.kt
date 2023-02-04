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

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.yuriisurzhykov.tddgraded.core.Dispatchers
import kotlinx.coroutines.test.TestCoroutineContext
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.Assert.assertEquals
import org.junit.Test

class StringReverseViewModelTest {

    @Test
    fun `test view model for user input`() {
        val useCase: IStringReverseUseCase = ManualStringReverseUseCase()
        val communication = FakeStringReverseCommunication()
        val dispatchers: Dispatchers = TestCoroutineDispatchers()
        val viewModel = StringReverseViewModel(
            useCase = useCase,
            communication = communication,
            dispatchers = dispatchers
        )
        val userInputTest = "Some user input text"
        val expected = "txet tupni resu emoS"
        viewModel.applyUserInput(userInputTest)
        assertEquals(expected, communication.getCurrentValue())
        assertEquals(1, communication.getCallsCount())
    }

    @Test
    fun `test view model for user input and recreate fragment`() {
        val useCase: IStringReverseUseCase = ManualStringReverseUseCase()
        val communication: StringReverseCommunication = FakeStringReverseCommunication()
        val dispatchers: Dispatchers = TestCoroutineDispatchers()
        val viewModel = StringReverseViewModel(
            useCase = useCase,
            communication = communication,
            dispatchers = dispatchers
        )
        val userInputTest = "Some user input text"
        val expected = "txet tupni resu emoS"
        viewModel.observe()
        viewModel.applyUserInput(userInputTest)
        assertEquals(expected, communication.getCurrentValue())
        assertEquals(1, communication.getCallsCount())
    }

}

private class FakeStringReverseCommunication : StringReverseCommunication {

    private lateinit var value: IStringReverseState
    private var callCount: Int = 0

    override fun observe(owner: LifecycleOwner, observer: Observer<IStringReverseState>) {

    }

    override fun put(value: IStringReverseState) {
        this.value = value
        callCount++
    }

    fun getCallsCount() = callCount

    fun getCurrentValue() = value
}


private class TestCoroutineDispatchers :
    Dispatchers.Abstract(UnconfinedTestDispatcher(), UnconfinedTestDispatcher())