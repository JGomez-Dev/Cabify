package com.feature.data.repository

import com.core.common.model.NetworkResult
import com.core.common.utils.extensions.getErrorMessage
import com.core.network.feature.product.repository.ProductDataProviders
import com.feature.data.mapper.toDomainProductModel
import com.feature.domain.model.ProductModel
import com.feature.domain.repository.ProductRepository
import retrofit2.HttpException
import java.net.UnknownHostException
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(private val productDataProviders: ProductDataProviders) : ProductRepository {
    override suspend fun getProducts(): NetworkResult<List<ProductModel>> {
        return try {
            val response = productDataProviders.getProducts()
            NetworkResult.Success(response.toDomainProductModel())
        } catch (e: HttpException) {
            NetworkResult.Error(code = e.code(), message = e.message())
        } catch (e: UnknownHostException) {
            NetworkResult.Exception(e)
        } catch (e: Throwable) {
            NetworkResult.Exception(e)
        }
    }
}