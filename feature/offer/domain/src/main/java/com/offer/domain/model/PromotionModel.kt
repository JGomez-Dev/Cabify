package com.offer.domain.model

import com.feature.domain.model.ProductModel
import com.feature.domain.model.Code

data class PromotionModel(
    val code: String,
    val text: String,
    val image: String,
    val offer: PromotionOfferModel? = null
)

sealed class PromotionOfferModel {
    abstract fun applyOffer(total: Double, products: List<ProductModel>): Double
    data object BuyOneGetOneFree : PromotionOfferModel() {
        override  fun applyOffer(total: Double, products: List<ProductModel>): Double {
            val voucherCount = products.count { it.type.code == Code.VOUCHER.code }
            if (voucherCount >= 2) {
                val voucherPrice = products.firstOrNull { it.type.code == Code.VOUCHER.code }?.price ?: 0.0
                return total - (voucherCount / 2) * voucherPrice
            }
            return total
        }
    }
    data class BulkDiscount(val minimumQuantity: Int, val discountPerItem: Double) : PromotionOfferModel() {
        override  fun applyOffer(total: Double, products: List<ProductModel>): Double {
            val tshirtCount = products.count { it.type.code == Code.TSHIRT.code }
            if (tshirtCount >= minimumQuantity) {
                val tshirtPrice = products.firstOrNull { it.type.code == Code.TSHIRT.code }?.price ?: 0.0
                return total - (tshirtCount * (tshirtPrice - (tshirtPrice - discountPerItem)))
            }
            return total
        }
    }
}