package com.yuriisurzhykov.fahrenheitconverter.presentation

import androidx.lifecycle.ViewModel
import com.yuriisurzhykov.fahrenheitconverter.domain.TemperatureConverterUseCase
import com.yuriisurzhykov.tddgraded.core.Dispatchers

class TemperatureConvertViewModel(
    private val useCase: TemperatureConverterUseCase,
    private val dispatchers: Dispatchers
) : ViewModel() {
}