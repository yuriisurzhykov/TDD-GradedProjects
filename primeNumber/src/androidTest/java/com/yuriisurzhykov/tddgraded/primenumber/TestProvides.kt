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

package com.yuriisurzhykov.tddgraded.primenumber

import com.yuriisurzhykov.tddgraded.core.Dispatchers
import com.yuriisurzhykov.tddgraded.primenumber.di.PrimeNumberModule
import com.yuriisurzhykov.tddgraded.primenumber.domain.PrimeNumberCheckCommunication
import com.yuriisurzhykov.tddgraded.primenumber.domain.PrimeNumberCheckUseCase
import com.yuriisurzhykov.tddgraded.primenumber.domain.StringToIntParser
import dagger.Module
import dagger.Provides
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import javax.inject.Singleton

@Module
@TestInstallIn(components = [SingletonComponent::class], replaces = [PrimeNumberModule::class])
class TestProvides {
    @Provides
    fun providesDispatchers(): Dispatchers =
        object : Dispatchers.Abstract(UnconfinedTestDispatcher(), UnconfinedTestDispatcher()) {}

    @Provides
    fun providesStringReverseUseCase(): PrimeNumberCheckUseCase {
        return PrimeNumberCheckUseCase.Base()
    }

    @Provides
    fun providesStringReverseCommunication(): PrimeNumberCheckCommunication {
        return PrimeNumberCheckCommunication.Base()
    }

    @Provides
    @Singleton
    fun provideStringToIntParser(): StringToIntParser {
        return StringToIntParser.Base()
    }
}