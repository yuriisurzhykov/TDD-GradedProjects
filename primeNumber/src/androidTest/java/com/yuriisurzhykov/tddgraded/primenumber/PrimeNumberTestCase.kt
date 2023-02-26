package com.yuriisurzhykov.tddgraded.primenumber

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.yuriisurzhykov.tddgraded.primenumber.presentation.PrimeNumberCheckActivity
import com.yuriisurzhykov.testingcore.android.AbstractTest
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
@LargeTest
class PrimeNumberTestCase :
    AbstractTest<PrimeNumberCheckActivity>(PrimeNumberCheckActivity::class.java) {

    @Test
    fun test_prime_number_check_for_correct_input() = PrimeNumberPage().run {
        input.typeText("29")
        button.click()
        resultView.checkText(getString(R.string.label_number_is_prime))
        orientationLandscape()
        resultView.checkText(getString(R.string.label_number_is_prime))
    }
}