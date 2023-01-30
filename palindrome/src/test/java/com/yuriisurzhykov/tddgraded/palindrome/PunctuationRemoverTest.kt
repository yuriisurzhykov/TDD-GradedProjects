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

import com.yuriisurzhykov.tddgraded.palindrome.domain.IPunctuationRemove
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class PunctuationRemoverTest {

    private lateinit var remover: IPunctuationRemove

    @Before
    fun init() {
        remover = IPunctuationRemove.Base()
    }

    @Test
    fun `test punctuation remove with commas`() {
        val testData = "A man, a plan, a canal: Panama!!!"
        val expected = "AmanaplanacanalPanama"
        val actual = remover.clearPunctuation(testData)
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `test punctuation remove from single word`() {
        val testData = "Testcase"
        val expected = "Testcase"
        val actual = remover.clearPunctuation(testData)
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `test remove all punctuations from only punctuations`() {
        val testData = ":;,.,:''\")(12345677890]][[}[{}}~`/,/\\"
        val expected = ""
        val actual = remover.clearPunctuation(testData)
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `test remove punctuation from cyrillic sentence`() {
        val testData = "Семён пошёл домой, когда-только пришлось;Вася весь в сопли;%111,,!"
        val expected = "СемёнпошёлдомойкогдатолькопришлосьВасявесьвсопли"
        val actual = remover.clearPunctuation(testData)
        Assert.assertEquals(expected, actual)
    }
}