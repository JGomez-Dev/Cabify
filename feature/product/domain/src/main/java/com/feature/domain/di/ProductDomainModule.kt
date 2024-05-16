package com.feature.domain.di

import com.core.common.domain.usecase.dispatchers.DispatcherProvider
import com.feature.domain.repository.ProductRepository
import com.feature.domain.usecase.GetProductsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object ProductDomainModule {

    @Provides
    fun  provideGetProductsUseCase(productRepository : ProductRepository, dispatcherProvider: DispatcherProvider) : GetProductsUseCase {
        return GetProductsUseCase(productRepository, dispatcherProvider)
    }
}