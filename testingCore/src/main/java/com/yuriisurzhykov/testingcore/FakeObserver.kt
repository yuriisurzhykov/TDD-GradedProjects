package com.yuriisurzhykov.testingcore

import androidx.lifecycle.Observer

interface FakeObserver<T : Any> : Observer<T> {

    abstract class Abstract<T : Any> : FakeObserver<T> {

        private var value: T? = null
        private var callCount: Int = 0

        override fun onChanged(t: T?) {
            value = t
            callCount++
        }

        fun getCurrentValue() = value
        fun getCallCount() = value
    }
}