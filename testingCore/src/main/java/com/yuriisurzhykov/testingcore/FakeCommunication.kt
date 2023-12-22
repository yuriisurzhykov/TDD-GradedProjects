/*
 * Copyright (c) 2023 Yurii Surzhykov.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.yuriisurzhykov.testingcore

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.yuriisurzhykov.tddgraded.core.Communication

interface FakeCommunication<T : Any> : Communication.LiveData.Put<T>,
    Communication.LiveData.Observe<T>,
    Communication.LiveData.Post<T> {

    abstract class Abstract<T : Any> : FakeCommunication<T>, Communication.LiveData.Abstract<T>() {

        private var currentValue: T? = null
        private var callCounts: Int = 0
        private val observers = mutableListOf<Observer<T>>()

        override fun put(value: T) {
            currentValue = value
            callCounts++
            observers.forEach { it.onChanged(value) }
        }

        override fun observe(owner: LifecycleOwner, observer: Observer<T>) {
            observers.add(observer)
            currentValue?.let { observer.onChanged(it) }
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