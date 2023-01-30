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

package com.yuriisurzhykov.palindrome.data

import android.widget.TextView
import androidx.annotation.StringRes
import com.yuriisurzhykov.palindrome.R
import com.yuriisurzhykov.tddgraded.presentation.resources.StringResource

abstract class IPalindromeCheckState constructor(
    private val stringResource: StringResource,
    @StringRes private val stringId: Int
) {

    open fun apply(textView: TextView) {
        textView.text = stringResource.getResource(stringId, textView.context)
    }

    class Success(stringResource: StringResource) :
        IPalindromeCheckState(stringResource, R.string.message_input_text_palindrome)

    class InputError(stringResource: StringResource) :
        IPalindromeCheckState(stringResource, R.string.message_input_text_empty)

    class CheckError(stringResource: StringResource) :
        IPalindromeCheckState(stringResource, R.string.message_input_text_not_palindrome)
}