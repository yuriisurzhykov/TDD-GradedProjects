import com.yuriisurzhykov.tddgraded.primenumber.data.PrimeNumberCheckResult
import com.yuriisurzhykov.tddgraded.primenumber.domain.PrimeNumberCheckUseCase
import org.junit.Assert.assertEquals
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
        assertEquals(useCase.isPrimeNumber(input.toLong()), PrimeNumberCheckResult.Prime())
    }

    @Test
    fun test_if_number_is_not_prime_valid_range() {
        val input = 6
        assertEquals(useCase.isPrimeNumber(input.toLong()), PrimeNumberCheckResult.NotPrime())
    }

    @Test
    fun test_if_number_is_outside_valid_range() {
        var input: Long = (Integer.MAX_VALUE).toLong()
        input += 10
        assertEquals(useCase.isPrimeNumber(input), PrimeNumberCheckResult.InvalidRange())
    }
}