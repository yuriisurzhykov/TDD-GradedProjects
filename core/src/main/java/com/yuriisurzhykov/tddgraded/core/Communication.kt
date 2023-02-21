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

package com.yuriisurzhykov.tddgraded.core

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

interface Communication {

    interface Put<T : Any> {
        fun put(value: T)
    }

    interface Observe<T : Any> {
        fun observe(owner: LifecycleOwner, observer: Observer<T>)
    }

    interface Post<T : Any> {
        fun post(value: T)
    }

    abstract class Abstract<T : Any> constructor(
        private val liveData: MutableLiveData<T> = MutableLiveData()
    ) : Put<T>, Observe<T>, Post<T> {
        override fun put(value: T) {
            liveData.value = value
        }

        override fun observe(owner: LifecycleOwner, observer: Observer<T>) {
            liveData.observe(owner, observer)
        }

        override fun post(value: T) {
            liveData.postValue(value)
        }
    }
}