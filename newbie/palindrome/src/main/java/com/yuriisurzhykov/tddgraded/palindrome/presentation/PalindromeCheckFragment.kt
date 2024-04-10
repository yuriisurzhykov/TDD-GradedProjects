/*
 * Copyright (c) 2023 Yurii Surzhykov.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.yuriisurzhykov.tddgraded.palindrome.presentation

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.yuriisurzhykov.tddgraded.palindrome.R
import com.yuriisurzhykov.tddgraded.palindrome.data.IPalindromeCheckState
import com.yuriisurzhykov.tddgraded.palindrome.data.PalindromeInputEntity
import com.yuriisurzhykov.tddgraded.presentation.AbstractStyleFragment
import com.yuriisurzhykov.tddgraded.presentation.delegates.viewsktx.findView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PalindromeCheckFragment : AbstractStyleFragment(R.layout.fragment_palindrome_check) {

    private val inputField: EditText by findView(R.id.palindrome_input)
    private val checkButton: Button by findView(R.id.check_button)
    private val checkResultText: TextView by findView(R.id.check_result_text)

    private val viewModel: PalindromeCheckViewModel by viewModels()

    override fun getTitle(): String = getString(R.string.title_palindrome_check_screen)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.observe(viewLifecycleOwner, checkStateObserver)
        if (savedInstanceState != null) {
            val string = savedInstanceState.getString(ARG_INPUT).orEmpty()
            inputField.setText(string)
            viewModel.applyInput(PalindromeInputEntity(string))
        }
        checkButton.setOnClickListener(checkClickListener)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(ARG_INPUT, inputField.text.toString())
    }

    private val checkStateObserver = Observer<IPalindromeCheckState> { state ->
        state.apply(checkResultText)
    }

    private val checkClickListener = View.OnClickListener {
        viewModel.applyInput(PalindromeInputEntity(inputField.text.toString()))
    }

    companion object {
        private const val ARG_INPUT = "argument_input"
    }
}