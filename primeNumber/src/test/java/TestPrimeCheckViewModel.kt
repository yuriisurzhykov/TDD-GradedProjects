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

import androidx.lifecycle.Lifecycle
import com.yuriisurzhykov.tddgraded.primenumber.data.PrimeNumberCheckResult
import com.yuriisurzhykov.tddgraded.primenumber.domain.PrimeNumberCheckCommunication
import com.yuriisurzhykov.tddgraded.primenumber.domain.PrimeNumberCheckUseCase
import com.yuriisurzhykov.tddgraded.primenumber.domain.StringToIntParser
import com.yuriisurzhykov.tddgraded.primenumber.presentation.PrimeCheckViewModel
import com.yuriisurzhykov.testingcore.FakeCommunication
import com.yuriisurzhykov.testingcore.FakeDispatchers
import com.yuriisurzhykov.testingcore.FakeLifecycleOwner
import com.yuriisurzhykov.testingcore.FakeObserver
import org.junit.Assert.assertEquals
import org.junit.Test

class TestPrimeCheckViewModel {

    @Test
    fun test_initialize_viewModel_get_result_and_recreate() {
        val useCase = PrimeNumberCheckUseCase.Base()
        val dispatchers = FakeDispatchers()
        val communication = FakePrimeCheckCommunication()
        val stringParser = StringToIntParser.Base()
        val viewModel = PrimeCheckViewModel(
            primeCheckUseCase = useCase,
            dispatchers = dispatchers,
            communication = communication,
            stringParser = stringParser
        )
        val testData = "12"
        val expected = PrimeNumberCheckResult.NotPrime()
        val observer1 = FakePrimeNumberObserver()
        viewModel.observe(FakeLifecycleOwner(Lifecycle.State.RESUMED), observer1)
        viewModel.checkNumber(testData)
        assertEquals(expected, communication.getStateResult())
        assertEquals(expected, observer1.getCurrentValue())
        assertEquals(1, communication.getCallCounts())
        val observer2 = FakePrimeNumberObserver()
        viewModel.observe(FakeLifecycleOwner(Lifecycle.State.RESUMED), observer2)
        assertEquals(expected, observer2.getCurrentValue())
        assertEquals(1, communication.getCallCounts())
    }

    @Test
    fun test_initialize_viewModel_pass_invalid_data() {
        val useCase = PrimeNumberCheckUseCase.Base()
        val dispatchers = FakeDispatchers()
        val communication = FakePrimeCheckCommunication()
        val stringParser = StringToIntParser.Base()
        val viewModel = PrimeCheckViewModel(
            primeCheckUseCase = useCase,
            dispatchers = dispatchers,
            communication = communication,
            stringParser = stringParser
        )
        val testData = "1891238912371237"
        val expected = PrimeNumberCheckResult.InvalidRange()
        val observer1 = FakePrimeNumberObserver()
        viewModel.observe(FakeLifecycleOwner(Lifecycle.State.RESUMED), observer1)
        viewModel.checkNumber(testData)

        assertEquals(expected, communication.getStateResult())
        assertEquals(expected, observer1.getCurrentValue())
        assertEquals(1, communication.getCallCounts())

        val observer2 = FakePrimeNumberObserver()
        viewModel.observe(FakeLifecycleOwner(Lifecycle.State.RESUMED), observer2)
        assertEquals(expected, observer2.getCurrentValue())
        assertEquals(1, communication.getCallCounts())
    }

    @Test
    fun test_viewModel_with_pass_correct_data() {
        val useCase = PrimeNumberCheckUseCase.Base()
        val dispatchers = FakeDispatchers()
        val communication = FakePrimeCheckCommunication()
        val stringParser = StringToIntParser.Base()
        val viewModel = PrimeCheckViewModel(
            primeCheckUseCase = useCase,
            dispatchers = dispatchers,
            communication = communication,
            stringParser = stringParser
        )
        val testData = "3"
        val expected = PrimeNumberCheckResult.Prime()
        val observer1 = FakePrimeNumberObserver()
        viewModel.observe(FakeLifecycleOwner(Lifecycle.State.RESUMED), observer1)
        viewModel.checkNumber(testData)
        assertEquals(expected, communication.getStateResult())
        assertEquals(expected, observer1.getCurrentValue())
        assertEquals(1, communication.getCallCounts())
        val observer2 = FakePrimeNumberObserver()
        viewModel.observe(FakeLifecycleOwner(Lifecycle.State.RESUMED), observer2)
        assertEquals(expected, observer2.getCurrentValue())
        assertEquals(1, communication.getCallCounts())
    }

}

private class FakePrimeCheckCommunication : FakeCommunication.Abstract<PrimeNumberCheckResult>(),
    PrimeNumberCheckCommunication

private class FakePrimeNumberObserver : FakeObserver<PrimeNumberCheckResult>()