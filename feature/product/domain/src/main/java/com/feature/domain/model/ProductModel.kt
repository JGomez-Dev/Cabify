package com.feature.domain.model

data class ProductModel(
    var type: Code,
    var name: String,
    var price: Double,
    var priceWithPromotion: Double,
)

enum class Code(val code: String) {
    TSHIRT(code = "TSHIRT"),
    VOUCHER(code = "VOUCHER"),
    MUG(code = "MUG")
}
