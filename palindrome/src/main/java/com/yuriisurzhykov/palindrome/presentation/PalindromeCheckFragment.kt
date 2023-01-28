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

package com.yuriisurzhykov.palindrome.presentation

import com.yuriisurzhykov.palindrome.R
import com.yuriisurzhykov.tddgraded.presentation.AbstractStyleFragment

class PalindromeCheckFragment : AbstractStyleFragment(R.layout.fragment_palindrome_check) {
    override fun getTitle() = getString(R.string.title_palindrome_check_screen)
}