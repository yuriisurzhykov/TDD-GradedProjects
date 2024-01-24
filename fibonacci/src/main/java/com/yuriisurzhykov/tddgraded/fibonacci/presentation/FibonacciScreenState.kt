/*
 * Copyright (c) 2024 Yurii Surzhykov.
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

package com.yuriisurzhykov.tddgraded.fibonacci.presentation

import androidx.annotation.StringRes
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.yuriisurzhykov.tddgraded.fibonacci.R
import com.yuriisurzhykov.tddgraded.fibonacci.data.FibonacciItem
import kotlinx.coroutines.flow.Flow

interface FibonacciScreenState {

    @Composable
    fun Render(modifier: Modifier)

    object Idle : FibonacciScreenState {
        @Composable
        override fun Render(modifier: Modifier) {
            Text(text = stringResource(id = R.string.fibonacci_idle_text), modifier = modifier)
        }
    }

    class Error(@StringRes val reason: Int) : FibonacciScreenState {
        @Composable
        override fun Render(modifier: Modifier) {
            Text(
                modifier = modifier,
                text = stringResource(id = R.string.error_message_format).format(stringResource(id = reason))
            )
        }
    }

    class Generating(private val itemsFlow: Flow<List<FibonacciItem>>) : FibonacciScreenState {
        @Composable
        override fun Render(modifier: Modifier) {
            val items = itemsFlow.collectAsStateWithLifecycle(emptyList()).value
            LazyColumn(modifier = modifier) {
                items(items) {
                    Text(text = it.toString())
                }
            }
        }
    }
}