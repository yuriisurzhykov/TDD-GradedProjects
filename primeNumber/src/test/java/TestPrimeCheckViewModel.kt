import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.yuriisurzhykov.tddgraded.core.Dispatchers
import com.yuriisurzhykov.tddgraded.primenumber.data.PrimeNumberCheckResult
import com.yuriisurzhykov.tddgraded.primenumber.domain.PrimeNumberCheckCommunication
import com.yuriisurzhykov.tddgraded.primenumber.domain.PrimeNumberCheckUseCase
import com.yuriisurzhykov.tddgraded.primenumber.presentation.PrimeCheckViewModel
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.Assert.assertEquals
import org.junit.Test

class TestPrimeCheckViewModel {

    @Test
    fun test_initialize_viewModel_get_result_and_recreate() {
        val useCase = PrimeNumberCheckUseCase.Base()
        val dispatchers = TestDispatchers()
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
        val dispatchers = TestDispatchers()
        val communication = TestPrimeCheckCommunication()
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
        val dispatchers = TestDispatchers()
        val communication = TestPrimeCheckCommunication()
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

private class TestDispatchers :
    Dispatchers.Abstract(UnconfinedTestDispatcher(), UnconfinedTestDispatcher())

private class TestPrimeCheckCommunication: PrimeNumberCheckCommunication {

    private var callCount: Int = 0
    private lateinit var stateResult: PrimeNumberCheckResult

    override fun put(result: PrimeNumberCheckResult) {
        callCount++
        stateResult = result
    }

    override fun observe(owner: LifecycleOwner, observer: Observer<PrimeNumberCheckResult>) {
    }

    fun getStateResult() = stateResult

    fun getCallCounts() = callCount
}

private class FakeLifecycleOwner(private val state: Lifecycle.State): LifecycleOwner {
    override fun getLifecycle(): Lifecycle {
        return object : Lifecycle() {
            override fun addObserver(observer: LifecycleObserver) {

            }

            override fun removeObserver(observer: LifecycleObserver) {
            }

            override fun getCurrentState(): State = state
        }
    }
}

private class FakePrimeNumberObserver: Observer<PrimeNumberCheckResult> {

    private var value: PrimeNumberCheckResult? = null

    override fun onChanged(t: PrimeNumberCheckResult?) {
        value = t
    }

    fun getCurrentValue() = value
}