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

package com.yuriisurzhykov.tddgraded.fibonacci.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.yuriisurzhykov.tddgraded.fibonacci.R
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

interface FibonacciScreenApi {

    fun startGenerate(amount: String)

    fun screenStateFlow(): Flow<FibonacciScreenState>

    object Empty : FibonacciScreenApi {
        override fun startGenerate(amount: String) = Unit
        override fun screenStateFlow() = emptyFlow<FibonacciScreenState>()
    }
}

@Composable
fun FibonacciScreen(modifier: Modifier = Modifier, api: FibonacciScreenApi) {
    ConstraintLayout(modifier = modifier) {
        val (input, button, list) = createRefs()
        var fibonacciAmount by rememberSaveable {
            mutableStateOf("")
        }
        val focusManager = LocalFocusManager.current
        var textFieldAlignment by remember { mutableStateOf(Alignment.Center) }
        OutlinedTextField(
            modifier = Modifier
                .constrainAs(input) {
                    top.linkTo(parent.top, margin = 16.dp)
                    start.linkTo(parent.start, margin = 16.dp)
                    end.linkTo(button.start, margin = 16.dp)
                },
            value = fibonacciAmount,
            onValueChange = { fibonacciAmount = it },
            placeholder = {
                Text(text = stringResource(id = R.string.fibonacci_sequence_input_hint))
            },
            textStyle = TextStyle(fontSize = 20.sp),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Go,
                keyboardType = KeyboardType.Number
            ),
            keyboardActions = KeyboardActions(onGo = {
                api.startGenerate(fibonacciAmount)
                focusManager.clearFocus()
                textFieldAlignment = Alignment.TopCenter
            })
        )
        Button(
            modifier = Modifier
                .constrainAs(button) {
                    top.linkTo(parent.top, margin = 16.dp)
                    start.linkTo(input.end, margin = 16.dp)
                    end.linkTo(parent.end, margin = 16.dp)
                },
            onClick = {
                api.startGenerate(fibonacciAmount)
                focusManager.clearFocus()
            },
            enabled = fibonacciAmount.isNotEmpty()
        ) {
            Text(text = stringResource(id = R.string.fibonacci_sequence_generate_button))
        }

        val state = api
            .screenStateFlow()
            .collectAsState(initial = FibonacciScreenState.Idle)
            .value

        DynamicFibonacciState(
            state = state,
            modifier = Modifier
                .constrainAs(list) {
                    top.linkTo(input.bottom, margin = 16.dp)
                    start.linkTo(parent.start, margin = 16.dp)
                    end.linkTo(parent.end, margin = 16.dp)
                    bottom.linkTo(parent.bottom, margin = 16.dp)
                }
        )
    }
}

@Composable
fun DynamicFibonacciState(
    state: FibonacciScreenState,
    modifier: Modifier = Modifier
) {
    when (state) {
        is FibonacciScreenState.Idle -> {
            Text(
                text = stringResource(id = R.string.fibonacci_idle_text),
                modifier = modifier
            )
        }

        is FibonacciScreenState.Error -> {
            Text(
                modifier = modifier,
                text = stringResource(id = R.string.error_message_format)
                    .format(stringResource(id = state.reason))
            )
        }

        is FibonacciScreenState.Generating -> {
            val items = state.itemsFlow.collectAsStateWithLifecycle(emptyList()).value
            LazyColumn(modifier = modifier) {
                items(items) {
                    Text(text = it.toString())
                }
            }
        }
    }
}

@Preview
@Composable
fun SequenceInputScreenPreview() {
    FibonacciScreen(
        modifier = Modifier.fillMaxSize(),
        api = FibonacciScreenApi.Empty
    )
}