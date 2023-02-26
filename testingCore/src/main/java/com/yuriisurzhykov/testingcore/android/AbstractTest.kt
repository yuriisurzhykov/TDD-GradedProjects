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

package com.yuriisurzhykov.testingcore.android

import android.app.Activity
import android.content.pm.ActivityInfo
import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.clearText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import dagger.hilt.android.testing.HiltAndroidRule
import org.junit.Before
import org.junit.Rule

abstract class AbstractTest<T : Activity>(activityClass: Class<T>) {

    @get:Rule(order = 1)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 2)
    var activityRule = ActivityScenarioRule(activityClass)

    protected lateinit var activity: T

    @Before
    fun init() {
        activityRule.scenario.onActivity { activity = it }
    }

    protected fun ViewInteraction.typeText(text: String) {
        perform(clearText(), ViewActions.typeText(text))
        closeSoftKeyboard()
    }

    protected fun ViewInteraction.checkText(text: String) {
        check(matches(withText(text)))
    }

    protected fun ViewInteraction.click() {
        perform(ViewActions.click())
    }

    protected fun getString(id: Int) =
        InstrumentationRegistry.getInstrumentation().context.getString(id)

    protected fun orientationPortrait() {
        onView(isRoot()).perform(
            ScreenOrientationChangeAction(
                ActivityInfo.SCREEN_ORIENTATION_PORTRAIT,
                activity
            )
        )
    }

    protected fun orientationLandscape() {
        onView(isRoot()).perform(
            ScreenOrientationChangeAction(
                ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE,
                activity
            )
        )
    }
}