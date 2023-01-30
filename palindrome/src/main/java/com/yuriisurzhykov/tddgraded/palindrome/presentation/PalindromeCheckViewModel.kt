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

package com.yuriisurzhykov.tddgraded.palindrome.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yuriisurzhykov.tddgraded.palindrome.data.IPalindromeCheckState
import com.yuriisurzhykov.tddgraded.palindrome.data.PalindromeInputEntity
import com.yuriisurzhykov.tddgraded.palindrome.domain.IPalindromeCheckUseCase
import com.yuriisurzhykov.tddgraded.palindrome.domain.PalindromeCheckCommunication
import com.yuriisurzhykov.tddgraded.core.Communication
import com.yuriisurzhykov.tddgraded.core.Dispatchers
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PalindromeCheckViewModel @Inject constructor(
    private val useCase: IPalindromeCheckUseCase,
    private val checkCommunication: PalindromeCheckCommunication,
    private val dispatchers: Dispatchers
) : ViewModel(), IPalindromeCheckViewModel, Communication.Observe<IPalindromeCheckState> {

    override fun observe(owner: LifecycleOwner, observer: Observer<IPalindromeCheckState>) {
        checkCommunication.observe(owner, observer)
    }

    override fun applyInput(data: PalindromeInputEntity) {
        dispatchers.launchUi(viewModelScope) {
            val checkState = useCase.check(data)
            checkCommunication.put(checkState)
        }
    }
}