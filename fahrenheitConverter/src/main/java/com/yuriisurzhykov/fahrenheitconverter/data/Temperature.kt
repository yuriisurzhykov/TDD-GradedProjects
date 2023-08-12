package com.yuriisurzhykov.fahrenheitconverter.data

interface Temperature {

    interface Mapper<T : Any> {
        fun map(value: Int, symbol: String): T

        object Value : Mapper<Int> {
            override fun map(value: Int, symbol: String) = value
        }

        object StringValue : Mapper<String> {
            override fun map(value: Int, symbol: String) = "$value °$symbol"
        }
    }

    fun <T : Any> map(mapper: Mapper<T>): T

    abstract class Abstract(
        private val symbol: String,
        private val value: Int
    ) : Temperature {

        override fun <T : Any> map(mapper: Mapper<T>): T {
            return mapper.map(value, symbol)
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Abstract

            if (symbol != other.symbol) return false
            if (value != other.value) return false

            return true
        }

        override fun hashCode(): Int {
            var result = symbol.hashCode()
            result = 31 * result + value.hashCode()
            return result
        }

        override fun toString(): String {
            return "${this::class.simpleName}(symbol='$symbol', value=$value)"
        }
    }

    class Fahrenheit(value: Int) : Abstract("F", value)

    class Celsius(value: Int) : Abstract("C", value)
}