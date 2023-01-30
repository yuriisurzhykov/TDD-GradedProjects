package com.yuriisurzhykov.palindrome.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yuriisurzhykov.palindrome.data.IPalindromeCheckState
import com.yuriisurzhykov.palindrome.data.PalindromeInputEntity
import com.yuriisurzhykov.palindrome.domain.IPalindromeCheckUseCase
import com.yuriisurzhykov.palindrome.domain.PalindromeCheckCommunication
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