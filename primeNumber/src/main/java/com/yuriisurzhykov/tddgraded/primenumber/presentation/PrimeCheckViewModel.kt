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

package com.yuriisurzhykov.tddgraded.primenumber.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yuriisurzhykov.tddgraded.core.Communication
import com.yuriisurzhykov.tddgraded.core.Dispatchers
import com.yuriisurzhykov.tddgraded.primenumber.data.PrimeNumberCheckResult
import com.yuriisurzhykov.tddgraded.primenumber.domain.PrimeNumberCheckCommunication
import com.yuriisurzhykov.tddgraded.primenumber.domain.PrimeNumberCheckUseCase
import com.yuriisurzhykov.tddgraded.primenumber.domain.StringToIntParser
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PrimeCheckViewModel @Inject constructor(
    private val primeCheckUseCase: PrimeNumberCheckUseCase,
    private val communication: PrimeNumberCheckCommunication,
    private val stringParser: StringToIntParser,
    private val dispatchers: Dispatchers
) : ViewModel(), Communication.LiveData.Observe<PrimeNumberCheckResult>, IPrimeCheckViewModel {

    override fun observe(owner: LifecycleOwner, observer: Observer<PrimeNumberCheckResult>) {
        communication.observe(owner, observer)
    }

    override fun checkNumber(number: String) {
        dispatchers.launchBackground(viewModelScope) {
            val integerValue = stringParser.parse(number)
            val checkingResult = primeCheckUseCase.isPrimeNumber(integerValue.toLong())
            communication.post(checkingResult)
        }
    }
}