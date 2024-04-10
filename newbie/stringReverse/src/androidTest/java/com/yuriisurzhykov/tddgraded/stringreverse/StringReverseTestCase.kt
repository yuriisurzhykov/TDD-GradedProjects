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

package com.yuriisurzhykov.tddgraded.stringreverse

import androidx.test.espresso.action.ViewActions.clearText
import androidx.test.filters.LargeTest
import com.yuriisurzhykov.tddgraded.stringreverse.base.AbstractTest
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
@LargeTest
@HiltAndroidTest
class StringReverseTestCase : AbstractTest() {

    @Test
    fun test_user_input_with_screen_rotation() = StringReversePage().run {
        val inputText = "Some user input value for text field!"
        val reversed = "!dleif txet rof eulav tupni resu emoS"
        input.typeText(inputText)
        responeText.checkText(reversed)
        orientationLandscape()
        input.checkText(reversed)
        responeText.checkText(reversed)
        input.perform(clearText())
        responeText.checkText("")
    }
}