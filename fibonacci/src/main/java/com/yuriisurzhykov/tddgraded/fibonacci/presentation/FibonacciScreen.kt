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

package com.yuriisurzhykov.tddgraded.fibonacci.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.unit.sp
import com.yuriisurzhykov.tddgraded.fibonacci.R
import com.yuriisurzhykov.tddgraded.fibonacci.data.FibonacciItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

interface FibonacciScreenApi {

    fun startGenerate(amount: String)

    fun fibonacciSequence(): Flow<List<FibonacciItem>>

    object Empty : FibonacciScreenApi {
        override fun startGenerate(amount: String) {

        }

        override fun fibonacciSequence(): Flow<List<FibonacciItem>> = emptyFlow()
    }
}

@Composable
fun FibonacciScreen(modifier: Modifier = Modifier, api: FibonacciScreenApi) {
    Box(modifier = modifier) {
        var fibonacciAmount by rememberSaveable {
            mutableStateOf("")
        }
        val focusManager = LocalFocusManager.current
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .align(Alignment.Center),
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
            })
        )
        Button(
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .align(Alignment.BottomCenter),
            onClick = {
                api.startGenerate(fibonacciAmount)
                focusManager.clearFocus()
            },
            enabled = fibonacciAmount.isNotEmpty()
        ) {
            Text(text = stringResource(id = R.string.fibonacci_sequence_generate_button))
        }
        val items = api.fibonacciSequence().collectAsState(initial = emptyList()).value
        LazyColumn {
            items(items = items) {
                Text(text = "Item $it")
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