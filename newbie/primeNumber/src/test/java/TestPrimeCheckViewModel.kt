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
        val communication = TestPrimeCheckCommunication()
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
        assertEquals(communication.getStateResult(), expected)
        assertEquals(observer1.getCurrentValue(), expected)
        assertEquals(communication.getCallCounts(), 1)
        val observer2 = FakePrimeNumberObserver()
        viewModel.observe(FakeLifecycleOwner(Lifecycle.State.RESUMED), observer2)
        assertEquals(observer2.getCurrentValue(), expected)
        assertEquals(communication.getCallCounts(), 1)
    }

    @Test
    fun test_initialize_viewModel_pass_invalid_data() {
        val useCase = PrimeNumberCheckUseCase.Base()
        val dispatchers = FakeDispatchers()
        val communication = TestPrimeCheckCommunication()
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
        assertEquals(communication.getStateResult(), expected)
        assertEquals(observer1.getCurrentValue(), expected)
        assertEquals(communication.getCallCounts(), 1)
        val observer2 = FakePrimeNumberObserver()
        viewModel.observe(FakeLifecycleOwner(Lifecycle.State.RESUMED), observer2)
        assertEquals(observer2.getCurrentValue(), expected)
        assertEquals(communication.getCallCounts(), 1)
    }

    @Test
    fun test_viewModel_with_pass_correct_data() {
        val useCase = PrimeNumberCheckUseCase.Base()
        val dispatchers = FakeDispatchers()
        val communication = TestPrimeCheckCommunication()
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
        assertEquals(communication.getStateResult(), expected)
        assertEquals(observer1.getCurrentValue(), expected)
        assertEquals(communication.getCallCounts(), 1)
        val observer2 = FakePrimeNumberObserver()
        viewModel.observe(FakeLifecycleOwner(Lifecycle.State.RESUMED), observer2)
        assertEquals(observer2.getCurrentValue(), expected)
        assertEquals(communication.getCallCounts(), 1)
    }

}

private class TestPrimeCheckCommunication : FakeCommunication.Abstract<PrimeNumberCheckResult>(),
    PrimeNumberCheckCommunication

private class FakePrimeNumberObserver : FakeObserver.Abstract<PrimeNumberCheckResult>()