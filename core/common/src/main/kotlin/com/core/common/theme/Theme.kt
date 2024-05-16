package com.core.common.theme

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.accessibility.AccessibilityManager
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.Typography
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalContext
import com.core.common.theme.decision.SemanticTextStyle

object Cabify {
    @Stable
    var theme: CabifyTheme by mutableStateOf(CabifyTheme())
}

@Immutable
data class CabifyTheme(
    val borderRadius: BorderRadius = BorderRadius(),
    val fontFamily: FontFamily = FontFamily(),
    val padding: Padding = Padding(),
    val dimension: Dimension = Dimension(),
    private val decision: Decision = Decision()
) {
    val semanticTextStyle: SemanticTextStyle
        get() = decision.textStyle
    val semanticColor: SemanticColor
        get() = decision.color
}


@Composable
@ReadOnlyComposable
@SuppressLint("ConflictingOnColor")
private fun cabifyColors(semanticColor: SemanticColor): Colors = lightColors(
    primary = semanticColor.accent,
    primaryVariant = semanticColor.primaryVariant,
    secondary = semanticColor.secondary,
    secondaryVariant = semanticColor.secondaryVariant,
    background = semanticColor.background,
    surface = semanticColor.surface,
    error = semanticColor.error,
    onPrimary = semanticColor.onPrimary,
    onSecondary = semanticColor.onSecondary,
    onError = semanticColor.onError,
)

@Composable
private fun cabifyTypography(semanticTextStyle: SemanticTextStyle) =
    Typography(
        defaultFontFamily = Cabify.theme.fontFamily.text,
        h1 = semanticTextStyle.cabifyHeading1(false),
        h2 = semanticTextStyle.cabifyHeading2(false),
        h3 = semanticTextStyle.cabifyHeading3(false),
        h4 = semanticTextStyle.cabifyHeading4(false),
        h5 = semanticTextStyle.cabifyHeading5(false),
        h6 = semanticTextStyle.cabifyHeading6(false),
        subtitle1 = semanticTextStyle.cabifyLink(false),
        subtitle2 = semanticTextStyle.cabifyLink(false),
        body1 = semanticTextStyle.cabifyBody(false),
        body2 = semanticTextStyle.cabifyHeading6(false),
        button = semanticTextStyle.cabifyButton(false),
        caption = semanticTextStyle.cabifyCaption(false),
        overline = semanticTextStyle.cabifyLink(false)
    )


/**
 * True if TalkBack is enabled.
 */
val LocalTalkBackState = staticCompositionLocalOf {
    Log.e("Flame", "LocalTalkBackState is not initialized. Did you forget to provide use ProvideTalkBackCompositionLocal in your root Composable?")
    false
}


@Composable
private fun rememberTalkBackState(): Boolean {
    val context = LocalContext.current
    val accessibilityManager =
        context.getSystemService(Context.ACCESSIBILITY_SERVICE) as AccessibilityManager
    var talkBackState by remember { mutableStateOf(accessibilityManager.isTalkBackEnabled) }

    DisposableEffect(context) {
        val listener = AccessibilityManager.AccessibilityStateChangeListener { talkBackState = it }
        accessibilityManager.addAccessibilityStateChangeListener(listener)
        onDispose { accessibilityManager.removeAccessibilityStateChangeListener(listener) }
    }

    return talkBackState
}

inline val AccessibilityManager?.isTalkBackEnabled: Boolean
    get() = if (this == null) false else isEnabled && isTouchExplorationEnabled

@Composable
fun CabifyTheme(
    theme: CabifyTheme = Cabify.theme, content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalTalkBackState provides rememberTalkBackState(),
        content = {
            MaterialTheme(
                colors = cabifyColors(theme.semanticColor),
                typography = cabifyTypography(semanticTextStyle = theme.semanticTextStyle),
                shapes = Shapes(),
                content = content,
            )
        },
    )
}
