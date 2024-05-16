package com.feature.data.di

import com.core.network.feature.product.repository.ProductDataProviders
import com.feature.data.repository.ProductRepositoryImpl
import com.feature.domain.repository.ProductRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object ProductDataModule {

    @Provides
    fun provideProductRepository(productDataProviders: ProductDataProviders): ProductRepository {
        return ProductRepositoryImpl(productDataProviders)
    }
}