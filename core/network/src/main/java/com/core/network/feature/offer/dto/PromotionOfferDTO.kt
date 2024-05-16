package com.core.network.feature.offer.dto

data class PromotionOfferDTO(
    var minimumQuantity: Int? = null,
    var discountPerItem: Double? = null,
    var value: Double? = null
)
