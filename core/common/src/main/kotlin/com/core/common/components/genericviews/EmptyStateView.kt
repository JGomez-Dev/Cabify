package com.core.common.components.genericviews

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.core.common.theme.Cabify
import com.core.common.theme.CabifyTheme

@Composable
fun EmptyStateView(message: String, theme: CabifyTheme = Cabify.theme) {
    Box(modifier = Modifier.background(theme.semanticColor.background).fillMaxWidth(), contentAlignment = Alignment.Center) {
        Text(
            modifier = Modifier.padding(theme.padding.padding32),
            text = message,
            style = theme.semanticTextStyle.cabifyValue(false)
        )
    }
}

@Composable
@Preview
fun EmptyStateViewPreview() {
    EmptyStateView("Sin promociones")
}