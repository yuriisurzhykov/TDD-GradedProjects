package com.yuriisurzhykov.tddgraded.primenumber.data

interface PrimeNumberCheckResult {

    abstract class Abstract : PrimeNumberCheckResult {
        override fun equals(other: Any?): Boolean {
            if (other == null) return false
            return this::class.java == other::class.java
        }

        override fun hashCode(): Int {
            return javaClass.hashCode()
        }
    }

    class Prime : Abstract()
    class NotPrime : Abstract()
    class InvalidRange : Abstract()
}