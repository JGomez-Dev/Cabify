package com.core.common.domain.usecase.di

import com.core.common.domain.usecase.dispatchers.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object CommonUseCaseModule {

    @Provides
    @Singleton
    fun provideDispatcherProvider(): DispatcherProvider {
        return object : DispatcherProvider {}
    }
}