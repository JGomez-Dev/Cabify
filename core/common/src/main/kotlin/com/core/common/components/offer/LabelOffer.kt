package com.core.common.components.offer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.core.common.theme.Cabify
import com.core.common.theme.CabifyTheme

@Composable
fun LabelOffer(theme: CabifyTheme = Cabify.theme, text :String) {
    Box(
        modifier = Modifier
            .background(color = Cabify.theme.semanticColor.onSecondary, shape = RoundedCornerShape(16))
    )
    {
        Text(
            modifier = Modifier
                .padding(4.dp), text = text,
            style = theme.semanticTextStyle.cabifyCaption(false),
            color = theme.semanticColor.background
        )
    }
}