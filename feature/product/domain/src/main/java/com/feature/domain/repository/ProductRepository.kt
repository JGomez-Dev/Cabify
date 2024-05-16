package com.feature.domain.repository

import com.core.common.model.NetworkResult
import com.feature.domain.model.ProductModel

interface ProductRepository {
    suspend fun getProducts(): NetworkResult<List<ProductModel>>
}