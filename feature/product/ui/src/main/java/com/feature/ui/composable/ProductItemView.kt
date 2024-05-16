package com.feature.ui.composable

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.core.common.R
import com.core.common.components.card.CardProductItem
import com.core.common.components.offer.LabelOffer
import com.core.common.theme.Cabify
import com.core.common.theme.CabifyTheme
import com.feature.domain.model.ProductModel
import com.feature.domain.model.Code
import com.feature.domain.model.Code.MUG
import com.feature.domain.model.Code.TSHIRT
import com.feature.domain.model.Code.VOUCHER
import com.offer.domain.model.PromotionModel
import com.offer.domain.model.PromotionOfferModel

@Composable
fun ProductItemView(
    offerCodeClicked: String?,
    product: ProductModel,
    cartItems: List<ProductModel>,
    promotions: List<PromotionModel>?,
    theme: CabifyTheme,
    onIncreaseClicked: (product: ProductModel) -> Unit,
    onDecreaseClicked: (product: ProductModel) -> Unit,
) {
    val totalWithOffers = calculateTotalWithOffers(product, cartItems, promotions)

    CardProductItem(
        name = product.name,
        price = product.price.toString(),
        totalWithOffers = totalWithOffers,
        code = product.type.code,
        offerCodeClicked = offerCodeClicked,
        quantity = cartItems.count { it.type == product.type },
        onIncreaseClicked = { onIncreaseClicked(product) },
        onDecreaseClicked = { onDecreaseClicked(product) },
        image = getProductImage(product.type),
        contentPromotion = {
            promotions?.let {
                GetPromotionLabel(product.type, theme)
            }
        }
    )
}

private fun calculateTotalWithOffers(
    product: ProductModel,
    cartItems: List<ProductModel>,
    promotions: List<PromotionModel>?
): Double {
    var totalWithOffers = product.price * cartItems.count { it.type == product.type }
    promotions?.forEach { promotion ->
        totalWithOffers = promotion.offer?.applyOffer(totalWithOffers, cartItems.filter { it.type == product.type }) ?: 0.0
    }
    return totalWithOffers
}

private fun getProductImage(productType: Code): Int {
    return when (productType) {
        TSHIRT -> R.drawable.shirt_svgrepo_com
        VOUCHER -> R.drawable.schedule_svgrepo_com
        MUG -> R.drawable.coffee_svgrepo_com
    }
}

@Composable
private fun GetPromotionLabel(productType: Code, theme: CabifyTheme) {
    when (productType) {
        TSHIRT -> LabelOffer(theme, stringResource(com.feature.ui.R.string.label_promotion_3x19_u, "3", "4"))
        VOUCHER -> LabelOffer(theme, stringResource(com.feature.ui.R.string.label_promotion_2x1))
        MUG -> { /*Nothing*/ }
    }
}

@Composable
@Preview
fun ProductItemViewPreview() {
    val products = listOf(
        ProductModel(TSHIRT, name = "Product 1", price = 10.0, priceWithPromotion = 10.0),
        ProductModel(VOUCHER, name = "Product 2", price = 20.0, priceWithPromotion = 20.0),
        ProductModel(MUG,name = "Product 3", price = 30.0, priceWithPromotion = 30.0)
    )
    val promotions = listOf(
        PromotionModel("CODE_1", offer = PromotionOfferModel.BulkDiscount(minimumQuantity = 3, discountPerItem = 2.0), text = "Offer 1", image = "image_1"),
        PromotionModel("CODE_2", offer = PromotionOfferModel.BuyOneGetOneFree, text = " Offer 2", image = "image_1")
    )
    ProductItemView(
        offerCodeClicked = "",
        product = ProductModel(TSHIRT, "Product 1", 10.0, 10.0),
        promotions = promotions,
        theme = Cabify.theme,
        onIncreaseClicked = {},
        onDecreaseClicked = {},
        cartItems = products
    )
}