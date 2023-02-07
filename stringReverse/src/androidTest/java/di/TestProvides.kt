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

package di

import com.yuriisurzhykov.tddgraded.core.Dispatchers
import com.yuriisurzhykov.tddgraded.stringreverse.data.StringReverseCommunication
import com.yuriisurzhykov.tddgraded.stringreverse.di.UseCaseModule
import com.yuriisurzhykov.tddgraded.stringreverse.domain.IStringReverseUseCase
import com.yuriisurzhykov.tddgraded.stringreverse.domain.ManualStringReverseUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.testing.TestInstallIn
import kotlinx.coroutines.test.UnconfinedTestDispatcher

@Module
@TestInstallIn(components = [ViewModelComponent::class], replaces = [UseCaseModule::class])
class TestProvides {
    @Provides
    fun providesDispatchers() =
        object : Dispatchers.Abstract(UnconfinedTestDispatcher(), UnconfinedTestDispatcher()) {}

    @Provides
    fun providesStringReverseUseCase(): IStringReverseUseCase {
        return ManualStringReverseUseCase()
    }

    @Provides
    fun providesStringReverseCommunication(): StringReverseCommunication {
        return StringReverseCommunication.Base()
    }
}