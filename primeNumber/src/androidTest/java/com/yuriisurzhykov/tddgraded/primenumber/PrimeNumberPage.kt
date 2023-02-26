package com.yuriisurzhykov.tddgraded.primenumber

import com.yuriisurzhykov.testingcore.android.Page

class PrimeNumberPage : Page() {
    val input = R.id.number_input.view()
    val resultView = R.id.number_check_result.view()
    val button = R.id.check_button.view()
}