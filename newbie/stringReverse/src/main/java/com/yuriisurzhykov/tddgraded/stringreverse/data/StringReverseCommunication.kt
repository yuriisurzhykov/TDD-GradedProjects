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

package com.yuriisurzhykov.tddgraded.stringreverse.data

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.yuriisurzhykov.tddgraded.presentation.Communication

interface StringReverseCommunication : Communication.Observe<IStringReverseState>,
    Communication.Put<IStringReverseState> {

    abstract class Abstract(private val liveData: MutableLiveData<IStringReverseState> = MutableLiveData()) :
        StringReverseCommunication {
        override fun put(value: IStringReverseState) {
            liveData.postValue(value)
        }

        override fun observe(owner: LifecycleOwner, observer: Observer<IStringReverseState>) {
            liveData.observe(owner, observer)
        }
    }

    class Base: Abstract()
}