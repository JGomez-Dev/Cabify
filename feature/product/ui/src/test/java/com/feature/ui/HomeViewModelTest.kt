package com.feature.ui

import app.cash.turbine.test
import com.core.common.error.SafeFlowUseCaseDelegate
import com.core.common.model.NetworkResult
import com.core.common.test.MainCoroutineScopeRule
import com.feature.domain.model.ProductModel
import com.feature.domain.model.Code
import com.feature.domain.usecase.GetProductsUseCase
import com.offer.domain.usecase.GetPromotionsUseCase
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension


@ExperimentalCoroutinesApi
class HomeViewModelTest {

    @RegisterExtension
    val coroutinesExtension = MainCoroutineScopeRule()

    private lateinit var viewModel: HomeViewModel

    private var getProductsUseCaseMockk: GetProductsUseCase = mockk(relaxed = true)
    private var getPromotionsUseCase: GetPromotionsUseCase = mockk(relaxed = true)
    private var safeFlowUseCaseDelegate: SafeFlowUseCaseDelegate = mockk(relaxed = true)

    @BeforeEach
    fun setup() {
        viewModel = HomeViewModel(safeFlowUseCaseDelegate, getProductsUseCaseMockk, getPromotionsUseCase)
    }


    @Test
    fun `addProduct should add product to cartItems`() = coroutinesExtension.runTest {
        // Given
        val productToAdd = ProductModel(Code.TSHIRT, "Product 1", 10.0, 10.0)

        with(safeFlowUseCaseDelegate) {
            every { getProductsUseCaseMockk.safePrepare() }.returns(flow { emit(NetworkResult.Success(listOf())) })
        }

        // When
        viewModel.addProduct(productToAdd)

        // Then
        viewModel.state.test {
            awaitItem() // <-- Loading State
            awaitItem() // <-- Success State
            assertEquals(1, viewModel.state.value.cartItems.size)
            assertEquals(productToAdd, viewModel.state.value.cartItems.first())
        }
    }

    @Test
    fun `removeProduct should remove product from cartItems`() = coroutinesExtension.runTest {
        // Given
        val productToRemove = ProductModel(Code.TSHIRT, "Product 1", 10.0, 10.0)
        val products = listOf(
            ProductModel(Code.TSHIRT, "Product 1", 10.0, 10.0)
        )

        with(safeFlowUseCaseDelegate) {
            every { getProductsUseCaseMockk.safePrepare() }.returns(flow { emit(NetworkResult.Success(products)) })
        }

        // When
        viewModel.removeProduct(productToRemove)

        // Then
        viewModel.state.test {
            awaitItem() // <-- Loading State
            assertEquals(0, viewModel.state.value.cartItems.size)
        }
    }

}
