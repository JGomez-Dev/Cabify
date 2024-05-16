package com.feature.ui.composable

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.core.common.theme.Cabify
import com.core.common.theme.CabifyTheme
import com.feature.domain.model.ProductModel
import com.feature.domain.model.Code
import com.offer.domain.model.PromotionModel
import com.offer.domain.model.PromotionOfferModel

@Composable
fun ProductListView(
    offerCodeClicked: String?,
    products: List<ProductModel> = listOf(),
    cartItems: List<ProductModel>,
    promotions: List<PromotionModel>?,
    onIncreaseClicked: (product: ProductModel) -> Unit,
    onDecreaseClicked: (product: ProductModel) -> Unit,
    theme: CabifyTheme
) {
    LazyColumn(modifier = Modifier.height(theme.dimension.sizeDimension296)) {
        itemsIndexed(products) { index, product ->
            ProductItemView(
                offerCodeClicked = offerCodeClicked,
                product = product,
                cartItems = cartItems,
                promotions = promotions,
                theme = theme,
                onIncreaseClicked = { product ->
                    onIncreaseClicked(product)
                },
                onDecreaseClicked = { product ->
                    onDecreaseClicked(product)
                }
            )
            if (index < products.lastIndex)
                HorizontalDivider(color = theme.semanticColor.onSecondary)
        }
    }
}

@Composable
@Preview
fun ProductSectionPreview() {
    val products = listOf(
        ProductModel(Code.TSHIRT, "Product 1", 10.0, 10.0),
        ProductModel(Code.VOUCHER, "Product 2", 20.0, 20.0),
        ProductModel(Code.MUG, "Product 3", 30.0, 30.0)
    )
    val promotions = listOf(
        PromotionModel("CODE_1", offer = PromotionOfferModel.BulkDiscount(minimumQuantity = 3, discountPerItem = 2.0), text = "Offer 1", image = "image_1"),
        PromotionModel("CODE_2", offer = PromotionOfferModel.BuyOneGetOneFree, text = " Offer 2", image = "image_1")
    )
    ProductSection(
        theme = Cabify.theme,
        products = products,
        offerCodeClicked = "",
        onIncreaseClicked = {},
        onDecreaseClicked = {},
        promotions = promotions,
        cartItems = products
    )
}