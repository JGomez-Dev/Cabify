package com.feature.domain.usecase

import com.core.common.model.NetworkResult
import com.core.common.domain.usecase.dispatchers.DispatcherProvider
import com.core.common.domain.usecase.flow.FlowUseCase
import com.feature.domain.model.ProductModel
import com.feature.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class GetProductsUseCase @Inject constructor(
    private val provinceRepository: ProductRepository, dispatcherProvider: DispatcherProvider
) : FlowUseCase<Unit, NetworkResult<List<ProductModel>>>(dispatcherProvider) {
    override fun prepareFlow(input: Unit): Flow<NetworkResult<List<ProductModel>>> = flow {
        emit(provinceRepository.getProducts())
    }
}