package com.offer.domain.di

import com.offer.domain.repository.PromotionRepository
import com.offer.domain.usecase.GetPromotionsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object OfferDomainModule {

    @Provides
    fun  provideGetPromotionsUseCase(promotionRepository : PromotionRepository) : GetPromotionsUseCase {
        return GetPromotionsUseCase(promotionRepository)
    }
}