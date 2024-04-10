package com.yuriisurzhykov.tddgraded.primenumber.di

import com.yuriisurzhykov.tddgraded.primenumber.domain.PrimeNumberCheckCommunication
import com.yuriisurzhykov.tddgraded.primenumber.domain.PrimeNumberCheckUseCase
import com.yuriisurzhykov.tddgraded.primenumber.domain.StringToIntParser
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PrimeNumberModule {


    @Singleton
    @Provides
    fun providePrimeNumberCheckUseCase(): PrimeNumberCheckUseCase {
        return PrimeNumberCheckUseCase.Base()
    }

    @Provides
    fun providePrimeNumberCheckCommunication(): PrimeNumberCheckCommunication {
        return PrimeNumberCheckCommunication.Base()
    }

    @Provides
    @Singleton
    fun provideStringToIntParser(): StringToIntParser {
        return StringToIntParser.Base()
    }
}