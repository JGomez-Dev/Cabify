package com.core.common.theme.decision

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.core.common.theme.SemanticColor
import com.core.common.theme.font.CabifyFont
import com.core.common.utils.extensions.dpToSp

data class SemanticTextStyle(
    val cabifyHeading0: @Composable (fontScaling: Boolean) -> TextStyle = textStyleGenerator(
        fontFamily = CabifyFont.CABIFY_HEADING_0,
        fontSize = 32,
        lineHeight = 40,
        color = Color(0xFF232323)
    ),
    val cabifyHeading1: @Composable (fontScaling: Boolean) -> TextStyle = textStyleGenerator(
        fontFamily = CabifyFont.CABIFY_HEADING_1,
        fontSize = 28,
        lineHeight = 36,
        color = Color(0xFF232323)
    ),
    val cabifyHeading2: @Composable (fontScaling: Boolean) -> TextStyle = textStyleGenerator(
        fontFamily = CabifyFont.CABIFY_HEADING_2,
        fontSize = 24,
        lineHeight = 32,
        color = Color(0xFF232323)
    ),
    val cabifyHeading3: @Composable (fontScaling: Boolean) -> TextStyle = textStyleGenerator(
        fontFamily = CabifyFont.CABIFY_HEADING_3,
        fontSize = 20,
        lineHeight = 24,
        color = Color(0xFF232323)
    ),
    val cabifyHeading4: @Composable (fontScaling: Boolean) -> TextStyle = textStyleGenerator(
        fontFamily = CabifyFont.CABIFY_HEADING_4,
        fontSize = 18,
        lineHeight = 20,
        color = Color(0xFF232323)
    ),
    val cabifyButton: @Composable (fontScaling: Boolean) -> TextStyle = textStyleGenerator(
        fontFamily = CabifyFont.CABIFY_HEADING_4_BUTTON,
        fontSize = 18,
        lineHeight = 24,
        color = Color(0xFF232323)
    ),
    val cabifyBodyLead: @Composable (fontScaling: Boolean) -> TextStyle = textStyleGenerator(
        fontFamily = CabifyFont.CABIFY_HEADING_4_BODY_LEAD,
        fontSize = 16,
        lineHeight = 24,
        color = Color(0xFF232323)
    ),
    val cabifyHeading5: @Composable (fontScaling: Boolean) -> TextStyle = textStyleGenerator(
        fontFamily = CabifyFont.CABIFY_HEADING_5,
        fontSize = 16,
        lineHeight = 24,
        color = SemanticColor().onSecondary
    ),
    val cabifyLink: @Composable (fontScaling: Boolean) -> TextStyle = textStyleGenerator(
        fontFamily = CabifyFont.CABIFY_LINK,
        fontSize = 16,
        lineHeight = 24,
        color = SemanticColor().onSecondary
    ),
    val cabifyValue: @Composable (fontScaling: Boolean) -> TextStyle = textStyleGenerator(
        fontFamily = CabifyFont.CABIFY_VALUE,
        fontSize = 16,
        lineHeight = 24,
        color = SemanticColor().onSecondary
    ),
    val cabifyLabel: @Composable (fontScaling: Boolean) -> TextStyle = textStyleGenerator(
        fontFamily = CabifyFont.CABIFY_LABEL,
        fontSize = 16,
        lineHeight = 24,
        color = SemanticColor().onSecondary
    ),
    val cabifyHeading6: @Composable (fontScaling: Boolean) -> TextStyle = textStyleGenerator(
        fontFamily = CabifyFont.CABIFY_HEADING_6,
        fontSize = 14,
        lineHeight = 20,
        color = SemanticColor().onSecondary
    ),
    val cabifyBody: @Composable (fontScaling: Boolean) -> TextStyle = textStyleGenerator(
        fontFamily = CabifyFont.CABIFY_BODY,
        fontSize = 14,
        lineHeight = 20,
        color = SemanticColor().onSecondary
    ),
    val cabifyCaption: @Composable (fontScaling: Boolean) -> TextStyle = textStyleGenerator(
        fontFamily = CabifyFont.CABIFY_CAPTION,
        fontSize = 13,
        lineHeight = 16,
        color = SemanticColor().onSecondary
    )
)


private fun textStyleGenerator(
    fontFamily: CabifyFont,
    fontSize: Int,
    lineHeight: Int,
    color: Color
): @Composable (fontScaling: Boolean) -> TextStyle =
    { fontScaling ->
        TextStyle(
            fontFamily = fontFamily.fontFamily,
            fontSize = if (fontScaling) fontSize.sp else fontSize.dpToSp,
            lineHeight = if (fontScaling) lineHeight.sp else lineHeight.dpToSp,
            color = color
        )
    }
