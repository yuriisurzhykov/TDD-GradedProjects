package com.yuriisurzhykov.palindrome.domain

import com.yuriisurzhykov.palindrome.data.IPalindromeCheckState
import com.yuriisurzhykov.palindrome.data.PalindromeInputEntity

interface IPalindromeCheckUseCase {
    fun check(input: PalindromeInputEntity): IPalindromeCheckState
}