package com.feature.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import com.core.common.components.button.CabifyButton
import com.core.common.theme.Cabify
import com.core.common.theme.CabifyTheme
import com.feature.ui.R

@Composable
fun FooterSection(theme: CabifyTheme, totalWithoutPromotions: Double, totalToPay: Double) {
    Column(modifier = Modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = theme.padding.padding16), horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(R.string.label_items),
                style = theme.semanticTextStyle.cabifyBody(false),
                color = theme.semanticColor.n500
            )
            Text(
                text = stringResource(R.string.label_currency, totalWithoutPromotions),
                style = theme.semanticTextStyle.cabifyBodyLead(false).copy(textDecoration = TextDecoration.LineThrough),
                color = theme.semanticColor.accentLight
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = theme.padding.padding16), horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(R.string.label_discounts),
                style = theme.semanticTextStyle.cabifyBody(false),
                color = theme.semanticColor.n500
            )
            Text(
                text = stringResource(R.string.label_currency_discount, totalWithoutPromotions - totalToPay),
                style = theme.semanticTextStyle.cabifyBodyLead(false), color = theme.semanticColor.accentLight
            )
        }
        Spacer(modifier = Modifier.padding(theme.padding.padding16))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = theme.padding.padding16), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.label_total),
                style = theme.semanticTextStyle.cabifyBody(false),
                color = theme.semanticColor.accentLight
            )
            Box(
                modifier = Modifier
                    .background(color = Cabify.theme.semanticColor.onSecondary, shape = RoundedCornerShape(16))
            )
            {
                Text(
                    modifier = Modifier.padding(theme.padding.padding8),
                    text = stringResource(R.string.label_currency, totalToPay),
                    style = theme.semanticTextStyle.cabifyBodyLead(false), color = theme.semanticColor.surface
                )
            }
        }
        Box(modifier = Modifier.padding(theme.padding.padding16)) {
            CabifyButton(text = stringResource(R.string.label_buy_now), onClick = {})
        }
    }
}

@Composable
@Preview
fun FooterSectionPreview() {
    FooterSection(theme = Cabify.theme, totalToPay = 120.00, totalWithoutPromotions = 150.00)
}