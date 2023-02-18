package com.yuriisurzhykov.tddgraded.primenumber.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.yuriisurzhykov.tddgraded.presentation.AbstractStyleFragment
import com.yuriisurzhykov.tddgraded.primenumber.R

class PrimeNumberCheckFragment : AbstractStyleFragment(R.layout.fragment_prime_check) {

    private val viewModel: PrimeCheckViewModel by viewModels()

    override fun getTitle(): String = resources.getString(R.string.title_screen_prime_check)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}