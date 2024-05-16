package com.feature.data.mapper

import com.core.network.feature.product.dto.ProductDTO
import com.feature.domain.model.ProductModel
import com.feature.domain.model.Code

fun ProductDTO.toDomainProductModel(): List<ProductModel> {
    return this.products.map {
        ProductModel(
            name = it.name,
            price = it.price,
            priceWithPromotion = 0.0,
            type = it.code.toDiscountCode() ?: Code.MUG
        )
    }
}

fun String.toDiscountCode(): Code? {
    return when (this.uppercase()) {
        "TSHIRT" -> Code.TSHIRT
        "VOUCHER" -> Code.VOUCHER
        "MUG" -> Code.MUG
        else -> null
    }
}


