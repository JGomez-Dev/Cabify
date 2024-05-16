package com.offer.domain.repository

import com.offer.domain.model.PromotionModel

interface PromotionRepository {
    suspend fun getPromotions(): List<PromotionModel>
}