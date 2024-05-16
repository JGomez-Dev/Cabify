package com.core.network.feature.product.di

import com.core.network.feature.product.repository.ProductDataProviders
import com.core.network.feature.product.service.ProductApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@InstallIn(SingletonComponent::class)
@Module
object ProductNetworkModule {

    @Provides
    fun provideProductApiService(retrofit: Retrofit): ProductApiService {
        return retrofit.create(ProductApiService::class.java)
    }

    @Provides
    fun provideProductDataProvider(productApiService: ProductApiService): ProductDataProviders {
        return ProductDataProviders(productApiService)
    }

}