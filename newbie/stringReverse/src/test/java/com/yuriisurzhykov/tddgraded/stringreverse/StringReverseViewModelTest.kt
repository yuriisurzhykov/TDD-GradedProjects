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

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.yuriisurzhykov.tddgraded.core.data.Dispatchers
import com.yuriisurzhykov.tddgraded.stringreverse.data.IStringReverseState
import com.yuriisurzhykov.tddgraded.stringreverse.data.StringReverseCommunication
import com.yuriisurzhykov.tddgraded.stringreverse.domain.IStringReverseUseCase
import com.yuriisurzhykov.tddgraded.stringreverse.domain.ManualStringReverseUseCase
import com.yuriisurzhykov.tddgraded.stringreverse.presentation.StringReverseViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
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
        val expected = IStringReverseState.Success("txet tupni resu emoS")
        viewModel.applyUserInput(userInputTest)
        assertEquals(expected, communication.getCurrentValue())
        assertEquals(1, communication.getCallsCount())
    }

    @Test
    fun `test view model for user input and recreate fragment`() {
        val useCase: IStringReverseUseCase = ManualStringReverseUseCase()
        val communication = FakeStringReverseCommunication()
        val dispatchers: Dispatchers = TestCoroutineDispatchers()
        val viewModel = StringReverseViewModel(
            useCase = useCase,
            communication = communication,
            dispatchers = dispatchers
        )
        val userInputTest = "Some user input text"
        val expected = IStringReverseState.Success("txet tupni resu emoS")
        viewModel.observe(FakeViewLifecycleOwner(Lifecycle.State.STARTED), FakeObserver())
        viewModel.applyUserInput(userInputTest)
        assertEquals(expected, communication.getCurrentValue())
        assertEquals(1, communication.getCallsCount())
        viewModel.observe(FakeViewLifecycleOwner(Lifecycle.State.STARTED), FakeObserver())
        assertEquals(2, communication.getObserveCount())
        assertEquals(1, communication.getCallsCount())
        assertEquals(expected, communication.getCurrentValue())
    }

}

class FakeObserver : Observer<IStringReverseState> {
    override fun onChanged(value: IStringReverseState) {
    }
}

private class FakeStringReverseCommunication : StringReverseCommunication {

    private lateinit var value: IStringReverseState
    private var callCount: Int = 0
    private var observeCount: Int = 0

    override fun observe(owner: LifecycleOwner, observer: Observer<IStringReverseState>) {
        observeCount++
    }

    override fun put(value: IStringReverseState) {
        this.value = value
        callCount++
    }

    fun getCallsCount() = callCount

    fun getCurrentValue() = value

    fun getObserveCount() = observeCount
}

private class FakeViewLifecycleOwner(private val state: Lifecycle.State) : LifecycleOwner {

    private var observerCount = 0
    override val lifecycle: Lifecycle = object : Lifecycle() {
        override fun addObserver(observer: LifecycleObserver) {
            observerCount++
        }

        override fun removeObserver(observer: LifecycleObserver) {
            observerCount--
        }

        override val currentState: State = state
    }
}


@OptIn(ExperimentalCoroutinesApi::class)
private class TestCoroutineDispatchers :
    Dispatchers.Abstract(UnconfinedTestDispatcher(), UnconfinedTestDispatcher())