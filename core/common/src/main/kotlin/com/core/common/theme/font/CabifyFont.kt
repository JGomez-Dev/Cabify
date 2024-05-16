package com.core.common.theme.font

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.core.common.R

enum class CabifyFont(private val resId: Int) {
    CABIFY_HEADING_0(R.font.sfproroundedbold),
    CABIFY_HEADING_1(R.font.sfproroundedbold),
    CABIFY_HEADING_2(R.font.sfproroundedbold),
    CABIFY_HEADING_3(R.font.sfproroundedbold),
    CABIFY_HEADING_4(R.font.sfproroundedbold),
    CABIFY_HEADING_4_BUTTON(R.font.sfproroundedbold),
    CABIFY_HEADING_4_BODY_LEAD(R.font.sfproroundedmedium),
    CABIFY_LINK(R.font.sfproroundedbold),
    CABIFY_VALUE(R.font.sfproroundedsemibold),
    CABIFY_LABEL(R.font.sfproroundedsemibold),
    CABIFY_HEADING_5(R.font.sfproroundedbold),
    CABIFY_HEADING_6(R.font.sfproroundedbold),
    CABIFY_BODY(R.font.sfproroundedsemibold),
    CABIFY_CAPTION(R.font.sfproroundedmedium);

    val fontFamily: FontFamily
        get() = FontFamily(Font(resId))

}