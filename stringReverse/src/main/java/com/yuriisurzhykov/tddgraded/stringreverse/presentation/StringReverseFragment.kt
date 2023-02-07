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

package com.yuriisurzhykov.tddgraded.stringreverse.presentation

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.yuriisurzhykov.tddgraded.presentation.AbstractStyleFragment
import com.yuriisurzhykov.tddgraded.presentation.delegates.viewsktx.findView
import com.yuriisurzhykov.tddgraded.stringreverse.R
import com.yuriisurzhykov.tddgraded.stringreverse.data.IStringReverseState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StringReverseFragment : AbstractStyleFragment(R.layout.fragment_string_reverse) {

    private val viewModel: StringReverseViewModel by viewModels()

    private val inputField: EditText by findView(R.id.text_input)
    private val responseTextView: TextView by findView(R.id.text_response)

    override fun getTitle(): String = getString(R.string.title_text_reverse_screen)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.observe(viewLifecycleOwner, responseObserver)
        inputField.addTextChangedListener { viewModel.applyUserInput(it.toString()) }
    }

    private val responseObserver = Observer<IStringReverseState> { state ->
        state.apply(responseTextView)
    }
}