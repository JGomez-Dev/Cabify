package com.core.network.feature.offer.dto

data class PromotionDTO(
    var code: String,
    var text: String,
    var image: String,
    var offer: PromotionOfferDTO = PromotionOfferDTO()
)
