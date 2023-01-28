package com.yuriisurzhykov.palindrome.data

interface IPalindromeCheckState {

    class Success : IPalindromeCheckState

    class InputError : IPalindromeCheckState

    class CheckError : IPalindromeCheckState
}