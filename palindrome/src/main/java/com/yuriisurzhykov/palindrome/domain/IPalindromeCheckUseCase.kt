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

package com.yuriisurzhykov.palindrome.domain

import com.yuriisurzhykov.palindrome.data.IPalindromeCheckState
import com.yuriisurzhykov.palindrome.data.PalindromeInputEntity
import com.yuriisurzhykov.tddgraded.presentation.resources.StringResource

interface IPalindromeCheckUseCase {

    suspend fun check(input: PalindromeInputEntity): IPalindromeCheckState

    class Base(private val punctuationRemove: IPunctuationRemove) : IPalindromeCheckUseCase {
        override suspend fun check(input: PalindromeInputEntity): IPalindromeCheckState {
            val stringResource = StringResource.Base()

            val reverse = punctuationRemove.clearPunctuation(input.inputString.reversed()).lowercase()
            val straight = punctuationRemove.clearPunctuation(input.inputString).lowercase()
            if (straight.isBlank()) return IPalindromeCheckState.InputError(stringResource)
            val isValid = reverse == straight
            return if (isValid) IPalindromeCheckState.Success(stringResource)
            else IPalindromeCheckState.CheckError(stringResource)
        }
    }
}