package com.offer.domain.usecase

import com.offer.domain.model.PromotionModel
import com.offer.domain.repository.PromotionRepository
import javax.inject.Inject

class GetPromotionsUseCase @Inject constructor(private val promotionRepository: PromotionRepository){
    suspend operator fun invoke(): List<PromotionModel> {
        return promotionRepository.getPromotions()
    }
}