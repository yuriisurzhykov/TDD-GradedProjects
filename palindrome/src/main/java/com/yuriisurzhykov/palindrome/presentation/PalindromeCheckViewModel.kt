package com.yuriisurzhykov.palindrome.presentation

import androidx.lifecycle.ViewModel
import com.yuriisurzhykov.palindrome.domain.IPalindromeCheckUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PalindromeCheckViewModel @Inject constructor(
    private val useCase: IPalindromeCheckUseCase
) : ViewModel() {
}