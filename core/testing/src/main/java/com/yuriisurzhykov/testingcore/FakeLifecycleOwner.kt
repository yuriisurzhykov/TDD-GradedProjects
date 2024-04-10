package com.yuriisurzhykov.testingcore

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner

class FakeLifecycleOwner(private val state: Lifecycle.State) : LifecycleOwner {
    override val lifecycle: Lifecycle
        get() = object : Lifecycle() {
            override val currentState: State = state

            override fun addObserver(observer: LifecycleObserver) {
            }

            override fun removeObserver(observer: LifecycleObserver) {
            }
        }
}