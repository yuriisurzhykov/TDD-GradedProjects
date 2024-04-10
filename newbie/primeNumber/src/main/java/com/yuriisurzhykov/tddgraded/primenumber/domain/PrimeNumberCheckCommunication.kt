package com.yuriisurzhykov.tddgraded.primenumber.domain

import com.yuriisurzhykov.tddgraded.presentation.Communication
import com.yuriisurzhykov.tddgraded.primenumber.data.PrimeNumberCheckResult

interface PrimeNumberCheckCommunication :
    Communication.Observe<PrimeNumberCheckResult>,
    Communication.Put<PrimeNumberCheckResult>,
    Communication.Post<PrimeNumberCheckResult> {

    abstract class Abstract : Communication.Abstract<PrimeNumberCheckResult>(),
        PrimeNumberCheckCommunication

    class Base : Abstract()
}