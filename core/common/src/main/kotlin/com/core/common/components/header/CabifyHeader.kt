package com.core.common.components.header

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.core.common.theme.Cabify
import com.core.common.theme.CabifyTheme


@Composable
fun CabifyHeader(text: String, theme: CabifyTheme = Cabify.theme) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(theme.semanticColor.background)
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = theme.padding.padding8),
            textAlign = TextAlign.Center,
            text = text,
            color = theme.semanticColor.onSecondary,
            style = theme.semanticTextStyle.cabifyButton(false)
        )
    }
}
