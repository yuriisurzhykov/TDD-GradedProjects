/*
 * Copyright (c) 2023-2024 Yurii Surzhykov.
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

package com.yuriisurzhykov.tddgraded.fibonacci

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.yuriisurzhykov.tddgraded.fibonacci.presentation.FibonacciScreen
import com.yuriisurzhykov.tddgraded.fibonacci.presentation.FibonacciViewModel
import com.yuriisurzhykov.wonderscalculator.ui.theme.FibonacciCalculatorTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FibonacciActivity : ComponentActivity() {

    private val viewModel by viewModels<FibonacciViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FibonacciCalculatorTheme {
                FibonacciScreen(
                    modifier = Modifier.fillMaxSize(),
                    api = viewModel
                )
            }
        }
    }
}