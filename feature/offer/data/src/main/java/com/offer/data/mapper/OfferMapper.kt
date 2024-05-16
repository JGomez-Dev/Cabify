package com.offer.data.mapper

import com.core.network.feature.offer.dto.PromotionDTO
import com.core.network.feature.offer.dto.PromotionOfferDTO
import com.offer.domain.model.PromotionModel
import com.offer.domain.model.PromotionOfferModel

fun PromotionDTO.toPromotionModel(): PromotionModel {
    return PromotionModel(code = code, offer = offer.toPromotionOfferModel(code), text = text, image = image)
}

fun PromotionOfferDTO.toPromotionOfferModel(code : String): PromotionOfferModel? {
    return when(code) {
        "TSHIRT" ->  PromotionOfferModel.BulkDiscount(
            minimumQuantity ?: 3,
            discountPerItem ?: 1.0
        )
        "VOUCHER" -> PromotionOfferModel.BuyOneGetOneFree
        else -> null
    }
}