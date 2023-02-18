import com.yuriisurzhykov.tddgraded.primenumber.data.PrimeNumberCheckResult
import com.yuriisurzhykov.tddgraded.primenumber.domain.PrimeNumberCheckUseCase
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class TestPrimeNumberCheckUseCase {

    private lateinit var useCase: PrimeNumberCheckUseCase

    @Before
    fun initDeps() {
        useCase = PrimeNumberCheckUseCase.Base()
    }

    @Test
    fun test_if_number_is_prime_in_valid_range() {
        val input = 7
        assertTrue(useCase.isPrimeNumber(input) is PrimeNumberCheckResult.Prime)
    }

    @Test
    fun test_if_number_is_not_prime_valid_range() {
        val input = 6
        assertTrue(useCase.isPrimeNumber(input) is PrimeNumberCheckResult.NotPrime)
    }

    @Test
    fun test_if_number_is_outside_valid_range() {
        var input: Long = (Integer.MAX_VALUE).toLong()
        input += 10
        assertTrue(useCase.isPrimeNumber(input) is PrimeNumberCheckResult.InvalidRange)
    }
}