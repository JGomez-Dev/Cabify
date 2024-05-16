package com.core.common.components.card


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import com.core.common.theme.Cabify
import com.core.common.theme.CabifyTheme

@Composable
fun CardBuyNow(
    theme: CabifyTheme = Cabify.theme,
    totalToPay: Double,
    totalWithoutPromotions: Double,
    onBuyNowClicked: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = Modifier
                .padding(theme.padding.padding16)
                .weight(1f)
        ) {
            Text(text = "Total",  style = theme.semanticTextStyle.cabifyButton(false), color = theme.semanticColor.accent)
            Text(text = "${totalToPay}€", style = theme.semanticTextStyle.cabifyBodyLead(false), color = theme.semanticColor.accent)
            Text(
                text = "${totalWithoutPromotions}€",
                style = TextStyle(
                    textDecoration = TextDecoration.LineThrough
                ), color = theme.semanticColor.n500
            )
        }
        Column(modifier = Modifier.weight(0.5f)) {
            Button(
                modifier = Modifier
                    .clip(CircleShape)
                    .fillMaxWidth(),
                shape = CircleShape,
                onClick = {
                    onBuyNowClicked()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = theme.semanticColor.accent,
                    disabledContainerColor = theme.semanticColor.background
                )
            ) {
                Text(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(vertical = theme.padding.padding8),
                    text = "Buy now",
                    style = theme.semanticTextStyle.cabifyHeading4(false),
                )
            }
        }
    }
}

@Composable
@Preview
fun BuyNowItemViewPreview(
) {
    CardBuyNow(
        totalToPay = 1.1,
        totalWithoutPromotions = 2.0,
        onBuyNowClicked = {}
    )
}