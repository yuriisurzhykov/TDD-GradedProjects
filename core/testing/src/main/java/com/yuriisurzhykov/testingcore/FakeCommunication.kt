package com.yuriisurzhykov.testingcore

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.yuriisurzhykov.tddgraded.presentation.Communication

interface FakeCommunication<T : Any> : Communication.Put<T>, Communication.Observe<T>,
    Communication.Post<T> {

    abstract class Abstract<T : Any> : FakeCommunication<T> {

        private var currentValue: T? = null
        private var callCounts: Int = 0
        private val observers = mutableListOf<Observer<T>>()

        override fun put(value: T) {
            currentValue = value
            callCounts++
            observers.forEach { it.onChanged(value) }
        }

        override fun observe(owner: LifecycleOwner, observer: Observer<T>) {
            currentValue?.let { observer.onChanged(it) }
            observers.add(observer)
        }

        override fun post(value: T) {
            currentValue = value
            callCounts++
            observers.forEach { it.onChanged(value) }
        }

        fun getCallCounts() = callCounts
        fun getStateResult() = currentValue
    }
}