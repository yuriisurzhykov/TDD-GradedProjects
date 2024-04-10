package com.yuriisurzhykov.tddgraded.primenumber.domain

interface StringToIntParser {
    fun parse(value: String): Int

    abstract class Abstract : StringToIntParser {
        override fun parse(value: String): Int {
            return try {
                Integer.parseInt(value)
            } catch (e: NumberFormatException) {
                -1
            }
        }
    }

    class Base : Abstract()
}