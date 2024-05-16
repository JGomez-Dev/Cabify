package com.feature.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.common.error.SafeFlowUseCaseDelegate
import com.core.common.model.NetworkResult
import com.feature.domain.model.ProductModel
import com.feature.domain.usecase.GetProductsUseCase
import com.offer.domain.model.PromotionModel
import com.offer.domain.usecase.GetPromotionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import java.net.UnknownHostException
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val safeFlowUseCaseDelegate: SafeFlowUseCaseDelegate,
    private val getProductsUseCase: GetProductsUseCase,
    private val getPromotionsUseCase: GetPromotionsUseCase
) : ViewModel(), SafeFlowUseCaseDelegate by safeFlowUseCaseDelegate {

    private val _mainState = MutableStateFlow(MainState())
    val state: StateFlow<MainState> = _mainState.asStateFlow()

    init {
        getProducts()
        getPromotions()
    }

    private fun getProducts() {
        getProductsUseCase
            .safePrepare(
                onGenericError = { _mainState.value = _mainState.value.copy(productsError = "") }
            )
            .onStart {
                _mainState.value = _mainState.value.copy(isLoading = true)
            }
            .onCompletion {
                _mainState.value = _mainState.value.copy(isLoading = false)
            }
            .onEach { response ->
                when (response) {
                    is NetworkResult.Error -> {
                        _mainState.value = _mainState.value.copy(productsError = response.message.toString())
                    }
                    is NetworkResult.Exception -> {
                        when(response.e){
                            is UnknownHostException -> _mainState.value = _mainState.value.copy(productsNetworkError = response.e.message.toString())
                            else -> _mainState.value = _mainState.value.copy(productsError = response.e.message.toString())
                        }
                    }
                    is NetworkResult.Success -> {
                        _mainState.value = _mainState.value.copy(products = response.data)
                    }
                }
            }
            .launchIn(viewModelScope)
    }

    private fun getPromotions() = viewModelScope.launch {
        val promotions = getPromotionsUseCase()
        _mainState.value = _mainState.value.copy(promotions = promotions)
    }

    fun addProduct(product: ProductModel) = viewModelScope.launch {
        _mainState.value = _mainState.value.copy(
            cartItems = _mainState.value.cartItems.toMutableList().apply { add(product) },
        )
    }


    fun removeProduct(product: ProductModel) = viewModelScope.launch {
        _mainState.value = _mainState.value.copy(
            cartItems = _mainState.value.cartItems.toMutableList().apply { remove(product) },
        )
    }

    fun calculateTotal() = viewModelScope.launch {
        val totalWithoutPromotions = _mainState.value.cartItems.sumOf { it.price }
        _mainState.value = _mainState.value.copy(
            totalWithoutPromotions = totalWithoutPromotions
        )
    }


    fun applyOffers() = viewModelScope.launch {
        val articles = _mainState.value.cartItems
        var total = articles.sumOf { it.price }
        _mainState.value.promotions?.onEach { promotion ->
            total = promotion.offer?.applyOffer(total, articles) ?: 0.0
        }
        _mainState.value = _mainState.value.copy(
            totalToPay = total
        )
    }

}

data class MainState(
    val products: List<ProductModel>? = null,
    val promotions: List<PromotionModel>? = null,
    var cartItems: List<ProductModel> = mutableListOf(),
    val totalWithoutPromotions: Double = 0.0,
    val totalToPay: Double = 0.0,
    val error: String = "",
    val productsError: String = "",
    val productsNetworkError: String = "",
    val promotionsError: String = "",
    val isLoading: Boolean = true
)

