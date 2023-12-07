package com.yuriisurzhykov.tddgraded.fibonacci

import android.os.Bundle
import com.yuriisurzhykov.tddgraded.presentation.AbstractFragmentActivity

class FibonacciActivity : AbstractFragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        openFragment()
    }
}