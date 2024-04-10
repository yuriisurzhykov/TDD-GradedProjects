package com.yuriisurzhykov.tddgraded.primenumber.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yuriisurzhykov.tddgraded.core.data.Dispatchers
import com.yuriisurzhykov.tddgraded.presentation.Communication
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
) : ViewModel(), Communication.Observe<PrimeNumberCheckResult>, IPrimeCheckViewModel {

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