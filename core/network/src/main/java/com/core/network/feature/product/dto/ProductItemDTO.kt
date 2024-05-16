package com.core.network.feature.product.dto

import com.google.gson.annotations.SerializedName

data class ProductItemDTO(
    @SerializedName("code") var code: String,
    @SerializedName("name") var name: String,
    @SerializedName("price") var price: Double
)