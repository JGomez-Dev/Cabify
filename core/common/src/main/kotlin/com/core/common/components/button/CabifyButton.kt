package com.core.common.components.button

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import com.core.common.theme.Cabify
import com.core.common.theme.CabifyTheme


const val CABIFY_BUTTON_TAG = "CABIFY_BUTTON_TAG"

@Composable
fun CabifyButton(text: String, theme: CabifyTheme = Cabify.theme, onClick: () -> Unit) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .testTag(CABIFY_BUTTON_TAG),
        onClick = onClick,
        shape = RoundedCornerShape(corner = CornerSize(theme.borderRadius.sizeBorderRadius03)),
        colors = ButtonColors(
            contentColor = theme.semanticColor.surface,
            disabledContentColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,
            containerColor = theme.semanticColor.accent
        )
    ) {
        Text(
            text = text,
            style = theme.semanticTextStyle.cabifyButton(false),
            color = theme.semanticColor.surface
        )
    }
}