package com.feature.domain

import com.core.common.error.GlobalErrorManager
import com.core.common.error.SafeFlowUseCaseDelegate
import com.core.common.model.NetworkResult
import com.core.common.model.UiEvent
import com.core.common.test.MainCoroutineScopeRule
import com.core.common.domain.usecase.dispatchers.DispatcherProvider
import com.feature.domain.model.ProductModel
import com.feature.domain.model.Code
import com.feature.domain.repository.ProductRepository
import com.feature.domain.usecase.GetProductsUseCase
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension

@OptIn(ExperimentalCoroutinesApi::class)
class GetProductsUseCaseTest {

    @JvmField
    @RegisterExtension
    val coroutinesExtension = MainCoroutineScopeRule()
    private var productRepository: ProductRepository = mockk()
    private lateinit var getProductsUseCase: GetProductsUseCase
    private val globalErrorManager: GlobalErrorManager = mockk(relaxed = true)
    private val safeFlowUseCaseDelegate: SafeFlowUseCaseDelegate = SafeFlowUseCaseDelegate.Default(globalErrorManager)

    @BeforeEach
    fun setup() {
        Dispatchers.resetMain()
        getProductsUseCase = GetProductsUseCase(productRepository, coroutinesExtension.testDispatcherProvider)
    }

    @Test
    fun `prepareFlow emits NetworkResult_Success when getProducts succeeds`() = coroutinesExtension.runTest {

        val mockProducts = listOf(
            ProductModel(Code.TSHIRT, "Product 1", 10.0, 10.0),
            ProductModel(Code.TSHIRT, "Product 2", 20.0, 20.0),
            ProductModel(Code.VOUCHER, "Product 3", 30.0, 30.0)
        )

        coEvery { productRepository.getProducts() } answers { NetworkResult.Success(mockProducts) }

        with(safeFlowUseCaseDelegate){
            getProductsUseCase.safePrepare().collect { response ->
                when(response){
                    is NetworkResult.Error -> TODO()
                    is NetworkResult.Exception -> TODO()
                    is NetworkResult.Success -> {
                        assertEquals(mockProducts, response.data)
                    }
                }
            }
        }

    }

    @Test
    fun `invoke should emit Error event with message when repository call fails`() = coroutinesExtension.runTest {
        // Given
        val exceptionMessage = "Error fetching products"

        coEvery { productRepository.getProducts() } throws RuntimeException(exceptionMessage)

        with(safeFlowUseCaseDelegate){
            getProductsUseCase.safePrepare().collect { response ->
                when(response){
                    is NetworkResult.Error -> TODO()
                    is NetworkResult.Exception -> { assertEquals(exceptionMessage, response.e.message) }
                    is NetworkResult.Success -> { TODO() }
                }
            }
        }

    }
}