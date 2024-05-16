package com.core.common.theme

import com.google.gson.annotations.SerializedName
import com.core.common.theme.decision.SemanticTextStyle

data class Decision(
    @SerializedName("typescale") val textStyle: SemanticTextStyle = SemanticTextStyle(),
    @SerializedName("color") val color : SemanticColor = SemanticColor()
)
