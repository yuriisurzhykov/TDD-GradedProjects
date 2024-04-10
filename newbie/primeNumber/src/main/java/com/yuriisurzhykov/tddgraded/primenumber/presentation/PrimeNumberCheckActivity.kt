package com.yuriisurzhykov.tddgraded.primenumber.presentation

import android.os.Bundle
import com.yuriisurzhykov.tddgraded.presentation.AbstractFragmentActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PrimeNumberCheckActivity : AbstractFragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            openFragment(PrimeNumberCheckFragment(), "prime_check_fragment")
        }
    }
}