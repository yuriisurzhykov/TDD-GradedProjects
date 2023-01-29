package com.yuriisurzhykov.palindrome.presentation

import androidx.lifecycle.*
import com.yuriisurzhykov.palindrome.data.IPalindromeCheckState
import com.yuriisurzhykov.palindrome.data.PalindromeInputEntity
import com.yuriisurzhykov.palindrome.domain.IPalindromeCheckUseCase
import com.yuriisurzhykov.tddgraded.core.Dispatchers
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PalindromeCheckViewModel @Inject constructor(
    private val useCase: IPalindromeCheckUseCase,
    private val dispatchers: Dispatchers
) : ViewModel(), IPalindromeCheckViewModel {

    private val checkStateObservable = MutableLiveData<IPalindromeCheckState>()

    override fun observeCheckState(owner: LifecycleOwner, observer: Observer<IPalindromeCheckState>) {
        checkStateObservable.observe(owner, observer)
    }

    override fun applyInput(data: PalindromeInputEntity) {
        dispatchers.launchUi(viewModelScope) {
            val checkState = useCase.check(data)
            checkStateObservable.postValue(checkState)
        }
    }
}