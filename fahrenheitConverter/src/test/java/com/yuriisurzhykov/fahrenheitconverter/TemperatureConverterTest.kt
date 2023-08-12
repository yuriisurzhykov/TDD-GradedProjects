package com.yuriisurzhykov.fahrenheitconverter

import com.yuriisurzhykov.fahrenheitconverter.data.Temperature
import com.yuriisurzhykov.fahrenheitconverter.domain.TemperatureConverter
import org.junit.Assert.assertEquals
import org.junit.Test

class TemperatureConverterTest {

    @Test
    fun `convert F above zero to C`() {
        val converter = TemperatureConverter.FahrenheitToC()
        val fTemperature = Temperature.Fahrenheit(212)
        val expected = Temperature.Celsius(100)
        val actual = converter.convert(fTemperature)
        assertEquals(expected, actual)
    }

    @Test
    fun `convert F below zero to C`() {
        val converter = TemperatureConverter.FahrenheitToC()
        val fTemperature = Temperature.Fahrenheit(-31)
        val expected = Temperature.Celsius(-35)
        val actual = converter.convert(fTemperature)
        assertEquals(expected, actual)
    }

    @Test
    fun `convert F is zero to F`() {
        val converter = TemperatureConverter.FahrenheitToC()
        val fTemperature = Temperature.Fahrenheit(0)
        val expected = Temperature.Celsius(-17)
        val actual = converter.convert(fTemperature)
        assertEquals(expected, actual)
    }

    @Test
    fun `convert C above zero to F`() {
        val converter = TemperatureConverter.FahrenheitToC()
        val fTemperature = Temperature.Celsius(10)
        val expected = Temperature.Fahrenheit(50)
        val actual = converter.convert(fTemperature)
        assertEquals(expected, actual)
    }

    @Test
    fun `convert C below zero to F`() {
        val converter = TemperatureConverter.FahrenheitToC()
        val fTemperature = Temperature.Celsius(-5)
        val expected = Temperature.Fahrenheit(23)
        val actual = converter.convert(fTemperature)
        assertEquals(expected, actual)
    }

    @Test
    fun `convert C is zero to F`() {
        val converter = TemperatureConverter.FahrenheitToC()
        val fTemperature = Temperature.Celsius(0)
        val expected = Temperature.Fahrenheit(32)
        val actual = converter.convert(fTemperature)
        assertEquals(expected, actual)
    }
}