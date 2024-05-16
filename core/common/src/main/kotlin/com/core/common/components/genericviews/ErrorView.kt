package com.core.common.components.genericviews

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.core.common.theme.Cabify
import com.core.common.theme.CabifyTheme

@Composable
fun ErrorView(errorMessage: String, theme: CabifyTheme = Cabify.theme) {
    Box(modifier = Modifier.fillMaxSize().background(theme.semanticColor.background), contentAlignment = Alignment.Center) {
        Text(text = errorMessage)
    }
}