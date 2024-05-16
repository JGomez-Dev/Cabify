package com.feature.ui.di

import com.feature.ui.navigation.ProductNavigation
import com.feature.ui.navigation.ProductNavigationImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object ProductUIModule {

    @Provides
    fun provideProductNavigation(): ProductNavigation {
        return ProductNavigationImpl()
    }
}