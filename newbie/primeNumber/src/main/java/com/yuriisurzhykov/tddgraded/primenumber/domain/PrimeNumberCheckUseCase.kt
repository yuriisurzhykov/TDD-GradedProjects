package com.yuriisurzhykov.tddgraded.primenumber.domain

import com.yuriisurzhykov.tddgraded.primenumber.data.PrimeNumberCheckResult
import kotlin.math.sqrt

interface PrimeNumberCheckUseCase {

    fun isPrimeNumber(number: Long): PrimeNumberCheckResult

    abstract class Abstract : PrimeNumberCheckUseCase {
        override fun isPrimeNumber(number: Long): PrimeNumberCheckResult {
            when {
                number > Integer.MAX_VALUE -> return PrimeNumberCheckResult.InvalidRange()
                number < 0 -> return PrimeNumberCheckResult.InvalidRange()
                number in 0..1 -> return PrimeNumberCheckResult.NotPrime()
                number == 2L -> return PrimeNumberCheckResult.Prime()
                number % 2L == 0L -> return PrimeNumberCheckResult.NotPrime()
                else -> {
                    for (i in 3..sqrt(number.toFloat()).toLong() step 2) {
                        if (number % i == 0L) {
                            return PrimeNumberCheckResult.NotPrime()
                        }
                    }
                }
            }
            return PrimeNumberCheckResult.Prime()
        }
    }

    class Base : Abstract()
}