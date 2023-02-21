package com.yuriisurzhykov.testingcore

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner

class FakeLifecycleOwner(private val state: Lifecycle.State) : LifecycleOwner {
    override fun getLifecycle(): Lifecycle {
        return object : Lifecycle() {
            override fun addObserver(observer: LifecycleObserver) {
            }

            override fun removeObserver(observer: LifecycleObserver) {
            }

            override fun getCurrentState() = state
        }
    }
}