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

package com.yuriisurzhykov.tddgraded.palindrome

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.yuriisurzhykov.tddgraded.palindrome.base.AbstractTest
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@LargeTest
@HiltAndroidTest
class PalindromeCheckPageTest : AbstractTest() {

    @Test
    fun test_palindrome_check_screen() = PalindromeCheckPage().run {
        input.typeText("Madam")
        button.click()
        resultText.checkText(getString(R.string.message_input_text_palindrome))
        orientationLandscape()
        resultText.checkText(getString(R.string.message_input_text_palindrome))
        orientationPortrait()
        input.typeText("qwerty")
        button.click()
        resultText.checkText(getString(R.string.message_input_text_not_palindrome))
        orientationLandscape()
        resultText.checkText(getString(R.string.message_input_text_not_palindrome))
        orientationPortrait()
        input.typeText("")
        button.click()
        resultText.checkText(getString(R.string.message_input_text_empty))
    }
}
