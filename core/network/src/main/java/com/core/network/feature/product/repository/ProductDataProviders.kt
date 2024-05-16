package com.core.network.feature.product.repository

import com.core.network.feature.product.service.ProductApiService
import javax.inject.Inject

class ProductDataProviders @Inject constructor(private val productApiService: ProductApiService) {
    suspend fun getProducts() = productApiService.getProducts()
}