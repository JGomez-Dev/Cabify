package com.core.network.feature.product.service

import com.core.network.feature.product.dto.ProductDTO
import retrofit2.Call
import retrofit2.http.GET

interface ProductApiService {

    @GET("palcalde/6c19259bd32dd6aafa327fa557859c2f/raw/ba51779474a150ee4367cda4f4ffacdcca479887/Products.json")
    suspend fun getProducts(): ProductDTO
}