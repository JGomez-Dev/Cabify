package com.core.network.feature.product.dto

import com.google.gson.annotations.SerializedName

data class ProductDTO(
    @SerializedName("products") var products: ArrayList<ProductItemDTO> = arrayListOf()
)