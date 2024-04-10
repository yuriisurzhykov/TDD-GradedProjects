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

package com.yuriisurzhykov.tddgraded.stringreverse.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yuriisurzhykov.tddgraded.core.data.Dispatchers
import com.yuriisurzhykov.tddgraded.presentation.Communication
import com.yuriisurzhykov.tddgraded.stringreverse.data.IStringReverseState
import com.yuriisurzhykov.tddgraded.stringreverse.data.StringReverseCommunication
import com.yuriisurzhykov.tddgraded.stringreverse.domain.IStringReverseUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StringReverseViewModel @Inject constructor(
    private val useCase: IStringReverseUseCase,
    private val communication: StringReverseCommunication,
    private val dispatchers: Dispatchers
) : ViewModel(), Communication.Observe<IStringReverseState>, IStringReverseViewModel {

    override fun observe(owner: LifecycleOwner, observer: Observer<IStringReverseState>) {
        communication.observe(owner, observer)
    }

    override fun applyUserInput(userInputTest: String) {
        dispatchers.launchBackground(viewModelScope) {
            val reversed = useCase.reverse(userInputTest)
            communication.put(IStringReverseState.Success(reversed))
        }
    }
}