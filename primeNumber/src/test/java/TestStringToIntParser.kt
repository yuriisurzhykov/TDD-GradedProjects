import com.yuriisurzhykov.tddgraded.primenumber.domain.StringToIntParser
import org.junit.Assert.assertEquals
import org.junit.Test

class TestStringToIntParser {
    @Test
    fun test_parse_correct_integer_value() {
        val input = "31"
        val expected = 31
        val useCase = StringToIntParser.Base()
        assertEquals(expected, useCase.parse(input))
    }

    @Test
    fun test_parse_more_than_max_integer() {
        val input = "2291939388123812"
        val expected = -1
        val useCase = StringToIntParser.Base()
        assertEquals(expected, useCase.parse(input))
    }

    @Test
    fun test_parse_negative_value() {
        val input = "-12"
        val expected = -12
        val useCase = StringToIntParser.Base()
        assertEquals(expected, useCase.parse(input))
    }
}