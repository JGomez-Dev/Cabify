package com.core.common.components.notification

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.core.common.theme.Cabify

@Composable
fun CardItem(
    color: com.core.common.theme.SemanticColor,
    articles: Int?
) {
    Box(
        contentAlignment = Alignment.Center
    ) {
        Icon(imageVector = Icons.Sharp.ShoppingCart, contentDescription = "")
        Text(
            modifier = Modifier
                .size(20.dp)
                .offset((0).dp, (-3).dp),
            textAlign = TextAlign.Center,
            text = articles.toString(),
            fontSize = 14.sp,
            color = color.surface,
        )
    }
}

@Composable
@Preview
fun BubbleNotificationPreview(){
    CardItem(Cabify.theme.semanticColor, 4)
}
