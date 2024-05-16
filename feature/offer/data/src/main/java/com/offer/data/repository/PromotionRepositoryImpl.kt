package com.offer.data.repository

import com.core.network.feature.offer.repository.OfferDataProviders
import com.offer.data.mapper.toPromotionModel
import com.offer.domain.model.PromotionModel
import com.offer.domain.repository.PromotionRepository
import javax.inject.Inject

class PromotionRepositoryImpl @Inject constructor(private val offerDataProviders: OfferDataProviders) : PromotionRepository {
    override suspend fun getPromotions(): List<PromotionModel> {
        return offerDataProviders.getOffers().map { it.toPromotionModel() }
    }
}