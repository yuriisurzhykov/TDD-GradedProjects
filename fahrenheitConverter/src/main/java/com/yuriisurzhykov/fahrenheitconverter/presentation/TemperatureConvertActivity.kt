package com.yuriisurzhykov.fahrenheitconverter.presentation

import android.os.Bundle
import com.yuriisurzhykov.tddgraded.presentation.AbstractFragmentActivity

class TemperatureConvertActivity : AbstractFragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        openFragment(TemperatureConvertFragment(), "converter_fragment")
    }
}