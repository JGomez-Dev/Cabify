package com.feature.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.core.common.theme.Cabify
import com.core.common.theme.CabifyTheme
import com.feature.domain.model.ProductModel
import com.feature.domain.model.Code
import com.feature.ui.R
import com.offer.domain.model.PromotionModel
import com.offer.domain.model.PromotionOfferModel

@Composable
fun ProductSection(
    theme: CabifyTheme,
    products: List<ProductModel>,
    cartItems: List<ProductModel>,
    offerCodeClicked: String?,
    onIncreaseClicked: (product: ProductModel) -> Unit,
    onDecreaseClicked: (product: ProductModel) -> Unit,
    promotions: List<PromotionModel>?,
) {
    Column(modifier = Modifier.padding(horizontal = theme.padding.padding16)) {
        Text(
            text = stringResource(R.string.label_articles),
            style = theme.semanticTextStyle.cabifyHeading4(false),
            color = theme.semanticColor.onSecondary
        )
        HorizontalDivider(color = theme.semanticColor.onSecondary)
        Spacer(modifier = Modifier.padding(theme.padding.padding8))
        ProductListView(
            products = products,
            cartItems = cartItems,
            promotions = promotions,
            theme = theme,
            offerCodeClicked = offerCodeClicked,
            onIncreaseClicked = { product ->
                onIncreaseClicked(product)
            },
            onDecreaseClicked = { product ->
                onDecreaseClicked(product)
            }
        )
        Row(
            modifier = Modifier
                .background(
                    theme.semanticColor.accentLight,
                    shape = RoundedCornerShape(bottomEnd = 8f, bottomStart = 8f, topEnd = 0f, topStart = 0f)
                )
                .fillMaxWidth()
                .padding(theme.padding.padding16),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Rounded.ShoppingCart, contentDescription = "",
                tint = theme.semanticColor.accent
            )
            Text(
                modifier = Modifier
                    .padding(horizontal = theme.padding.padding16),
                text = stringResource(R.string.label_have_articles, cartItems.size),
                color = theme.semanticColor.accent,
                style = theme.semanticTextStyle.cabifyBody(false)
            )
        }
    }
}

@Composable
@Preview
fun ProductListViewPreview() {
    val products = listOf(
        ProductModel(Code.TSHIRT, "Product 1", 10.0, 10.0),
        ProductModel(Code.VOUCHER, "Product 2", 20.0, 20.0),
        ProductModel(Code.MUG, "Product 3", 30.0, 30.0)
    )
    val promotions = listOf(
        PromotionModel("CODE_1", offer = PromotionOfferModel.BulkDiscount(minimumQuantity = 3, discountPerItem = 2.0), text = "Offer 1", image = "image_1"),
        PromotionModel("CODE_2", offer = PromotionOfferModel.BuyOneGetOneFree, text = " Offer 2", image = "image_1")
    )
    ProductListView(
        offerCodeClicked = "",
        products = products,
        promotions = promotions,
        onIncreaseClicked = {},
        onDecreaseClicked = {},
        theme = Cabify.theme,
        cartItems = products
    )
}