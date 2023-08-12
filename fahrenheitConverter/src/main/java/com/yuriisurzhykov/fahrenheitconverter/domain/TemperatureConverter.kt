package com.yuriisurzhykov.fahrenheitconverter.domain

import com.yuriisurzhykov.fahrenheitconverter.data.Temperature

interface TemperatureConverter {

    fun convert(from: Temperature): Temperature

    class FahrenheitToC : TemperatureConverter {
        override fun convert(from: Temperature): Temperature {
            val fDegree = from.map(Temperature.Mapper.Value)
            val cDegree = (fDegree.toFloat() - 32) * (5.0 / 9)
            return Temperature.Celsius(cDegree.toInt())
        }
    }

    class CToFahrenheit : TemperatureConverter {
        override fun convert(from: Temperature): Temperature {
            val cDegree = from.map(Temperature.Mapper.Value)
            val fDegree = cDegree * (9.0 / 5) + 32
            return Temperature.Fahrenheit(fDegree.toInt())
        }
    }
}