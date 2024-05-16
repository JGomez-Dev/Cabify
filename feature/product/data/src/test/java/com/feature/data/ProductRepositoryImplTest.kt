package com.feature.data

import com.core.common.model.NetworkResult
import com.core.common.test.MainCoroutineScopeRule
import com.core.network.feature.product.dto.ProductDTO
import com.core.network.feature.product.dto.ProductItemDTO
import com.core.network.feature.product.repository.ProductDataProviders
import com.feature.data.repository.ProductRepositoryImpl
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension
import java.net.UnknownHostException

@OptIn(ExperimentalCoroutinesApi::class)
class ProductRepositoryImplTest {

    @JvmField
    @RegisterExtension
    val coroutinesExtension = MainCoroutineScopeRule()

    @Test
    fun `getProducts should return list of ProductModel`() = coroutinesExtension.runTest {
        // Given
        val mockProductDataProviders = mockk<ProductDataProviders>()
        val productRepository = ProductRepositoryImpl(mockProductDataProviders)

        val mockProductDTOs = ArrayList<ProductItemDTO>()
        mockProductDTOs.add(ProductItemDTO("TSHIRT", "Product 1", 10.0))
        mockProductDTOs.add(ProductItemDTO("TSHIRT", "Product 2", 20.0))
        mockProductDTOs.add(ProductItemDTO("TSHIRT", "Product 2", 30.0))
        mockProductDTOs.toMutableList()

        val productDTO = ProductDTO(products = mockProductDTOs)

        // Simulate repository providing mock products
        coEvery { mockProductDataProviders.getProducts() } returns productDTO

        // When
        val result = productRepository.getProducts()

        // Then
        assert(result is NetworkResult.Success)
    }

    @Test
    fun `getProducts with UnknownHostException return a Exception error`() = coroutinesExtension.runTest {
        // Given
        val mockProductDataProviders = mockk<ProductDataProviders>()
        val productRepository = ProductRepositoryImpl(mockProductDataProviders)

        // Simulate repository providing mock products
        coEvery { mockProductDataProviders.getProducts() } throws  UnknownHostException()

        // When
        val result = productRepository.getProducts()

        // Then
        assert(result is NetworkResult.Exception)
    }


}