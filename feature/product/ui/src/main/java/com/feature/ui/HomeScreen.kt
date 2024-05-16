package com.feature.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.core.common.components.genericviews.EmptyStateView
import com.core.common.components.genericviews.ErrorView
import com.core.common.components.genericviews.LoadingView
import com.core.common.components.header.CabifyHeader
import com.core.common.theme.Cabify
import com.core.common.theme.CabifyTheme
import com.feature.domain.model.ProductModel
import com.feature.domain.model.Code.MUG
import com.feature.domain.model.Code.TSHIRT
import com.feature.domain.model.Code.VOUCHER
import com.feature.ui.composable.CarrouselSection
import com.feature.ui.composable.FooterSection
import com.feature.ui.composable.ProductSection
import com.offer.domain.model.PromotionModel
import com.offer.domain.model.PromotionOfferModel


@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    theme: CabifyTheme = Cabify.theme,
) {
    val result = viewModel.state.collectAsStateWithLifecycle()
    when {
        result.value.isLoading -> LoadingView()
        result.value.error.isNotBlank() -> ErrorView("Generic Error")
        result.value.productsError.isNotBlank() -> ErrorView("Error al recuperar los productos")
        result.value.productsNetworkError.isNotBlank() -> ErrorView("Error de conexión")
        result.value.promotionsError.isNotBlank() -> ErrorView("Error al recuperar las promociones")
        else ->
            HomeContent(
                theme = theme,
                promotions = result.value.promotions,
                products = result.value.products,
                cartItems = result.value.cartItems,
                totalWithoutPromotions = result.value.totalWithoutPromotions,
                totalToPay = result.value.totalToPay,
                onDecreaseClicked = { product ->
                    viewModel.removeProduct(product)
                    viewModel.applyOffers()
                    viewModel.calculateTotal()
                },
                onIncreaseClicked = { product ->
                    viewModel.addProduct(product)
                    viewModel.applyOffers()
                    viewModel.calculateTotal()
                }
            )
    }
}

@Composable
fun HomeContent(
    theme: CabifyTheme,
    promotions: List<PromotionModel>?,
    products: List<ProductModel>?,
    cartItems: List<ProductModel>,
    totalWithoutPromotions: Double,
    totalToPay: Double,
    onIncreaseClicked: (product: ProductModel) -> Unit,
    onDecreaseClicked: (product: ProductModel) -> Unit,
) {
    var offerCodeClicked: String? by remember { mutableStateOf(null) }
    Scaffold(
        topBar = {
            CabifyHeader(text = stringResource(R.string.label_cart))
        }
    ) {
        Box(modifier = Modifier.padding(it)) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(theme.semanticColor.background),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                promotions?.let { promotions ->
                    CarrouselSection(
                        promotions = promotions,
                        theme = theme,
                        offerCodeClicked = { code -> offerCodeClicked = code })
                }
                if (products != null) {
                    ProductSection(
                        products = products,
                        promotions = promotions,
                        theme = theme,
                        cartItems = cartItems,
                        offerCodeClicked = offerCodeClicked,
                        onIncreaseClicked = { product ->
                            onIncreaseClicked(product)
                        },
                        onDecreaseClicked = { product ->
                            onDecreaseClicked(product)
                        }
                    )
                } else {
                    EmptyStateView(message = stringResource(R.string.label_products))
                }
                FooterSection(
                    theme = theme,
                    totalWithoutPromotions = totalWithoutPromotions,
                    totalToPay = totalToPay
                )
            }
        }
    }
}


@Composable
@Preview(device = "id:Nexus 5X")
fun HomeScreenPreview() {
    val products = listOf(
        ProductModel(TSHIRT, "Product 1", 10.0, 10.0),
        ProductModel(VOUCHER, "Product 2", 20.0, 20.0),
        ProductModel(MUG, "Product 3", 30.0, 30.0)
    )
    val promotions = listOf(
        PromotionModel("CODE_1", offer = PromotionOfferModel.BulkDiscount(minimumQuantity = 3, discountPerItem = 2.0), text = "Offer 1", image = "image_1"),
        PromotionModel("CODE_2", offer = PromotionOfferModel.BuyOneGetOneFree, text = " Offer 2", image = "image_1")
    )
    HomeContent(
        theme = Cabify.theme,
        promotions = promotions,
        products = products,
        totalWithoutPromotions = 120.00,
        totalToPay = 100.00,
        onIncreaseClicked = {},
        onDecreaseClicked = {},
        cartItems = products
    )
}