package com.yuriisurzhykov.tddgraded.primenumber.domain

import com.yuriisurzhykov.tddgraded.core.Communication
import com.yuriisurzhykov.tddgraded.primenumber.data.PrimeNumberCheckResult

interface PrimeNumberCheckCommunication :
    Communication.Observe<PrimeNumberCheckResult>,
    Communication.Put<PrimeNumberCheckResult> {

    abstract class Abstract : Communication.Abstract<PrimeNumberCheckResult>(),
        PrimeNumberCheckCommunication

    class Base : Abstract()
}