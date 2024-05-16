package com.core.common.components.card

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.core.common.R
import com.core.common.theme.Cabify
import com.core.common.theme.CabifyTheme


const val CABIFY_CARD_PRODUCT_ITEM_PLUS_TAG = "CABIFY_CARD_PRODUCT_ITEM_PLUS_TAG"
const val CABIFY_CARD_PRODUCT_ITEM_MINUS_TAG = "CABIFY_CARD_PRODUCT_ITEM_MINUS_TAG"

@Composable
fun CardProductItem(
    theme: CabifyTheme = Cabify.theme,
    name: String,
    price: String,
    totalWithOffers: Double,
    image: Int,
    offerCodeClicked: String?,
    code: String,
    quantity: Int,
    onIncreaseClicked: () -> Unit,
    onDecreaseClicked: () -> Unit,
    contentPromotion: @Composable (() -> Unit)? = null
) {
    val value by rememberInfiniteTransition(label = "").animateFloat(
        initialValue = 1f,
        targetValue = 1.2f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 600,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .background(theme.semanticColor.surface, shape = RoundedCornerShape(bottomEnd = 0f, bottomStart = 0f, topEnd = 8f, topStart = 8f))
                .border(BorderStroke(0.dp, Color.Transparent), shape = RoundedCornerShape(bottomEnd = 0f, bottomStart = 0f, topEnd = 8f, topStart = 8f))
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    modifier = Modifier
                        .weight(0.25f)
                        .padding(theme.padding.padding8)
                        .clickable { onDecreaseClicked() },
                    painter = painterResource(id = image),
                    contentDescription = ""
                )
                Column(
                    modifier = Modifier
                        .padding(theme.padding.padding16)
                        .weight(1f)
                ) {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(theme.padding.padding8)) {
                                Text(text = name, style = theme.semanticTextStyle.cabifyHeading4(false))
                                Text(text = stringResource(R.string.price_label, price), style = theme.semanticTextStyle.cabifyHeading5(false))
                            }
                            contentPromotion?.let { contentPromotion ->
                                contentPromotion()
                            }

                        }
                    }
                    Text(
                        text = stringResource(R.string.price_label, (price.toDouble() * quantity)),
                        style = TextStyle(
                            textDecoration = TextDecoration.LineThrough
                        ), color = theme.semanticColor.n500
                    )
                    Text(text = stringResource(R.string.price_label, totalWithOffers), style = theme.semanticTextStyle.cabifyBody(false))
                    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.End) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.End
                        ) {
                            Icon(
                                modifier = Modifier
                                    .size(32.dp)
                                    .testTag(CABIFY_CARD_PRODUCT_ITEM_MINUS_TAG)
                                    .clickable { onDecreaseClicked() },
                                painter = painterResource(id = R.drawable.ic_minus_foreground),
                                tint = theme.semanticColor.accentLight,
                                contentDescription = ""
                            )
                            Text(
                                modifier = Modifier.padding(horizontal = theme.padding.padding16),
                                text = quantity.toString(),
                                textAlign = TextAlign.Center,
                                color = if (quantity == 0) theme.semanticColor.n500 else theme.semanticColor.accentLight,
                                style = theme.semanticTextStyle.cabifyCaption(false)
                            )
                            Icon(
                                modifier = Modifier
                                    .size(theme.dimension.sizeDimension32)
                                    .then(
                                        generateGraphicsLayerModifier(offerCodeClicked, code, value)
                                    )
                                    .testTag(CABIFY_CARD_PRODUCT_ITEM_PLUS_TAG)
                                    .clickable { onIncreaseClicked() },
                                painter = painterResource(id = R.drawable.ic_add_foreground),
                                tint = theme.semanticColor.accentLight,
                                contentDescription = ""
                            )
                        }
                    }
                }
            }
        }
    }
}

private fun generateGraphicsLayerModifier(
    offerCodeClicked: String?,
    code: String,
    value: Float
): Modifier {
    val modifier = Modifier.graphicsLayer {
        scaleX = value
        scaleY = value
    }
    return when {
        offerCodeClicked == code && (code == "TSHIRT" || code == "VOUCHER") -> modifier
        else -> Modifier
    }
}

@Composable
@Preview
fun CardProductPreview() {
    CardProductItem(
        name = "T-Shirt",
        price = 20.00.toString(),
        image = R.drawable.coffee_svgrepo_com,
        offerCodeClicked = "",
        code = "",
        onIncreaseClicked = {},
        onDecreaseClicked = {},
        quantity = 2,
        contentPromotion = {
            Box(
                modifier = Modifier
                    .background(color = Cabify.theme.semanticColor.onSecondary, shape = RoundedCornerShape(16))
            )
            {
                Text(
                    modifier = Modifier
                        .padding(4.dp), text = "Offer"
                )
            }
        },
        totalWithOffers = 120.00
    )
}
