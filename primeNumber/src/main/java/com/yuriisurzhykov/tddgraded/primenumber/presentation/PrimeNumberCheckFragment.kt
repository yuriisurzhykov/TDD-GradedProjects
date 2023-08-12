package com.yuriisurzhykov.tddgraded.primenumber.presentation

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.yuriisurzhykov.tddgraded.presentation.AbstractStyleFragment
import com.yuriisurzhykov.tddgraded.presentation.delegates.viewsktx.findView
import com.yuriisurzhykov.tddgraded.primenumber.R
import com.yuriisurzhykov.tddgraded.primenumber.data.PrimeNumberCheckResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PrimeNumberCheckFragment : AbstractStyleFragment(R.layout.fragment_prime_check) {

    private val viewModel: PrimeCheckViewModel by viewModels()
    private val numberInput: EditText by findView(R.id.number_input)
    private val checkResult: TextView by findView(R.id.number_check_result)
    private val checkButton: Button by findView(R.id.check_button)

    override fun getTitle(): String? = resources.getString(R.string.title_screen_prime_check)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkButton.setOnClickListener(checkClickListener)
        viewModel.observe(viewLifecycleOwner, observer)
    }

    private val checkClickListener = View.OnClickListener {
        viewModel.checkNumber(numberInput.text.toString().orEmpty())
    }

    private val observer = Observer<PrimeNumberCheckResult> { result ->
        result?.applyToTextView(checkResult)
    }
}