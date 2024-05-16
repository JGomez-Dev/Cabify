package com.offer.data.di

import com.core.network.feature.offer.repository.OfferDataProviders
import com.offer.data.repository.PromotionRepositoryImpl
import com.offer.domain.repository.PromotionRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object OfferDataModule {
    @Provides
    fun providePromotionRepository(offerDataProviders: OfferDataProviders): PromotionRepository {
        return PromotionRepositoryImpl(offerDataProviders)
    }
}